package io.eventuate.tram.quarkus.events.subscriber;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class DomainEventDispatcherInitializer {
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Inject
  Instance<DomainEventDispatcher> domainEventDispatchers;

  @PostConstruct
  public void init() {
    logger.info("Initializing domain event dispatchers");
    domainEventDispatchers.stream().forEach(DomainEventDispatcher::initialize);
    logger.info("Initialized domain event dispatchers");
  }
}
