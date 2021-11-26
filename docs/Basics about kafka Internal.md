# Main Concepts and Terminology

## Event

- An event records the fact that "something happened" in the world or in your business. 
- It is also called record or message in the documentation.
- When you read or write data to Kafka, you do this in the form of events. 

## Producers & Consumers
- Producers are those client applications that publish (write) events to Kafka, and consumers are those that subscribe to (read and process) these events.
- In Kafka, producers and consumers are fully decoupled and agnostic of each other, which is a key design element to achieve the high scalability.

## Kafka Topics
- Topic is an Entity in Kafka with a name.
- Events are organized and durably stored in topics.

## Topic and Partitions
- Partition is where the message lives inside the topic.
- Each Topic will be create with one or more partitions.

### Details:
- Each Partition is an ordered , immutable sequence of records
- Each record is assigned a sequential number called offset
- Each partition is independent of each other
- Ordering is guaranteed only at the partition level
- Partition continuously grows as new records are produced
- All the records are persisted in a commit log in the file system where Kafka is installed.


#Next: [What is Event Streaming]https://hakuna16.github.io/Kafka-Tutorials/what%20is%20Event%20Streaming.html
