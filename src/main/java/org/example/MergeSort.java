package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Сортировка слиянием. Имплементируется от интерфейса Sorting.
 *
 * @author Samyrai47
 */
class MergeSort implements Sorting {

  /**
   * @return тип алгоритма сортировки.
   */
  @Override
  public SortTypes type() {
    return SortTypes.MERGE_SORT;
  }

  /**
   * Непосредственно алгортим сортировки слиянием.
   *
   * @param inputList подаваемый на вход лист для сортировки.
   * @throws ListSizeException если размер листа превышает 100 элементов.
   * @throws IllegalArgumentException если null стречается в листе.
   * @return отсортированную копию inputList.
   */
  @Override
  public List<Integer> sort(List<Integer> inputList) {
    try {
      List<Integer> list = new ArrayList<>(List.copyOf(inputList));
      if (list.size() > 100) {
        throw new ListSizeException("List is too large for MergeSort");
      }
      Collections.sort(list);
      return list;
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Null argument in list for MergeSort");
    }
  }
}
