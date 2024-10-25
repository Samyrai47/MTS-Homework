package org.example;

import java.util.Map;
import java.util.Objects;

/**
 * Класс, представляющий DTO. Передается пользователем.
 *
 * @author Samyrai47
 */
public class Message {
  protected Map<String, String> content;
  protected EnrichmentType enrichmentType;

  public Message(Map<String, String> content, EnrichmentType enrichmentType) {
    this.content = content;
    this.enrichmentType = enrichmentType;
  }

  public enum EnrichmentType {
    MSISDN;
  }

  /**
   * Перезаписанный метод сравнения. Требуется для тестов.
   *
   * @param o объект для сравнения с экземпляром класса.
   * @return результат проверки объектов на идентичность.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Message message)) return false;
    return Objects.equals(this.content, message.content);
  }
}
