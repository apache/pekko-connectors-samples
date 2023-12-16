# Apache Pekko Connectors sample

## Rotate data stream over to multiple compressed files on SFTP server

This example reads a stream of data and uses @extref[Alpakka File](alpakka:file.html) `LogRotatorSink` to write multiple files which get rotated triggered by a rotation function, the files are zipped in-flow and written to an SFTP server with @extref[Alpakka FTP](alpakka:ftp.html).

Browse the sources at @link:[Github](https://github.com/apache/incubator-pekko-connectors-samples/tree/main/pekko-connectors-sample-rotate-logs-to-ftp) { open=new }.

To try out this project clone @link:[the Pekko-Connectors Samples repository](https://github.com/apache/incubator-pekko-connectors-samples) { open=new } and find it in the `pekko-connectors-sample-rotate-logs-to-ftp` directory.
