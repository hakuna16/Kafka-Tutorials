# Creating Topic and publising to topic

1.Create a new topic.

- Go to the kafka folder inside.`/opt/bitnami/kafka/bin`
- Run the following command to create topic. <br/>
    
    kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4 --topic TestTopic

2. Listing topics <br/>

    ` kafka-topics.sh --list --bootstrap-server localhost:9092`

### How to instantiate a Console Producer Without Key?

3. Producing message on topic.<br/>

    `kafka-console-producer.sh --topic TestTopic --bootstrap-server localhost:9092`

### How to instantiate a Console Consumer Without Key?

4. Consuming message on topic. <br/>

    `kafka-console-consumer.sh --topic TestTopic --from-beginning --bootstrap-server localhost:9092`

 - _**--from-beginning**_ : means it will read the message from the begging of the topic.

<details><summary>What will happen if we not put --from-beginning</summary>
 
<p>
    
````
    * It will not the read the messgage from the topic which is already there.
    * It will randomly read the new data from the topic.
    * Its basically reading the data form the different partition.
````
</p>
</details>

Next : Advance Topic creation
