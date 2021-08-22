# Consumer Groups

- group.id is mandatory
- goup.id plays a major role when its comes to consumption.

What do I exactly mean by that?

 - If we have a topic here named TestTopic and it has 4 partiton. and we have one consumer with a group.id as groupId-1.

 - We have one single consumer pulling all the four partitions in the topic and processing them.
 - Note: The pullup is always a single threaded.
 - So in this case, a single thread is going to pull from all the partitions.

If the producer of the topic is going to produce messages at a faster rate than the consumer processing rate.Then in that case it will introduce a lag in the consumer and you might end up not processing the event's real time.

This is where consumer groups comes in.

If we spawn another instance of consumer, but make sure you are using the same group.
 - The partitions are split between the two instances of the consumer partitions.
 - This will help process the records a little faster than it was before.

If we have four instances of consumer application, but the group ideas seem across all the different instances that we have.
- you are going to process for records parallel.

So the consumer groups are fundamentally the basis for a scalable message consumption.

Now if we spawn one more consumer, that will be sitting idle, and that will be the inefficient use of the resources.

Scenerio 2: Where we have 2 diffrent application reading from the same topic.

In this case we will be needing two different group id for the consuption of the messages.

Who manages the Consumer Groups.
 - Its the kafka broker who manages the consumer group.
 - Kafka broker act as a group co-ordinator.

Command will list the consumer group.

 kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list

How to instantiate a Console Consumer With Consumer Group?

  kafka-console-consumer.sh --topic <Topic_name> --from-beginning  --bootstrap-server localhost:9092 --group <group-name>

ex: kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic TestTopic --group TestTopic-Group-1

kafka-console-consumer.sh --topic TestTopic --from-beginning --bootstrap-server localhost:9092 --property "key.separator= - " --property "print.key=true" --group TestTopic-Group-1





