package com.evatool.impact.application.controller.util;

import static com.evatool.impact.application.controller.util.RestSetting.*;

public class DimensionRest {
    public static final String DIMENSION_REST_CONTROLLER_MAPPING = BASE_URI;

    public static final String DIMENSIONS = "dimensions";

    public static final String GET_DIMENSION_MAPPING = "/dimensions/{id}";
    public static final String GET_DIMENSIONS_MAPPING = "/dimensions";
    public static final String POST_DIMENSION_MAPPING = "/dimensions";
    public static final String PUT_DIMENSION_MAPPING = "/dimensions/{id}"; // DIMENSIONS_SLASH_ID
    public static final String DELETE_DIMENSION_MAPPING = "/dimensions/{id}";

    public static String buildGetDimensionUri(String id) {
        return DIMENSION_REST_CONTROLLER_MAPPING + GET_DIMENSION_MAPPING.replace("{id}", id);
    }

    public static String buildGetDimensionsUri() {
        return DIMENSION_REST_CONTROLLER_MAPPING + GET_DIMENSIONS_MAPPING;
    }

    public static String buildPostDimensionUri() {
        return DIMENSION_REST_CONTROLLER_MAPPING + POST_DIMENSION_MAPPING;
    }

    public static String buildPutDimensionUri(String id) {
        return DIMENSION_REST_CONTROLLER_MAPPING + PUT_DIMENSION_MAPPING.replace("{id}", id);
    }

    public static String buildDeleteDimensionUri(String id) {
        return DIMENSION_REST_CONTROLLER_MAPPING + DELETE_DIMENSION_MAPPING.replace("{id}", id);
    }

    public static String buildGetDimensionRel() {
        return GET_REL_PREFIX + " " + DIMENSIONS;
    }

    public static String buildGetDimensionsRel() {
        return GET_ALL_REL_PREFIX + " " + DIMENSIONS;
    }

    public static String buildPostDimensionRel() {
        return POST_REL_PREFIX + " " + DIMENSIONS;
    }

    public static String buildPutDimensionRel() {
        return PUT_REL_PREFIX + " " + DIMENSIONS;
    }

    public static String buildDeleteDimensionRel() {
        return DELETE_REL_PREFIX + " " + DIMENSIONS;
    }
}
