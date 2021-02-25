package io.eventuate.tram.quarkus.consumer.jdbc;

import io.eventuate.common.jdbc.EventuateTransactionTemplate;
import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.eventuate.tram.consumer.jdbc.TransactionalNoopDuplicateMessageDetector;
import io.quarkus.arc.properties.IfBuildProperty;

import javax.inject.Singleton;

@Singleton
public class TransactionalNoopDuplicateMessageDetectorConfiguration {

  @Singleton
  @IfBuildProperty(name = "transactional.noop.duplicate.message.detector.configuration.enabled", stringValue = "true")
  public DuplicateMessageDetector duplicateMessageDetector(EventuateTransactionTemplate eventuateTransactionTemplate) {
    return new TransactionalNoopDuplicateMessageDetector(eventuateTransactionTemplate);
  }
}
