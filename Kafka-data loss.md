# How Kafka handles Data Loss ?

If one broker goes down then, the data which is there in the logs will get lost.

Kafka handels this issue using replication.

When ever we create topic we pass replication-factor to the cluster so that data will be replicated accross the brokers.

- When we publish a messgae to the topic and if we have passed the `--replication-factor` value, then the 
  data will be replicated.
- The data replicated on the other brokers of the cluster are called as follower for the first one.
- The replica of the leader is called `leader replica` and others are called as `follower replica`.

When the failure will happen then first Zoopeker will be notified and it will assign the other broker as the new leader using controler node.

## In-Sync Replica(ISR)
- Represents the number of replica in sync with each other in the cluster
- Includes both leader and follower replica Recommended value is always greater than 1
- Ideal value is ISR == Replication Factor
- This can be controlled by min.insync.replicas property
- It can be set at the broker or topic level

