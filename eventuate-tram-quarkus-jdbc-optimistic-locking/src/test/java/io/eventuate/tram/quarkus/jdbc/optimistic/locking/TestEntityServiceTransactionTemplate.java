package io.eventuate.tram.quarkus.jdbc.optimistic.locking;

import io.eventuate.common.jdbc.EventuateTransactionTemplate;
import io.eventuate.tram.jdbc.optimistic.locking.common.test.AbstractTestEntityService;
import io.eventuate.tram.jdbc.optimistic.locking.common.test.TestEntityRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestEntityServiceTransactionTemplate extends AbstractTestEntityService {

  @Inject
  TestEntityRepository testEntityRepository;

  @Inject
  EventuateTransactionTemplate eventuateTransactionalTemplate;

  @Override
  public Long createTestEntityInTransaction() {
    return eventuateTransactionalTemplate.executeInTransaction(this::createTestEntity);
  }

  @Override
  public void incDataInTransaction(Long entityId) {
    eventuateTransactionalTemplate.executeInTransaction(() -> {
      incData(entityId);
      return null;
    });
  }

  @Override
  public long getDataInTransaction(Long entityId) {
    return eventuateTransactionalTemplate.executeInTransaction(() -> {
      return getData(entityId);
    });
  }

  @Override
  public TestEntityRepository getTestEntityRepository() {
    return testEntityRepository;
  }
}
