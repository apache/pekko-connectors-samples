package samples.javadsl;

// #imports

import org.apache.mina.util.AvailablePortFinder;
import org.apache.pekko.Done;
import org.apache.pekko.actor.typed.ActorSystem;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.apache.pekko.japi.function.Creator;
import org.apache.pekko.japi.function.Function;
import org.apache.pekko.stream.IOResult;
import org.apache.pekko.stream.connectors.file.javadsl.Directory;
import org.apache.pekko.stream.connectors.file.javadsl.LogRotatorSink;
import org.apache.pekko.stream.connectors.ftp.FtpCredentials;
import org.apache.pekko.stream.connectors.ftp.KeyFileSftpIdentity;
import org.apache.pekko.stream.connectors.ftp.SftpIdentity;
import org.apache.pekko.stream.connectors.ftp.SftpSettings;
import org.apache.pekko.stream.connectors.ftp.javadsl.Sftp;
import org.apache.pekko.stream.javadsl.Compression;
import org.apache.pekko.stream.javadsl.Flow;
import org.apache.pekko.stream.javadsl.Keep;
import org.apache.pekko.stream.javadsl.Sink;
import org.apache.pekko.stream.javadsl.Source;
import org.apache.pekko.util.ByteString;
import playground.SftpServerEmbedded;
import playground.filesystem.FileSystemMock;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
// #imports

public class Main {

  private void run() throws IOException {
    final ActorSystem<Void> actorSystem = ActorSystem.create(Behaviors.empty(), "RotateLogsToFtp");

    final FileSystem ftpFileSystem = new FileSystemMock().fileSystem;

    final String privateKeyPassphrase = new String(SftpServerEmbedded.clientPrivateKeyPassphrase());
    final String pathToIdentityFile = SftpServerEmbedded.clientPrivateKeyFile();
    final String username = "username";
    final String password = username;
    final String hostname = "localhost";

    int port = AvailablePortFinder.getNextAvailable(21_000);

    Path home = ftpFileSystem.getPath(SftpServerEmbedded.FtpRootDir()).resolve("tmp");
    if (!Files.exists(home)) {
      Files.createDirectories(home);
    }

    SftpServerEmbedded.start(ftpFileSystem, port);

    // #sample
    Iterator<ByteString> data =
        Arrays.asList('a', 'b', 'c', 'd').stream()
            .map(
                e -> {
                  char[] arr = new char[100];
                  Arrays.fill(arr, e);
                  return ByteString.fromString(String.valueOf(arr));
                })
            .iterator();

    // (2)
    Creator<Function<ByteString, Optional<String>>> rotator =
        () -> {
          final char[] last = {' '};
          return (bs) -> {
            char c = (char) bs.head();
            if (c != last[0]) {
              last[0] = c;
              return Optional.of("log-" + c + ".z");
            } else {
              return Optional.empty();
            }
          };
        };

    // (3)
    KeyFileSftpIdentity identity =
        SftpIdentity.createFileSftpIdentity(pathToIdentityFile, privateKeyPassphrase.getBytes());
    SftpSettings settings =
        SftpSettings.create(InetAddress.getByName(hostname))
            .withPort(port)
            .withSftpIdentity(identity)
            .withStrictHostKeyChecking(false)
            .withCredentials(FtpCredentials.create(username, password));

    Function<String, Sink<ByteString, CompletionStage<IOResult>>> sink =
        path -> {
          Sink<ByteString, CompletionStage<IOResult>> ftpSink = Sftp.toPath("tmp/" + path, settings);
          return Flow.<ByteString>create()
              .via(Compression.gzip()) // (4)
              .toMat(ftpSink, Keep.right());
        };

    CompletionStage<Done> completion =
        Source.fromIterator(() -> data)
            .runWith(LogRotatorSink.withSinkFactory(rotator, sink), actorSystem);
    // #sample

    completion
        .thenApply(
            (i) ->
                Directory.ls(home)
                    .runForeach((f) -> System.out.println(f.toString()), actorSystem))
        .whenComplete(
            (res, ex) -> {
              if (ex != null) {
                ex.printStackTrace();
              }
              actorSystem.terminate();
              actorSystem.getWhenTerminated().thenAccept(t -> SftpServerEmbedded.stopServer());
            });
  }

  public static void main(String[] args) throws IOException {
    new Main().run();
  }
}
