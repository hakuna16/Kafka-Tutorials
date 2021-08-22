# Consumer offset

- Any message we produce to the kafka will go to some partion , ans it will have the offset.


Consumer have three options to read
 - from-beginning
 - latest (read only the latest message)
 - specific offset (read message by passing the offset to the consumer)

For any kafka consumer its required to provide the consumer group id.

- The consumer in general pulls and retrieves a multiple records at the same time as it process each message.
- It moves the consumer read offset one by one.
- If for some reason the consumer is crashed while the consumer was down, the producer of the
 topic produced some more messages.
- Now the consumer is brought up after some time.

How does it know that it needs to read from offset for the consumer?

- Offset in general are stored in an internal topic called __consumer_offset.

- So it starts from offset zero and it proceeds one by one once all the pull records and read the consumer
  comments offset to the topic called __consumer_offsets topic with the group, Now
  the consumer goes down and new records are produced to the topic in the meantime.

Once it is brought up after some time,the consumer knows where to start reading the messages in the topic, using the information that's available and the __consumer_offsets topic with the group.

- Consumer offset behaves like a bookmark for the consumer to start reading the messages from the point it left.

Commands to see the __consumer_offset

Listing topics <br/>

    kafka-topics.sh --list --zookeeper zookeeper:2181
 