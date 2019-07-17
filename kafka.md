# Kafka Streaming
* Speaker: @tlberglund
* Slides:

## ACID
* https://en.wikipedia.org/wiki/ACID

## What is Kafka?
* messages written to a topic.
* topics are a durable log of events, kept in order.
* message keys are not unique, they just write to a pool
* Broker just sees producers and consumers

### Producers
* Programs that write into clusters
* a message is consumed and it stays in the queue, so we can have multiple consumers

### Connect Server
* handles connections of streams to things like elastic search
* hub.confluent.io has all the connectors
* when stuff breaks, connect remembers it's last successful point

## Streams
* It's a Java API. It's functional.
* Filter, join, aggregate
* Locate stream processing and scales like a consumer group
* A new website is coming of kafka recipes

## KSQL
* On top of kafka, they are continuously streaming queries.
* Example of real time stream of ratings that is calculated.

## Cool, but can I ACID with Kafka?
* Atomicity - write a message to a topic and it is atomic. It happens or it doesn't.
* Consitency - sorta shakes out with isolation done properly. If the work is one message, it works well.
* Isolation - a k-table is backed by a topic, sorta like a hash table. REST endpoint can answer those questions.
* Durability - with Ksql we get a table with a one-liner

## Onions and Databases
* What is a database anyway? It's a bunch of layers.
* Tables, rows of data, storage layer, a commit log
* Kafka is writing a database that is inside-out