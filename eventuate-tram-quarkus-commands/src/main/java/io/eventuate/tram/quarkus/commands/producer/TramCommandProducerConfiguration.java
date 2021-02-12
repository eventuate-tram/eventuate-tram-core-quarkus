package io.eventuate.tram.quarkus.commands.producer;

import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.commands.producer.CommandProducerImpl;
import io.eventuate.tram.messaging.producer.MessageProducer;

import javax.enterprise.inject.Instance;
import javax.inject.Singleton;

@Singleton
public class TramCommandProducerConfiguration {
  @Singleton
  public CommandProducer commandProducer(Instance<MessageProducer> messageProducer) {
    return new CommandProducerImpl(messageProducer.get());
  }
}
