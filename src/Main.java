/**
 * Используется для тестирования и отладки CustomList. Приведены примеры использования созданного
 * типа данных
 *
 * @author Samyrai47
 */
public class Main {
  public static void main(String[] args) {
    String[] list = new String[10];
    for (int i = 0; i < list.length; i++) {
      list[i] = "num" + i;
    }

    Integer[] list2 = new Integer[5];
    for (int i = 0; i < list2.length; i++) {
      list2[i] = i;
    }

    CustomList<String> arrayListStr = new CustomList<>(list);
    CustomList<Integer> arrayListInt = new CustomList<>(list2);

    arrayListInt.add(123);
    arrayListInt.remove(2222);
    arrayListStr.add(null);
    arrayListInt.get(4);

    arrayListInt.print();
    arrayListStr.print();
  }
}
