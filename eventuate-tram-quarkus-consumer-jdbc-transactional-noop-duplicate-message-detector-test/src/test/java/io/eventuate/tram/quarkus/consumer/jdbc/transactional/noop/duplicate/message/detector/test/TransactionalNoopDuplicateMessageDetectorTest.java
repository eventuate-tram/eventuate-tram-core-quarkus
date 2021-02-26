package io.eventuate.tram.quarkus.consumer.jdbc.transactional.noop.duplicate.message.detector.test;

import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.eventuate.tram.consumer.jdbc.TransactionalNoopDuplicateMessageDetector;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@QuarkusTest
public class TransactionalNoopDuplicateMessageDetectorTest {

  @Inject
  DuplicateMessageDetector duplicateMessageDetector;

  @Test
  public void shouldDetectDuplicate() {
    assertEquals(duplicateMessageDetector.getClass(), TransactionalNoopDuplicateMessageDetector.class);
  }
}