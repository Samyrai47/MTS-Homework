package org.example;

/**
 * Интерфейс, хранящий методы для различных видов обогащения.
 *
 * @author Samyrai47
 */
public interface Enriching {
  /**
   * @return тип параметра, на основе которого выполняется обогащение.
   */
  Message.EnrichmentType type();

  /**
   * Непосредственно метод обогащения сообщения.
   *
   * @param message сообщение, которое нужно обогатить.
   * @return обогащенное сообщение.
   */
  Message enrich(Message message);
}
