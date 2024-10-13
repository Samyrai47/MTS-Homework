package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Сортировка пузырьком. Имплементируется от интерфейса Sorting.
 *
 * @author Samyrai47
 */
class BubbleSort implements Sorting {

  /**
   * @return тип алгоритма сортировки.
   */
  @Override
  public SortTypes type() {
    return SortTypes.BUBBLE_SORT;
  }

  /**
   * Непосредственно алгоритм сортировки пузырьком.
   *
   * @param inputList подаваемый на вход лист для сортировки.
   * @throws ListSizeException если размер листа превышает 20 элементов.
   * @throws IllegalArgumentException если null встречается в листе.
   * @return отсортированную копию inputList.
   */
  @Override
  public List<Integer> sort(List<Integer> inputList) {
    try {
      List<Integer> list = new ArrayList<>(List.copyOf(inputList));
      if (list.size() > 20) {
        throw new ListSizeException("List is too large for BubbleSort");
      }
      for (int i = 0; i < list.size(); i++) {
        for (int j = i + 1; j < list.size(); j++) {
          if (list.get(i) > list.get(j)) {
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
          }
        }
      }
      return list;
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Null argument in list for BubbleSort");
    }
  }
}
