package io.eventuate.tram.quarkus.consumer.common;

import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.eventuate.tram.consumer.common.NoopDuplicateMessageDetector;
import io.quarkus.arc.DefaultBean;

import javax.inject.Singleton;

@Singleton
public class TramNoopDuplicateMessageDetectorConfiguration {

  @Singleton
  @DefaultBean
  public DuplicateMessageDetector duplicateMessageDetector() {
    return new NoopDuplicateMessageDetector();
  }
}
