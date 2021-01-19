package io.eventuate.tram.quarkus.events.common;

import io.eventuate.tram.events.common.DefaultDomainEventNameMapping;
import io.eventuate.tram.events.common.DomainEventNameMapping;
import io.quarkus.arc.DefaultBean;

import javax.inject.Singleton;

@Singleton
public class TramEventsCommonConfiguration {

  @Singleton
  @DefaultBean
  public DomainEventNameMapping domainEventNameMapping() {
    return new DefaultDomainEventNameMapping();
  }
}
