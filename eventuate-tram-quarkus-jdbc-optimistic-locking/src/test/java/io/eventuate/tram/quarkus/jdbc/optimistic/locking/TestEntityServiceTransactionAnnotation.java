package io.eventuate.tram.quarkus.jdbc.optimistic.locking;

import io.eventuate.tram.jdbc.optimistic.locking.common.test.AbstractTestEntityService;
import io.eventuate.tram.jdbc.optimistic.locking.common.test.TestEntityRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class TestEntityServiceTransactionAnnotation extends AbstractTestEntityService {

  @Inject
  TestEntityRepository testEntityRepository;

  @Transactional
  @Override
  public Long createTestEntityInTransaction() {
    return createTestEntity();
  }

  @Transactional
  @Override
  public void incDataInTransaction(Long entityId) {
    incData(entityId);
  }

  @Transactional
  @Override
  public long getDataInTransaction(Long entityId) {
    return getData(entityId);
  }

  @Override
  public TestEntityRepository getTestEntityRepository() {
    return testEntityRepository;
  }
}
