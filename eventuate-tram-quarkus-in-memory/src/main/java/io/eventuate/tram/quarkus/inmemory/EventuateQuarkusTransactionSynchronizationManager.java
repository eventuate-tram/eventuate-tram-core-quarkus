package io.eventuate.tram.quarkus.inmemory;

import io.eventuate.tram.inmemory.EventuateTransactionSynchronizationManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

@Singleton
public class EventuateQuarkusTransactionSynchronizationManager implements EventuateTransactionSynchronizationManager {

  @Inject
  TransactionManager transactionManager;

  @Override
  public boolean isTransactionActive() {
    try {
      return transactionManager.getTransaction() != null;
    } catch (SystemException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void executeAfterTransaction(Runnable callback) {
    try {
      transactionManager.getTransaction().registerSynchronization(new Synchronization() {
        @Override
        public void beforeCompletion() {

        }

        @Override
        public void afterCompletion(int status) {
          if (status == Status.STATUS_COMMITTED) {
            callback.run();
          }
        }
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
