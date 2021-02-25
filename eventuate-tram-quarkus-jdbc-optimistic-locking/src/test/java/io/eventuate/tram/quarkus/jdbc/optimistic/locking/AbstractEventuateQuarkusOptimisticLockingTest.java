package io.eventuate.tram.quarkus.jdbc.optimistic.locking;

import io.eventuate.tram.consumer.common.MessageHandlerDecorator;
import io.eventuate.tram.jdbc.optimistic.locking.common.test.AbstractEventuateOptimisticLockingTest;

import javax.inject.Inject;

abstract public class AbstractEventuateQuarkusOptimisticLockingTest  extends AbstractEventuateOptimisticLockingTest {

  @Inject
  OptimisticLockingDecorator optimisticLockingDecorator;

  @Override
  public MessageHandlerDecorator getOptimisticLockingDecorator() {
    return optimisticLockingDecorator;
  }
}