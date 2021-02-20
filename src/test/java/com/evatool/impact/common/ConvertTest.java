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
        var converted = Convert.iterableToList(list);

        // then
        assertThat(converted.size()).isEqualTo(list.size());
        assertThat(converted).isEqualTo(list);
    }
}
