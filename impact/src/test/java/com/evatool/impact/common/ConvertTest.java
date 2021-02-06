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
}
