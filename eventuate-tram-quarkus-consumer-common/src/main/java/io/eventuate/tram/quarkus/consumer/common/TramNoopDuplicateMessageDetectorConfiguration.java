package io.eventuate.tram.quarkus.consumer.common;

import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.eventuate.tram.consumer.common.NoopDuplicateMessageDetector;
import io.quarkus.arc.DefaultBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class TramNoopDuplicateMessageDetectorConfiguration {

  @Produces
  @DefaultBean
  public DuplicateMessageDetector duplicateMessageDetector() {
    return new NoopDuplicateMessageDetector();
  }
}
