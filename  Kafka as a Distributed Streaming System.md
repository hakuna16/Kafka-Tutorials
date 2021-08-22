#  Kafka as a Distributed Streaming System

Distributed systems are a collection of systems working together to deliver a value.

## Characteristics of Distributed System
- Availability and Fault Tolerance.
- Reliable Work Distribution.
- Easily Scalable.
- Handling Concurrency is fairly easy.

## Kafka as a Distributed System
IN Koka Cluster the Zookeier manages the distributed systems.

- Normally you can have one broker or to have more than one broker.
- If we have three brokers on the cluster. It will be managed by zookeeper.
- All the brokers send heartbeat's to the zookeeper at regular intervals to ensure that the state of the
  broker is healthy and active to serve client request.
- The client requests are evenly distributed between them, and it handles the load.
- If one of the broker goes down, then the cluster manager, which is the zookeeper here, gets notified,  
  and the then all the client requests will be routed to the other available brokers.

## How were Kafka handle data loss?

Using replication