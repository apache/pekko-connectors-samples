## Step 3: parse CSV

### Description

The binary data in @scaladoc:[ByteString](org.apache.pekko.util.ByteString)s is passed into @extref:[Pekko-Connectors CSV](pekko-connectors:data-transformations/csv.html) to be parsed and converted per line into a Map. The stream elements becomes a @scala[`Map[String, ByteString]`]@java[`Map<String, ByteString>`], one entry per column using the column headers as keys.
