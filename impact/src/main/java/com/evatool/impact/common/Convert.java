package com.evatool.impact.common;

import java.util.ArrayList;
import java.util.List;

public class Convert {

    private Convert() {

    }

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        var list = new ArrayList<T>();

        for (var iterableItem : iterable) {
            list.add(iterableItem);
        }

        return list;
    }
}
