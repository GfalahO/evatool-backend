package com.evatool.impact.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Convert {

    private static final Logger logger = LoggerFactory.getLogger(Convert.class);

    private Convert() {

    }

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        logger.debug("Converting iterable to list");
        var list = new ArrayList<T>();
        iterable.forEach(list::add);
        return list;
    }
}
