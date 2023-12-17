package samples.javadsl;

import static org.junit.Assert.assertTrue;

import com.google.common.collect.Streams;
import org.apache.pekko.NotUsed;
import org.apache.pekko.actor.ActorSystem;
import org.apache.pekko.japi.Pair;
import org.apache.pekko.stream.javadsl.Flow;
import org.apache.pekko.stream.javadsl.Sink;
import org.apache.pekko.stream.javadsl.Source;
import org.apache.pekko.testkit.javadsl.TestKit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTest {
    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("WebsocketExampleMainTest");
    }

    @AfterClass
    public static void tearDown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void addIndexFlow_Test() throws Exception {
        final Main example = new Main(new Helper());

        List<String> messages = Arrays.asList(
                "I say high, you say low",
                "You say why and I say I don't know",
                "Oh, no",
                "You say goodbye and I say hello"
        );

        final Flow<String, String, NotUsed> addIndexFlow = example.addIndexFlow();

        final CompletionStage<List<String>> future = Source.from(messages)
                .via(addIndexFlow)
                .runWith(Sink.seq(), system);
        final List<String> result = future.toCompletableFuture().get(3, TimeUnit.SECONDS);

        assert(result.size() == messages.size());

        final Pattern pattern = Pattern.compile("index: \\d+, message: (.*)");
        Streams.zip(
                messages.stream(),
                result.stream(),
                Pair::create)
                .forEachOrdered(pair -> {
                    String message = pair.first();
                    String resultMessage = pair.second();
                    Matcher matcher = pattern.matcher(resultMessage);
                    assertTrue(matcher.find());
                    assert(matcher.groupCount() == 1);
                    assert(matcher.group(1).equals(message));
                });
    }
}
