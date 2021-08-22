# How Kafka Cluster distributes the Client Requests ? - Leader/Follower

## How Topics are distributed?

So, we have a zookeeper and a Kafka cluster.
 - One of the available broker will behave as a controller. usually this is the first broker to join the 
   cluster.
 - When create topic command issued to the zookeeper, the zookeeper takes care of redirecting this      
   request to the controller.
 - The role of this controller is to distribute the ownership of the partitions to the available broker.
 - In distributed systems, this concept of distributing partitions to the brokers is called a leader  
   assignment.
 - This way the topic is distributed accross the cluster.

 ### How Topics are distributed? : From Kafka producer End.

 - It has the layer called Partition or which takes care of determining which partition the message is 
   going.
 - The Partitioner will decide which partition the message will go.
 - The client will always invoke the leader of the partition after that.
 - Then the message will be persited in the filesystem of the broker.

 ### How Topics are distributed? : From Kafka consumer End.

Each broker owns a partition.
- The poll request goes to all the brokers and retrieves the record from them and retrieves.
- Records are handed over to the consumer and the consumer process.
- So From the consumer end, the request to retrieve the data are distributed between the brokers.
- When the consumer are multiple with the same group id, the pooling will happen parallely from the desired partion whihc they are intrested in.

Partition leaders are assigned during topic Creation
Clients will only invoke leader of the partition to produce and consume data
Load is evenly distributed between the brokers

