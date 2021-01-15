package io.eventuate.tram.quarkus.events.common;

import io.eventuate.tram.events.common.DefaultDomainEventNameMapping;
import io.eventuate.tram.events.common.DomainEventNameMapping;
import io.quarkus.arc.DefaultBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class TramEventsCommonConfiguration {

  @Produces
  @DefaultBean
  public DomainEventNameMapping domainEventNameMapping() {
    return new DefaultDomainEventNameMapping();
  }
}
