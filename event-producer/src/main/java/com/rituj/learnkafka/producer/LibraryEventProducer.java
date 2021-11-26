package com.rituj.learnkafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rituj.learnkafka.domain.LibraryEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
@AllArgsConstructor
public class LibraryEventProducer {

  private KafkaTemplate<Integer, String> kafkaTemplate;
  private ObjectMapper objectMapper;

  final String topic = "library-events";

  public void sendLibraryEvent(final LibraryEvent libraryEvent) throws JsonProcessingException {

    final Integer key = libraryEvent.getLibraryEventId();
    final String value = objectMapper.writeValueAsString(libraryEvent);

    ListenableFuture<SendResult<Integer, String>> futureValue =
        kafkaTemplate.sendDefault(key, value);

    futureValue.addCallback(
        new ListenableFutureCallback<SendResult<Integer, String>>() {
          @Override
          public void onFailure(Throwable ex) {
            handleFailure(key, value, ex);
          }

          @Override
          public void onSuccess(SendResult<Integer, String> result) {
            handleSuccess(key, value, result);
          }
        });
  }

  public SendResult<Integer, String> sendLibraryEventSync(final LibraryEvent libraryEvent)
      throws JsonProcessingException, ExecutionException, InterruptedException {

    final Integer key = libraryEvent.getLibraryEventId();
    final String value = objectMapper.writeValueAsString(libraryEvent);

    SendResult<Integer, String> result = null;
    try {
      result = kafkaTemplate.sendDefault(key, value).get();
    } catch (Exception e) {
      log.error("Error on failure: {}", e.getMessage());
      throw e;
    }
    return result;
  }

  /**
   * Here we are using {@link KafkaTemplate} to send the messages to the topic. We can have
   * explicitly provided the topic and can be sent to n number of topic. using {@link
   * KafkaTemplate#send(String, Object)}.
   */
  public void sendLibraryEventUsingSend(final LibraryEvent libraryEvent)
      throws JsonProcessingException {

    final Integer key = libraryEvent.getLibraryEventId();
    final String value = objectMapper.writeValueAsString(libraryEvent);

    ListenableFuture<SendResult<Integer, String>> futureValue =
        kafkaTemplate.send(topic, key, value);

    futureValue.addCallback(
        new ListenableFutureCallback<SendResult<Integer, String>>() {
          @Override
          public void onFailure(Throwable ex) {
            handleFailure(key, value, ex);
          }

          @Override
          public void onSuccess(SendResult<Integer, String> result) {
            handleSuccess(key, value, result);
          }
        });
  }

  /**
   * Here we are using {@link KafkaTemplate} to send the messages to the topic. We can have
   * explicitly provided the topic and can be sent to n number of topic. using {@link
   * org.apache.kafka.clients.producer.ProducerRecord}
   */
  public void sendLibraryEventUsingSendWithProducerRecord(final LibraryEvent libraryEvent)
      throws JsonProcessingException {

    final Integer key = libraryEvent.getLibraryEventId();
    final String value = objectMapper.writeValueAsString(libraryEvent);

    ProducerRecord<Integer, String> producerRecord = buildProducerRecord(key, value, topic);

    ListenableFuture<SendResult<Integer, String>> futureValue = kafkaTemplate.send(producerRecord);

    futureValue.addCallback(
        new ListenableFutureCallback<SendResult<Integer, String>>() {
          @Override
          public void onFailure(Throwable ex) {
            handleFailure(key, value, ex);
          }

          @Override
          public void onSuccess(SendResult<Integer, String> result) {
            handleSuccess(key, value, result);
          }
        });
  }

  private ProducerRecord<Integer, String> buildProducerRecord(
      Integer key, String value, String topic) {
    return new ProducerRecord<Integer, String>(topic, null, key, value, null);
  }

  private void handleFailure(final Integer key, final String value, final Throwable ex) {
    log.error("Error sending the Message sent for the key: {} and the value is:{}", key, value);
    try {
      throw ex;
    } catch (Throwable throwable) {
      log.error("Error on failure: {}", throwable.getMessage());
    }
  }

  private void handleSuccess(
      final Integer key, final String value, final SendResult<Integer, String> result) {
    log.info(
        "Message sent for the key: {} and the value is:{} and the partition is:{}",
        key,
        value,
        result.getRecordMetadata().partition());
  }
}
