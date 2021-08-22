# Commit Log and Retention Policy

## Commit Log
One of the key qualities of Kafka is the concept of retaining the record for a certain period of time.

- When the producer sends a message, it first reaches the topic and then the record gets returned to a   
  file system in the machine.
- So the file system is where the Kafka Brooker's installed, that is our local machine.
- The record is always written into the file system as bite's the file system.
- Where that file needs to be written is configured using the log, not the property.
- The property is available in the server, not properties file.

So when the consumer who is continuously pooling for new records can only see there are cards that are
committed to the file system as new records are produced for the topic, then the records get uppended.

## Retention Policy
- Determines how long the message is retained ?
- Configured using the property log.retention.hours in server.properties file
- Default retention period is 168 hours (7 days)

Viewing the Commit Log


kafka-run-class.sh kafka.tools.DumpLogSegments --deep-iteration --files bitnami/kafka/data/TestTopic-1/00000000000000000000.log

````sh
Dumping /bitnami/kafka/data/TestTopic-1/00000000000000000000.log
Starting offset: 0
I have no name!@cfb1ec480e2b:/opt/bitnami/kafka/bin$ 
````
