package com.evatool.impact.common;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ConvertTest {
    @Test
    public void testIterableToList_() {
        // when
        var list = new ArrayList<String>();

        // given
        list.addAll(Arrays.asList("a", "b", "c"));

        // then
        assertThat(Convert.iterableToList(list).size()).isEqualTo(list.size());
        assertThat(Convert.iterableToList(list)).isEqualTo(list);
    }

    @Test
    public void testAllElements_NumberList_SquaredList() {
        // given
        var numbers = Arrays.asList(1, 2, 3, 4, 5);

        // when
        var squaredNumbers = Convert.allElements(numbers, i -> i * i);

        // then
        assertThat(squaredNumbers).isEqualTo(Arrays.asList(1, 4, 9, 16, 25));
    }
}
