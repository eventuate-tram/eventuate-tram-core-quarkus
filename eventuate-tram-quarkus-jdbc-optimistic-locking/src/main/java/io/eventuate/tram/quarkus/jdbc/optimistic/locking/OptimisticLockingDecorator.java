package io.eventuate.tram.quarkus.jdbc.optimistic.locking;

import io.eventuate.common.jdbc.EventuateTransactionTemplate;
import io.eventuate.tram.consumer.common.MessageHandlerDecorator;
import io.eventuate.tram.consumer.common.MessageHandlerDecoratorChain;
import io.eventuate.tram.messaging.common.SubscriberIdAndMessage;
import org.eclipse.microprofile.faulttolerance.Retry;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OptimisticLockingDecorator implements MessageHandlerDecorator {

  @Inject
  EventuateTransactionTemplate eventuateTransactionTemplate;

  @Override
  @Retry(retryOn = Exception.class, maxRetries = 10, delay = 100)
  public void accept(SubscriberIdAndMessage subscriberIdAndMessage, MessageHandlerDecoratorChain messageHandlerDecoratorChain) {
    eventuateTransactionTemplate.executeInTransaction(() -> {
      messageHandlerDecoratorChain.invokeNext(subscriberIdAndMessage);
      return null;
    });
  }

  @Override
  public int getOrder() {
    return 150;
  }
}
