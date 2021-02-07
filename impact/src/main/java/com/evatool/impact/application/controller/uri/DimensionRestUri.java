package com.evatool.impact.application.controller.uri;

import com.evatool.impact.common.ModuleSettings;

public class DimensionRestUri {
    public static final String DIMENSION_REST_CONTROLLER_MAPPING = ModuleSettings.BASE_URI;

    public static final String SINGULAR = "dimension";
    public static final String PLURAL = "dimensions";

    public static final String GET_DIMENSION = SINGULAR;
    public static final String GET_DIMENSIONS = PLURAL;
    public static final String POST_DIMENSION = SINGULAR;
    public static final String PUT_DIMENSION = SINGULAR;
    public static final String DELETE_DIMENSION = SINGULAR;

    public static final String GET_DIMENSION_MAPPING = "/" + GET_DIMENSION + "/{id}";
    public static final String GET_DIMENSIONS_MAPPING = "/" + GET_DIMENSIONS;
    public static final String POST_DIMENSION_MAPPING = "/" + POST_DIMENSION;
    public static final String PUT_DIMENSION_MAPPING = "/" + PUT_DIMENSION + "/{id}";
    public static final String DELETE_DIMENSION_MAPPING = "/" + DELETE_DIMENSION + "/{id}";

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
}
