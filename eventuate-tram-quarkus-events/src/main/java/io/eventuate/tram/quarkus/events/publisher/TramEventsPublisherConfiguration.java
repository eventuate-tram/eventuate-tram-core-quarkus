package io.eventuate.tram.quarkus.events.publisher;

import io.eventuate.tram.events.common.DomainEventNameMapping;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisherImpl;
import io.eventuate.tram.messaging.producer.MessageProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class TramEventsPublisherConfiguration {

  @Produces
  public DomainEventPublisher domainEventPublisher(MessageProducer messageProducer, DomainEventNameMapping domainEventNameMapping) {
    return new DomainEventPublisherImpl(messageProducer, domainEventNameMapping);
  }
}
