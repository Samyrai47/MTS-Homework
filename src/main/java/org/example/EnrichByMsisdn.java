package org.example;

/**
 * Класс обогащения сообщения по msisdn (номеру телефона)
 *
 * @author Samyrai47
 */
public class EnrichByMsisdn implements Enriching {

  /**
   * Наследуется от Enriching.
   *
   * @return тип параметра, по которому выполняется обогащение.
   */
  @Override
  public Message.EnrichmentType type() {
    return Message.EnrichmentType.MSISDN;
  }

  /**
   * Наследуется от Enriching. Непосредственно метод обогащения сообщения на основе msisdn.
   *
   * @param message сообщение, которое нужно обогатить.
   * @return обогащенное сообщение.
   */
  @Override
  public Message enrich(Message message) {
    if (message.content.containsKey("msisdn")) {
      message.content.put("firstName", "Boris");
      message.content.put("lastName", "Demidovich");
    }
    return message;
  }
}
