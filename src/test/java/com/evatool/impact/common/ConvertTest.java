package com.evatool.impact.common;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ConvertTest {
    @Test
    void testIterableToList_Iterable_ReturnList() {
        // when
        var list = new ArrayList<>(Arrays.asList("a", "b", "c"));

        // given

        // then
        assertThat(Convert.iterableToList(list).size()).isEqualTo(list.size());
        assertThat(Convert.iterableToList(list)).isEqualTo(list);
    }
}
