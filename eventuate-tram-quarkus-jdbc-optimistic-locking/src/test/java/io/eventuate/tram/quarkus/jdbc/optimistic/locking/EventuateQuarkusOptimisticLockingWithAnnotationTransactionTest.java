package io.eventuate.tram.quarkus.jdbc.optimistic.locking;

import io.eventuate.tram.jdbc.optimistic.locking.common.test.AbstractTestEntityService;
import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;

@QuarkusTest
public class EventuateQuarkusOptimisticLockingWithAnnotationTransactionTest extends AbstractEventuateQuarkusOptimisticLockingTest {


  @Inject
  TestEntityServiceTransactionAnnotation testEntityService;


  @Override
  protected AbstractTestEntityService testEntityService() {
    return testEntityService;
  }
}