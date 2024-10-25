package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс, хранящий данные о пользователях.
 *
 * @author Samyrai47
 */
public class UsersList implements UserRepository {
  /** Лист типа synchronizedList, для использования в многопоточном режиме. */
  protected static List<User> usersList = Collections.synchronizedList(new ArrayList<>());

  /**
   * Находит пользователя по номеру. Адаптирован для многопоточного режима.
   *
   * @param msisdn номер телефона пользователя.
   * @return user, если находит номер телефона в списке. В противном случае возвращает null.
   */
  @Override
  public User findByMsisdn(String msisdn) {
    synchronized (usersList) {
      for (User user : usersList) {
        if (user.info.containsValue(msisdn)) {
          return user;
        }
      }
      return null;
    }
  }

  /**
   * Наследуется от UserRepository. Обновляет данные о пользователе по переданным на вход номеру и
   * объекту User. Адаптирован для многопоточного режима.
   *
   * @param msisdn номер телефона пользователя.
   * @param user пользователь, который будет записан в лист на место старого.
   * @throws NoSuchUser если пользователь с данным номером не был найден.
   */
  @Override
  public void updateUserByMsisdn(String msisdn, User user) {
    synchronized (usersList) {
      User searchResult = findByMsisdn(msisdn);
      if (searchResult != null) {
        searchResult = user;
      } else {
        throw new NoSuchUser("No user with this msisdn.");
      }
    }
  }

  /**
   * Добавляет пользователей в лист.
   *
   * @throws NullParameterException если в метод передан null.
   * @param user пользователь, который должен быть добавлен в лист.
   */
  public void addUser(User user) {
    if (user == null) {
      throw new NullParameterException("Null can`t be added to list.");
    }
    usersList.add(user);
  }
}
