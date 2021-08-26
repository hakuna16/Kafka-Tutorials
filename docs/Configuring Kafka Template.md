# Configuring Kafka Template

We need to provide these mandatory values to configure a simple version of Kafka template.

1. bootstrap servers, which represents the broken address.
2. key serializer
3. value serializer.

````sh
    bootstrap-servers: localhost:9092,localhost:9093,localhost:9094
    key-serializer: org.apache.kafka.common.serialization.IntegerSerializer
    value-serializer: org.apache.kafka.common.serialization.StringSerializer
````

By default the console producer examples by useses the StringSerializer.

In Spring boot we can use Spring's Auto Configuration technique.

````ymal

spring:
  config:
    activate:
      on-profile: local
  kafka:
    template:
      default-topic: library-events
    producer:
      bootstrap-servers: localhost:9092,localhost:9093,localhost:9094
      key-serializer: org.apache.kafka.common.serialization.IntegerSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer

````

