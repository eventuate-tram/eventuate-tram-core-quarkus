package io.eventuate.tram.quarkus.inmemory;

import io.eventuate.tram.consumer.common.MessageConsumerImplementation;
import io.eventuate.tram.inmemory.InMemoryMessageConsumer;
import io.eventuate.tram.inmemory.InMemoryMessageProducer;
import io.eventuate.tram.messaging.producer.common.MessageProducerImplementation;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

@ApplicationScoped
@Alternative
@Priority(0)
public class TramInMemoryMessagingConfiguration {

  @Singleton
  public MessageConsumerImplementation messageConsumerImplementation(InMemoryMessageConsumer inMemoryMessageConsumer) {
    return inMemoryMessageConsumer;
  }

  @Singleton
  public MessageProducerImplementation messageProducerImplementation(InMemoryMessageProducer inMemoryMessageProducer) {
    return inMemoryMessageProducer;
  }
}
