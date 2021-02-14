package com.evatool.impact.application.controller.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.evatool.impact.application.controller.util.RestSetting.*;

public class DimensionRest {

    private static final Logger logger = LoggerFactory.getLogger(DimensionRest.class);

    public static final String DIMENSION_REST_CONTROLLER_MAPPING = BASE_URI;

    public static final String DIMENSIONS = "dimensions";

    public static final String GET_DIMENSION_MAPPING = "/dimensions/{id}";
    public static final String GET_DIMENSIONS_MAPPING = "/dimensions";
    public static final String POST_DIMENSION_MAPPING = "/dimensions";
    public static final String PUT_DIMENSION_MAPPING = "/dimensions/{id}";
    public static final String DELETE_DIMENSION_MAPPING = "/dimensions/{id}";

    public static String buildGetDimensionUri(String id) {
        logger.debug("Build Get Dimension Uri");
        return DIMENSION_REST_CONTROLLER_MAPPING + GET_DIMENSION_MAPPING.replace("{id}", id);
    }

    public static String buildGetDimensionsUri() {
        logger.debug("Build Get Dimensions Uri");
        return DIMENSION_REST_CONTROLLER_MAPPING + GET_DIMENSIONS_MAPPING;
    }

    public static String buildPostDimensionUri() {
        logger.debug("Build Post Dimension Uri");
        return DIMENSION_REST_CONTROLLER_MAPPING + POST_DIMENSION_MAPPING;
    }

    public static String buildPutDimensionUri(String id) {
        logger.debug("Build Put Dimension Uri");
        return DIMENSION_REST_CONTROLLER_MAPPING + PUT_DIMENSION_MAPPING.replace("{id}", id);
    }

    public static String buildDeleteDimensionUri(String id) {
        logger.debug("Build Delete Dimension Uri");
        return DIMENSION_REST_CONTROLLER_MAPPING + DELETE_DIMENSION_MAPPING.replace("{id}", id);
    }

    public static String buildGetDimensionRel() {
        logger.debug("Build Get Dimension Rel");
        return GET_REL_PREFIX + " " + DIMENSIONS;
    }

    public static String buildGetDimensionsRel() {
        logger.debug("Build Get Dimensions Rel");
        return GET_ALL_REL_PREFIX + " " + DIMENSIONS;
    }

    public static String buildPostDimensionRel() {
        logger.debug("Build Post Dimension Rel");
        return POST_REL_PREFIX + " " + DIMENSIONS;
    }

    public static String buildPutDimensionRel() {
        logger.debug("Build Put Dimension Rel");
        return PUT_REL_PREFIX + " " + DIMENSIONS;
    }

    public static String buildDeleteDimensionRel() {
        logger.debug("Build Delete Dimension Rel");
        return DELETE_REL_PREFIX + " " + DIMENSIONS;
    }
}
