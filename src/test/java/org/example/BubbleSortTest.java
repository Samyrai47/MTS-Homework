package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class BubbleSortTest {

    @Test
    void testBubbleSort() {
        List<Integer> unsortedList = new ArrayList<>(Arrays.asList(5, 2, 53, 12, 678, 1, 32));
        List<Integer> sortedList = new ArrayList<>(Arrays.asList(1, 2, 5, 12, 32, 53, 678));
        BubbleSort sort = new BubbleSort();

        assertEquals(sortedList, sort.sort(unsortedList));
    }

    @Test
    void testExpectedExceptionWithLargeListBubbleSort() {
        List<Integer> largeUnsortedList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22));
        BubbleSort sort = new BubbleSort();

        RuntimeException thrown = assertThrows(ListSizeException.class, () -> sort.sort(largeUnsortedList), "ListSizeException about list size was expected");
        assertEquals("List is too large for BubbleSort", thrown.getMessage());
    }

    @Test
    void testExpectedExceptionWithNullArgumentBubbleSort() {
        List<Integer> list = new ArrayList<>(Arrays.asList(23, 12, 543, null, 34, 32));
        BubbleSort sort = new BubbleSort();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> sort.sort(list), "IllegalArgumentException about null argument was expected");
        assertEquals("Null argument in list for BubbleSort", thrown.getMessage());
    }
}