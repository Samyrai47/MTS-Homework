package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MergeSortTest {

    @Test
    void testMergeSort() {
        List<Integer> unsortedList = new ArrayList<>(Arrays.asList(5, 2, 53, 12, 678, 1, 32));
        List<Integer> sortedList = new ArrayList<>(Arrays.asList(1, 2, 5, 12, 32, 53, 678));
        MergeSort sort = new MergeSort();

        assertEquals(sortedList, sort.sort(unsortedList));
    }

    @Test
    void testExpectedExceptionWithLargeListMergeSort() {
        List<Integer> largeUnsortedList = new ArrayList<>();
        for (int i = 1; i < 105; i++){
            largeUnsortedList.add(i);
        }
        MergeSort sort = new MergeSort();

        RuntimeException thrown = assertThrows(ListSizeException.class, () -> sort.sort(largeUnsortedList), "ListSizeException about list size was expected");
        assertEquals("List is too large for MergeSort", thrown.getMessage());
    }

    @Test
    void testExpectedExceptionWithNullArgumentMergeSort() {
        List<Integer> list = new ArrayList<>(Arrays.asList(23, 12, 543, null, 34, 32));
        MergeSort sort = new MergeSort();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> sort.sort(list), "IllegalArgumentException about null argument was expected");
        assertEquals("Null argument in list for MergeSort", thrown.getMessage());
    }
}
