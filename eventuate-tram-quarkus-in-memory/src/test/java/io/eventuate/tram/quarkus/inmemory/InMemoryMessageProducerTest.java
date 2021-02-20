package io.eventuate.tram.quarkus.inmemory;

import io.eventuate.common.jdbc.EventuateTransactionTemplate;
import io.eventuate.tram.inmemory.InMemoryMessageConsumer;
import io.eventuate.tram.inmemory.InMemoryMessageProducer;
import io.eventuate.tram.inmemory.test.AbstractInMemoryMessageProducerTest;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.messaging.consumer.MessageHandler;
import io.eventuate.tram.messaging.producer.MessageBuilder;
import io.eventuate.tram.messaging.producer.MessageProducer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@QuarkusTest
public class InMemoryMessageProducerTest extends AbstractInMemoryMessageProducerTest {
  @Inject
  MessageProducer messageProducer;

  @Inject
  MessageConsumer messageConsumer;

  @Inject
  TransactionManager transactionManager;

  @Override
  protected MessageProducer getMessageProducer() {
    return messageProducer;
  }

  @Override
  protected MessageConsumer getMessageConsumer() {
    return messageConsumer;
  }

  @Override
  protected void executeInTransaction(Consumer<Runnable> consumer) {
    try {
      transactionManager.begin();

      AtomicBoolean rollback = new AtomicBoolean(false);

      consumer.accept(() -> {
          rollback.set(true);
      });

      if (rollback.get()) {
        transactionManager.rollback();
      } else {
        transactionManager.commit();
      }

    } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Override
  @Test
  public void shouldDeliverToMatchingSubscribers() {
    super.shouldDeliverToMatchingSubscribers();
  }

  @Override
  @Test
  public void shouldSetIdWithinTransaction() {
    super.shouldSetIdWithinTransaction();
  }

  @Override
  @Test
  public void shouldDeliverToWildcardSubscribers() {
    super.shouldDeliverToWildcardSubscribers();
  }

  @Override
  @Test
  public void shouldReceiveMessageAfterTransaction() {
    super.shouldReceiveMessageAfterTransaction();
  }

  @Override
  @Test
  public void shouldNotReceiveMessageBeforeTransaction() {
    super.shouldNotReceiveMessageBeforeTransaction();
  }

  @Override
  @Test
  public void shouldNotReceiveMessageAfterTransactionRollback() {
    super.shouldNotReceiveMessageAfterTransactionRollback();
  }
}