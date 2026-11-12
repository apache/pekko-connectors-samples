# Apache Pekko Connectors sample

## Download all files an FTP server to local files

This example uses @extref[Pekko Connectors FTP](pekko-connectors:ftp.html) to read from the FTP server, and stores files using Pekko Stream `FileIO`.

Browse the sources at @link:[Github](https://github.com/apache/pekko-connectors-samples/tree/main/pekko-connectors-sample-ftp-to-file) { open=new }.

To try out this project clone @link:[the Pekko-Connectors Samples repository](https://github.com/apache/pekko-connectors-samples) { open=new } and find it in the `pekko-connectors-sample-ftp-to-file` directory.

### Organize Scala imports

Run `sbt sortImports` to organize Scala imports with Scalafix and remove unused
imports. The command enables SemanticDB and unused-import diagnostics for this sbt session
and processes both
main and test sources. Java sources are not rewritten.
