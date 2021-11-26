package com.rituj.learnkafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public record BookRecord(Integer bookId, String bookName, String bookAuthor){
}
