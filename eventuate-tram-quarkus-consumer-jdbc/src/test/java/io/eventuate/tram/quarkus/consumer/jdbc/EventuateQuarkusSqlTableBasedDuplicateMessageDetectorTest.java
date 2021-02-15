package io.eventuate.tram.quarkus.consumer.jdbc;

import io.eventuate.common.quarkus.jdbc.test.configuration.TestProfileResolver;
import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@QuarkusTest
@TestProfile(TestProfileResolver.class)
public class EventuateQuarkusSqlTableBasedDuplicateMessageDetectorTest {

  @Inject
  DuplicateMessageDetector duplicateMessageDetector;

  @Test
  public void shouldDetectDuplicate() {
    String consumerId = getClass().getName();
    String messageId = Long.toString(System.currentTimeMillis());

    assertFalse(duplicateMessageDetector.isDuplicate(consumerId, messageId));
    assertTrue(duplicateMessageDetector.isDuplicate(consumerId, messageId));
  }
}