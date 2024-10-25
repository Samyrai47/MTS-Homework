package org.example;

import java.util.List;

/**
 * Класс, в котором реализованы методы по обогащению сообщения в зависимости от переданного
 * параметра. Поддерживает расширение списка параметров, по которым происходит обогащение
 * (Open-Closed Principle).
 *
 * @author Samyrai47
 */
public class EnrichmentService {

  private final List<Enriching> enrichments;

  /**
   * Сохраняет список параметров для обогащения сообщения.
   *
   * @param enrichments список параметров, по которым осуществляется обогащение.
   */
  public EnrichmentService(List<Enriching> enrichments) {
    this.enrichments = enrichments;
  }

  /**
   * Обогащает сообщение на основе переданного аргумента. При совпадении переданного аргумента с
   * аргументом из списка, вызывает нужный метод обогащения. Поддерживает многопоточный режим.
   *
   * @param message сообщение, которое нужно обогатить
   * @return обогащенное сообщение, если подходящий метод был найден, в противном случае вернет
   *     неизмененное сообщение.
   * @throws NullParameterException если в метод передан null.
   */
  public synchronized Message enrich(Message message) {
    if (message == null) {
      throw new NullParameterException("Null can`t be enriched.");
    }
    for (Enriching enrichment : this.enrichments) {
      if (enrichment.type().equals(message.enrichmentType)) {
        enrichment.enrich(message);
        return message;
      }
    }
    return message;
  }
}
