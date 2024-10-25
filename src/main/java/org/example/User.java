package org.example;

import java.util.Map;
import java.util.Objects;

/**
 * Представление пользователя. Хранит данные, переданные в DTO.
 *
 * @author Samyrai47
 */
public class User {
  protected Map<String, String> info;

  /**
   * Конструктор класса.
   *
   * @param userInfo подаваемый на вход объект Map. Берется из Message.
   */
  public User(Map<String, String> userInfo) {
    this.info = userInfo;
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
    if (!(o instanceof User user)) return false;
    return Objects.equals(info, user.info);
  }
}
