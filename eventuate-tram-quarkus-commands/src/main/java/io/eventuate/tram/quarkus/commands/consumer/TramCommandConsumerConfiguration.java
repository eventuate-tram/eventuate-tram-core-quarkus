package io.eventuate.tram.quarkus.commands.consumer;

import io.eventuate.tram.commands.consumer.CommandDispatcherFactory;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.messaging.producer.MessageProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class TramCommandConsumerConfiguration {
  @Produces
  public CommandDispatcherFactory commandDispatcherFactory(MessageConsumer messageConsumer, MessageProducer messageProducer) {
    return new CommandDispatcherFactory(messageConsumer, messageProducer);
  }
}
