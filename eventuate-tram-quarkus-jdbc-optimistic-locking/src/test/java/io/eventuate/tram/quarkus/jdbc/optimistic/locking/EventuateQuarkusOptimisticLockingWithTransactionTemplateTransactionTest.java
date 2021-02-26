package io.eventuate.tram.quarkus.jdbc.optimistic.locking;

import io.eventuate.tram.jdbc.optimistic.locking.common.test.AbstractTestEntityService;
import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;

@QuarkusTest
public class EventuateQuarkusOptimisticLockingWithTransactionTemplateTransactionTest extends AbstractEventuateQuarkusOptimisticLockingTest {

  @Inject
  TestEntityServiceTransactionTemplate testEntityService;

  @Override
  protected AbstractTestEntityService testEntityService() {
    return testEntityService;
  }
}