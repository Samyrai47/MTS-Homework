package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 25; i++) {
      list.add(i);
    }
    Sorts sorts = new Sorts(List.of(new BubbleSort(), new MergeSort()));
    List<Integer> t1 = sorts.sort(list, SortTypes.MERGE_SORT);
    System.out.println(t1);
  }
}
