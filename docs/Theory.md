# Kafka Topics & Partitions

## Kafka Topics

- Topic is an Entity in Kafka with a name.

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

