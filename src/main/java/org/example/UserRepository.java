package org.example;

/**
 * Интерфейс, хранящий методы, требуемые для листа с пользователями.
 *
 * @author Samyrai47
 */
public interface UserRepository {
  /**
   * Ищет пользователя по номеру телефона.
   *
   * @param msisdn номер телефона пользователя.
   * @return пользователя с данным номером телефона.
   */
  User findByMsisdn(String msisdn);

  /**
   * Обновляет данные пользователя.
   *
   * @param msisdn номер телефона пользователя.
   * @param user объект, хранящий данные о пользователе для записи.
   */
  void updateUserByMsisdn(String msisdn, User user);
}
