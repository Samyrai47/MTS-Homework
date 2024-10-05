import java.util.Arrays;

/**
 * Тип данных собственного производства. В качестве референса используется ArrayList
 *
 * @author Samyrai47
 */
public class CustomList<A> implements Methods<A> {
  /** Хранит список элементов*/
  private Object[] list;

  /** Хранит указание на свободный индекс */
  private int capacity;

  /**
   * Создает CustomList
   *
   * @param data параметризованный список
   * @throws RuntimeException если хотя бы один элемент списка равен null
   */
  public CustomList(Object[] data) {
    boolean is_null = false;
    for (Object el : data) {
      if (el == null) {
        is_null = true;
        break;
      }
    }
    if (is_null) {
      throw new RuntimeException("Arguments can't be null");
    }
    this.list = data;
    this.capacity = data.length;
  }

  /**
   * Выводит в одну строку CustomList в консоли. Метод не указан в задании, добавлен для личного
   * удобства
   */
  public void print() {
    for (int i = 0; i < capacity - 1; i++) {
      System.out.print(list[i] + " ");
    }
    System.out.println(list[capacity - 1]);
  }

  /**
   * Добавляет элемент в CustomList. При превышении заданного размера динамически расширяет
   * структуру данных. Изменяет указатель на свободный индекс
   *
   * @param element передаваемый элемент generic типа
   * @throws RuntimeException если переданный аргумент равен null, ошибка отлавливается, и выводится
   *     сообщение в консоль
   */
  @Override
  public void add(A element) {
    try {
      if (element == null) {
        throw new RuntimeException();
      }
      if (list.length == capacity) {
        list = Arrays.copyOf(list, (capacity + 1) * 2);
        list[capacity] = element;
        capacity++;

      } else {
        list[capacity] = element;
        capacity++;
      }
    } catch (RuntimeException e) {
      System.out.println("Argument can't be null");
    }
  }

  /**
   * Выводит элемент в консоль по индексу. В случае передачи индекса превышающего количество
   * действительных элементов выводит ошибку в консоль
   *
   * @param index индекс требуемого элемента
   */
  @Override
  public void get(int index) {
    try {
      System.out.println(list[index]);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Index out of bounds");
    }
  }

  /**
   * Удаляет элемент из списка по индексу. Сдвигает элементы влево для избежания null между
   * элементами в списке. Изменяет указатель на свободный индекс
   *
   * @param index индекс требуемого элемента
   * @throws ArrayIndexOutOfBoundsException если введенный индекс превышает количество
   *     действительных элементов
   */
  @Override
  public void remove(int index) {
    try {
      if (index >= capacity) {
        throw new ArrayIndexOutOfBoundsException();
      }
      if (index + 1 != capacity) {
        for (int i = index + 1; i < capacity; i++) {
          list[i - 1] = list[i];
        }
      }
      list[capacity - 1] = null;
      capacity--;
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Index out of bounds");
    }
  }
}
