package com.rituj.learnkafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Class to auto create the topic.
 * This is not at all recommended in the production.
 */
@Configuration
public class AutoCreateConfig {

    @Bean
    public NewTopic libraryEvent() {

        return TopicBuilder.name("library-events")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
