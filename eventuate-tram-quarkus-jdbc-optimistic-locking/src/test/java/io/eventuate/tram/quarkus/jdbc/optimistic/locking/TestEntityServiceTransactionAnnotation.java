package io.eventuate.tram.quarkus.jdbc.optimistic.locking;

import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class TestEntityServiceTransactionAnnotation extends AbstractTestEntityService {


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
}
