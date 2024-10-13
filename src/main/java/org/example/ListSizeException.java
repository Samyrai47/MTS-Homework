package org.example;

/**
 * Кастомная ошибка для огрничения размера листа, принимаемого на вход алгоритмом сортировки.
 *
 * @author Samyrai47
 */
public class ListSizeException extends RuntimeException {
  public ListSizeException(String message) {
    super(message);
  }
}
