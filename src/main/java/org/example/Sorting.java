package org.example;

import java.util.List;

/**
 * Интерфейс для всех классов сортировок.
 *
 * @author Samyrai47
 */
interface Sorting {
  /**
   * @return тип алгоритма сортировки
   */
  SortTypes type();

  /**
   * Вызов алгоритма сортировки. Сам алгоритм определяется классом.
   *
   * @param list подаваемый на вход лист для сортировки.
   * @return отсортированный лист (копию).
   */
  List<Integer> sort(List<Integer> list);
}
