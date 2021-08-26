package com.rituj.learnkafka.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rituj.learnkafka.domain.LibraryEvent;
import com.rituj.learnkafka.producer.LibraryEventProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@AllArgsConstructor
public class LibraryEventService {

    private LibraryEventProducer libraryEventProducer;

    public void postLibraryEvent(final LibraryEvent libraryEvent) throws JsonProcessingException {
        log.info("Sending library data to the kafka topic");
        libraryEventProducer.sendLibraryEvent(libraryEvent);
    }

    public SendResult<Integer, String> postLibraryEventSync(final LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
        log.info("Sending library data to the kafka topic");
        return libraryEventProducer.sendLibraryEventSync(libraryEvent);
    }

    public void postLibraryEventUsingSend(final LibraryEvent libraryEvent) throws JsonProcessingException {
        log.info("Sending library data to the kafka topic");
        libraryEventProducer.sendLibraryEventUsingSend(libraryEvent);
    }

    public void postLibraryEventUsingSendWithProducerRecord(final LibraryEvent libraryEvent) throws JsonProcessingException {
        log.info("Sending library data to the kafka topic");
        libraryEventProducer.sendLibraryEventUsingSendWithProducerRecord(libraryEvent);
    }
}
