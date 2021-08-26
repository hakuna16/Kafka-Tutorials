package com.rituj.learnkafka.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rituj.learnkafka.domain.LibraryEvent;
import com.rituj.learnkafka.enums.EventCreationType;
import com.rituj.learnkafka.enums.LibraryEventType;
import com.rituj.learnkafka.services.LibraryEventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@AllArgsConstructor
public class LibraryEventController {

    private LibraryEventService libraryEventService;

    @PostMapping("/v1/library-event/{typeOfEventCreation}")
    public ResponseEntity<LibraryEvent>
    postLibraryEvent(@RequestBody LibraryEvent libraryEvent,
                     @PathVariable final EventCreationType typeOfEventCreation)
            throws JsonProcessingException, ExecutionException, InterruptedException {

        libraryEvent.setLibraryEventType(LibraryEventType.NEW);
        log.info("Calling post api to post the data to the kafka stream");
        switch (typeOfEventCreation) {
            case SEND_DEFAULT_ASYNC:
                log.info("Sending Message to the producer using send default mechanism");
                libraryEventService.postLibraryEvent(libraryEvent);
                break;
            case SEND_SYNC:
                log.info("Sending Message to the producer using send sync mechanism");
                libraryEventService.postLibraryEventSync(libraryEvent);
                break;
            case SEND:
                log.info("Sending Message to the producer using send when topic name is passed");
                libraryEventService.postLibraryEventUsingSend(libraryEvent);
                break;
            case SEND_WITH_PRODUCER_RECORD:
                log.info("Sending Message to the producer using send when producer record" +
                        "is created and passed.");
                libraryEventService.postLibraryEventUsingSendWithProducerRecord(libraryEvent);
                break;
            default:
                log.warn("");
                throw new IllegalStateException("Unexpected value: " + typeOfEventCreation);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

}
