package org.example;

/**
 * Кастомная ошибка для класса UserList. Срабатывает, если пользователь с данным номером телефона не найден.
 *
 * @author Samyrai47
 */
public class NoSuchUser extends RuntimeException {
  public NoSuchUser(String s) {
    super();
  }
}
