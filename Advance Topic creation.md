# Creating Topic and publising to topic

Listing the created topics.

    kafka-topics.sh --list --zookeeper zookeeper:2181

## Sending Kafka Message with key and value.

- Kafka Message these sent from producer has two properties
    - Key (optional) 
    - Value

- Whenever we send any data to the kafka or produce any data to kafka. there is a layred service called
  as partionar, which takes care of sending the message to the partitions.

- As Ordering is only gaurentee at the partion level. 
  - So if we pulish then the partion will first check if the key is present or not.
  - If not then the data to the topic will follow the round robin approach, and sends the data to any
   partition.
  - In this Approach the consumer will not be able to get the data in same order, beacuse the consume r 
   will poll the message from all the partion at the same time.

- When sent the data with key-value pair.
  - The partionar service will apply some hashing algorithm to put the data in the same partition.
  - If the same key is send it will resolve to the same partition.
  - If the diffrent key is sent it will resolve to the diffrent partition.
  - SO Same key will always resolve to the same partition.

### How to instantiate a Console Producer With Key?

Producing message on topic.<br/>

    kafka-console-producer.sh --topic TestTopic --bootstrap-server localhost:9092 --property "key.separator=-" --property "parse.key=true"

### How to instantiate a Console Consumer With Key?

Consuming message on topic. <br/>

    kafka-console-consumer.sh --topic TestTopic --from-beginning --bootstrap-server localhost:9092 --property "key.separator= - " --property "print.key=true








