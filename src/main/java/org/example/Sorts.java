package org.example;

import java.util.List;

/**
 * Класс сортировок. Через него вызываются все алгоритмы для сортировки.
 *
 * @author Samyrai47
 */
class Sorts {
  /** Список алгоритмов сортировок. */
  private final List<Sorting> sorts;

  /**
   * @param sorts подаваемый на вход список алгоритмов.
   */
  public Sorts(List<Sorting> sorts) {
    this.sorts = sorts;
  }

  /**
   * Выполняет сортировку на основе переданного листа и типа алгоритма. Перебирает алгоритмы в
   * поиске тех, чей тип совпадает с переданным. В случае успеха выводит результат алгоритма
   * сортировки. В противном случае выдает ошибку.
   *
   * @param list передаваемый на вход список для сортировки.
   * @param sortType желаемый тип сортировки.
   * @return отсортированную копию массива.
   * @throws RuntimeException если не был найден подходящий алгоритм.
   */
  public List<Integer> sort(List<Integer> list, SortTypes sortType) {
    for (Sorting sorting : sorts) {
      if (sorting.type().equals(sortType)) {
        try {
          return sorting.sort(list);
        } catch (ListSizeException e) {
          System.out.println("Not suitable sorting type. Trying next one.");
        }
      }
    }
    throw new RuntimeException("No suitable sort.");
  }
}
