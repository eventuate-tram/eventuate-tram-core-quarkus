package io.eventuate.tram.quarkus.events.subscriber;

import io.eventuate.tram.events.common.DomainEventNameMapping;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.messaging.consumer.MessageConsumer;

import javax.inject.Singleton;

@Singleton
public class TramEventSubscriberConfiguration {

  @Singleton
  public DomainEventDispatcherFactory domainEventDispatcherFactory(MessageConsumer messageConsumer, DomainEventNameMapping domainEventNameMapping) {
    return new DomainEventDispatcherFactory(messageConsumer, domainEventNameMapping);
  }
}
