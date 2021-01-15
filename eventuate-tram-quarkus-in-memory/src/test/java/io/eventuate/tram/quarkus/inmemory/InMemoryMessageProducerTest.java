package io.eventuate.tram.quarkus.inmemory;

import io.eventuate.common.jdbc.EventuateTransactionTemplate;
import io.eventuate.tram.inmemory.InMemoryMessageConsumer;
import io.eventuate.tram.inmemory.InMemoryMessageProducer;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.consumer.MessageHandler;
import io.eventuate.tram.messaging.producer.MessageBuilder;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@QuarkusTest
public class InMemoryMessageProducerTest {

  private String subscriberId;
  private String destination;
  private String payload;
  private MyMessageHandler mh;

  @Inject
  InMemoryMessageProducer inMemoryMessageProducer;

  @Inject
  InMemoryMessageConsumer inMemoryMessageConsumer;

  @Inject
  EventuateTransactionTemplate eventuateTransactionTemplate;


  class MyMessageHandler implements MessageHandler {

    private BlockingQueue<String> queue = new LinkedBlockingDeque<>();

    @Override
    public void accept(Message message) {
      try {
        queue.put(message.getPayload());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    void shouldReceiveMessage(String payload) {
      String m;
      try {
        while ((m = queue.poll(1, TimeUnit.SECONDS)) != null) {
          if (payload.equals(m))
            return;
        }
        fail("Didn't find message with payload: " + payload);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @BeforeEach
  public void setUp() {
    subscriberId = "subscriberId-" + System.currentTimeMillis();
    destination = "destination-" + System.currentTimeMillis();
    payload = "payload-" + System.currentTimeMillis();
    mh = new MyMessageHandler();
  }

  @Test
  public void shouldDeliverToMatchingSubscribers() {

    inMemoryMessageConsumer.subscribe(subscriberId, Collections.singleton(destination), mh);

    Message m = makeMessage();
    inMemoryMessageProducer.send(m);
    assertNotNull(m.getId());
    mh.shouldReceiveMessage(payload);

  }

  @Test
  public void shouldSetIdWithinTransaction() {
    Message m = makeMessage();
    eventuateTransactionTemplate.executeInTransaction(() -> {
      inMemoryMessageProducer.send(m);
      assertNotNull(m.getId());
      return null;
    });
  }

  @Test
  public void shouldDeliverToWildcardSubscribers() {

    inMemoryMessageConsumer.subscribe(subscriberId, Collections.singleton("*"), mh);

    Message m = makeMessage();

    inMemoryMessageProducer.send(m);

    mh.shouldReceiveMessage(payload);

  }

  private Message makeMessage() {
    return MessageBuilder.withPayload(payload).withHeader(Message.DESTINATION, destination).withHeader(Message.ID, "message-id").build();
  }

}