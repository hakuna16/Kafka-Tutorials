# Kafka Producer in Spring

- Produce records in to Kafka Topic.
- Similar to JDBCTemplate for DB.

## How Kafka template work behind the scene.
When the message is published to topic it goes through different layers.

- Before the messages sent to the Kafka, the very first layer is the serializer.
  - A new record that's sent to the Kafka needs to be serialized to bytes.
  - There are two different types of serialization techniques that's applied to any record.
    - The key serializer 
    - The value serializer.
  - This configuration is mandatory for any producer.
  - The client needs to provide the key serializer value and value serializer value.

The Kafka client Java libraries comes with some predefined seducers.

- The second layer is a partitior  
  - This layer determines which partition the message is going to go into the topic.
  - The kakfa producer API comes up with the default partitior  logic, and in most cases that's enoug the 
    handle the partitioning logic.
  - We can override this default partition of logic too. But for most of use case, default partition 
   will work.

- The next layer is the Record Accumulator.
  - Any record that's sent from the Kafka template won't get sent to the topic immediately.
  - The record accumulator buffers, the records and the records are sent to the Kafka topic once the
    buffers is full.
  - The reason for this approach is to limit the number of trips from the application to the kafka
    cluster,
  - This eventually avoids the overhead of bombarding the cluster but numerous requests, which also
    helps in improving the overall performance of the system.
      - If we have a topic with three partitions, then you will have three Rocard batch.
      - Each and every record batch as a batch size, which is represented by the batch.size property.
        and the value is represented as a number of bytes.
   - It also has an overall buffer memory, which is represented by the property buffer, not memory.
     And this values also represented as a number of bytes.

what scenarios, the messages are sent to the topic.
- Once the batches full, then the message will be sent to the topic.
- There may be many scenarios where the record batch won't fill up.
- The producer API is not going to wait for so long to send the message to the topic.
- There is also another handy property called linger.ms, which will be used in this case to publish
  the records into the topic.
  - As the name suggests, just this value is represented in millisecond.
- If the batch is not full and the records accumulated, meet the linger.ms Value, then the records
  will be sent to Kafka.