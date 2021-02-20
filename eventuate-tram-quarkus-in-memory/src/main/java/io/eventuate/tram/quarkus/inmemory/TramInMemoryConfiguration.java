package io.eventuate.tram.quarkus.inmemory;

import io.eventuate.common.inmemorydatabase.EventuateDatabaseScriptSupplier;
import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.eventuate.tram.consumer.common.NoopDuplicateMessageDetector;
import io.eventuate.tram.inmemory.EventuateTransactionSynchronizationManager;
import io.eventuate.tram.inmemory.InMemoryMessageConsumer;
import io.eventuate.tram.inmemory.InMemoryMessageProducer;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class TramInMemoryConfiguration {

  @Singleton
  public InMemoryMessageConsumer inMemoryMessageConsumer() {
    return new InMemoryMessageConsumer();
  }

  @Singleton
  public InMemoryMessageProducer inMemoryMessageProducer(InMemoryMessageConsumer messageConsumer,
                                                         EventuateTransactionSynchronizationManager eventuateTransactionSynchronizationManager) {
    return new InMemoryMessageProducer(messageConsumer, eventuateTransactionSynchronizationManager);
  }

  @Singleton
  @Named("TramEventuateDatabaseScriptSupplier")
  public EventuateDatabaseScriptSupplier eventuateCommonInMemoryScriptSupplierForTram() {
    return () -> Collections.singletonList("eventuate-tram-embedded-schema.sql");
  }
}
