package com.rituj.learnkafka.domain;

import com.rituj.learnkafka.enums.LibraryEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryEvent implements Serializable {
    private Integer libraryEventId;
    private LibraryEventType libraryEventType;
    private Book book;
}
