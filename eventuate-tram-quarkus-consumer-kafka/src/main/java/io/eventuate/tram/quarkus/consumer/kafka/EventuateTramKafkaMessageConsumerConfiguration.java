package io.eventuate.tram.quarkus.consumer.kafka;

import io.eventuate.messaging.kafka.consumer.MessageConsumerKafkaImpl;
import io.eventuate.tram.consumer.common.MessageConsumerImplementation;
import io.eventuate.tram.consumer.kafka.EventuateTramKafkaMessageConsumer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class EventuateTramKafkaMessageConsumerConfiguration {
  @Produces
  public MessageConsumerImplementation messageConsumerImplementation(MessageConsumerKafkaImpl messageConsumerKafka) {
    return new EventuateTramKafkaMessageConsumer(messageConsumerKafka);
  }
}
