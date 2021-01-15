package io.eventuate.tram.quarkus.inmemory;

import io.eventuate.common.inmemorydatabase.EventuateDatabaseScriptSupplier;
import io.eventuate.tram.inmemory.InMemoryMessageConsumer;
import io.eventuate.tram.inmemory.InMemoryMessageProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Collections;

@ApplicationScoped
public class TramInMemoryConfiguration {

  @Singleton
  public InMemoryMessageConsumer inMemoryMessageConsumer() {
    return new InMemoryMessageConsumer();
  }

  @Singleton
  public InMemoryMessageProducer inMemoryMessageProducer(InMemoryMessageConsumer messageConsumer) {
    return new InMemoryMessageProducer(messageConsumer);
  }

  @Singleton
  @Named("TramEventuateDatabaseScriptSupplier")
  public EventuateDatabaseScriptSupplier eventuateCommonInMemoryScriptSupplierForTram() {
    return () -> Collections.singletonList("eventuate-tram-embedded-schema.sql");
  }
}
