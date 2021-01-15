package io.eventuate.tram.quarkus.commands.producer;

import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.commands.producer.CommandProducerImpl;
import io.eventuate.tram.messaging.producer.MessageProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class TramCommandProducerConfiguration {
  @Produces
  public CommandProducer commandProducer(MessageProducer messageProducer) {
    return new CommandProducerImpl(messageProducer);
  }
}
