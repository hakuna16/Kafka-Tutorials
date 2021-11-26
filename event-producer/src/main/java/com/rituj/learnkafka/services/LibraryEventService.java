package com.rituj.learnkafka.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rituj.learnkafka.domain.BookRecord;
import com.rituj.learnkafka.domain.LibraryEvent;
import com.rituj.learnkafka.producer.LibraryEventProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import javax.websocket.SendResult;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@AllArgsConstructor
public class LibraryEventService {

    private LibraryEventProducer libraryEventProducer;

    public void postLibraryEvent(final LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
        log.info("Sending library data to the kafka topic");
        BookRecord bookRecord = new BookRecord(1231,"Test", "Auth");
        var bookAuthor = bookRecord.bookName();

        var event = postLibraryEventSync(libraryEvent);


        switch (bookAuthor) {
            case "A" -> System.out.println("A");
            case "B" -> System.out.println("B");
            default -> System.out.println("Default");
        }

        if(event instanceof SendResult sentresult){
            var data = (Throwable) event.getException();
            sentresult.getException();
        }


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
