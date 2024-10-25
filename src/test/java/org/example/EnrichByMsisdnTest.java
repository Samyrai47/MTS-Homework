package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnrichByMsisdnTest {

  @Test
  void testShouldEnrichMessage() {
    EnrichByMsisdn enriching = new EnrichByMsisdn();
    Message message = new Message(new HashMap(Map.of("msisdn", "1234567890")), Message.EnrichmentType.MSISDN);
    Message messageForComparison = new Message(new HashMap(Map.of("firstName", "Boris", "lastName", "Demidovich", "msisdn", "1234567890")), Message.EnrichmentType.MSISDN);
    enriching.enrich(message);
    assertEquals(message, messageForComparison);
  }

  @Test
  void testShouldReturnUntouchedMessage() {
    EnrichByMsisdn enriching = new EnrichByMsisdn();
    Message message = new Message(new HashMap(Map.of("firstName", "Boris")), Message.EnrichmentType.MSISDN);
    Message messageForComparison = new Message(new HashMap(Map.of("firstName", "Boris")), Message.EnrichmentType.MSISDN);
    enriching.enrich(message);
    assertEquals(message, messageForComparison);
  }
}
