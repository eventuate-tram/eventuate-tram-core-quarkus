package io.eventuate.tram.quarkus.events.subscriber;

import io.eventuate.tram.events.common.DomainEventNameMapping;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.messaging.consumer.MessageConsumer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class TramEventSubscriberConfiguration {

  @Produces
  public DomainEventDispatcherFactory domainEventDispatcherFactory(MessageConsumer messageConsumer, DomainEventNameMapping domainEventNameMapping) {
    return new DomainEventDispatcherFactory(messageConsumer, domainEventNameMapping);
  }
}
