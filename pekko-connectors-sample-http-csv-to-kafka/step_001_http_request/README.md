## Step 1: Send HTTP request

### Description

This code uses Pekko HTTP to request a file containing listed companies from the NASDAQ web site.

It starts the Actor System, imports the Actor System's dispatcher as `ExecutionContext`, and gets a stream materializer from the Actor System.

The HTTP request is created as value (it will be sent multiple times in later steps) and sets a specific HTTP request header.

The request is run in an Pekko Stream from the single value, issuing the request by Pekko HTTP, and printing out the HTTP response.

Once the stream completes, the Actor System is terminated and the program exits.
