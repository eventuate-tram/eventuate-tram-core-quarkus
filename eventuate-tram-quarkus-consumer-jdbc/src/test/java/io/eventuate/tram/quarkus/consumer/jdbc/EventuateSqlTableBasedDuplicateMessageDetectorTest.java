package io.eventuate.tram.quarkus.consumer.jdbc;

import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.eventuate.tram.consumer.jdbc.SqlTableBasedDuplicateMessageDetector;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

@QuarkusTest
public class EventuateSqlTableBasedDuplicateMessageDetectorTest {

  @Inject
  DuplicateMessageDetector duplicateMessageDetector;

  @Test
  public void shouldDetectDuplicate() {
    assertEquals(duplicateMessageDetector.getClass(), SqlTableBasedDuplicateMessageDetector.class);

    String consumerId = getClass().getName();
    String messageId = Long.toString(System.currentTimeMillis());

    assertFalse(duplicateMessageDetector.isDuplicate(consumerId, messageId));
    assertTrue(duplicateMessageDetector.isDuplicate(consumerId, messageId));
  }
}