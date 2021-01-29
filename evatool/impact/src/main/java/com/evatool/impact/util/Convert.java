package com.evatool.impact.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Convert {
    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        var list = new ArrayList<T>();

        for (var iterableItem : iterable) {
            list.add(iterableItem);
        }

        return list;
    }

    public static <T> List<T> allElements(List<T> list, Function<T, T> func) {
        var newList = new ArrayList<T>();

        for (var listItem : list) {
            newList.add(func.apply(listItem));
        }

        return newList;
    }
}
