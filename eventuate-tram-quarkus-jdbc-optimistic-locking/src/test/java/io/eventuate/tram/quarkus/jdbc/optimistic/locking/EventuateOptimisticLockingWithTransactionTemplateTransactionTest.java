package io.eventuate.tram.quarkus.jdbc.optimistic.locking;

import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;

@QuarkusTest
public class EventuateOptimisticLockingWithTransactionTemplateTransactionTest extends AbstractEventuateOptimisticLockingTest {

  @Inject
  TestEntityServiceTransactionTemplate testEntityService;

  @Override
  protected AbstractTestEntityService testEntityService() {
    return testEntityService;
  }
}