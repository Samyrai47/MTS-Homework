package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortsTest {

    @Test
    void testExpectedExceptionIfSortsListIsEmpty() {
        List<Integer> unsortedList = new ArrayList<>(Arrays.asList(5, 2, 53, 12, 678, 1, 32));
        Sorts sorts = new Sorts(List.of());
        assertThrows(RuntimeException.class, () -> sorts.sort(unsortedList, SortTypes.BUBBLE_SORT));
    }
}