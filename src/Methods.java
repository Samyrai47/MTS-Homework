/**
 * Интерфейс с методами для CustomClass. Функионал и применение расписаны в CustomList
 *
 * @param <A> для использования generic типа в add()
 * @author Samyrai47
 */
public interface Methods<A> {
  void add(A element);

  void get(int index);

  void remove(int index);
}
