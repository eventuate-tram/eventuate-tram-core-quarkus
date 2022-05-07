package io.eventuate.tram.quarkus.commands.consumer;

import io.eventuate.tram.commands.common.CommandNameMapping;
import io.eventuate.tram.commands.consumer.CommandDispatcherFactory;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.messaging.producer.MessageProducer;

import javax.enterprise.inject.Instance;
import javax.inject.Singleton;

@Singleton
public class TramCommandConsumerConfiguration {
  @Singleton
  public CommandDispatcherFactory commandDispatcherFactory(Instance<MessageConsumer> messageConsumer,
                                                           Instance<MessageProducer> messageProducer,
                                                           Instance<CommandNameMapping> commandNameMapping) {
    return new CommandDispatcherFactory(messageConsumer.get(), messageProducer.get(), commandNameMapping.get());
  }
}
