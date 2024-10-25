package org.example;

/**
 * Кастомная ошибка, срабатывает если в метод обогащения или сохранения в лист передан null.
 *
 * @author Samyrai47
 */
public class NullParameterException extends RuntimeException {
  public NullParameterException(String s) {
    super();
  }
}
