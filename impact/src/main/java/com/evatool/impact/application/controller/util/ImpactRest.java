package com.evatool.impact.application.controller.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.evatool.impact.application.controller.util.RestSetting.*;

public class ImpactRest {

    private static final Logger logger = LoggerFactory.getLogger(ImpactRest.class);

    public static final String IMPACT_REST_CONTROLLER_MAPPING = BASE_URI;

    public static final String IMPACTS = "impacts";

    public static final String GET_IMPACT_MAPPING = "/impacts/{id}";
    public static final String GET_IMPACTS_MAPPING = "/impacts";
    public static final String POST_IMPACT_MAPPING = "/impacts";
    public static final String PUT_IMPACT_MAPPING = "/impacts/{id}";
    public static final String DELETE_IMPACT_MAPPING = "/impacts/{id}";

    public static String buildGetImpactUri(String id) {
        logger.debug("Build Get Impact Uri");
        return IMPACT_REST_CONTROLLER_MAPPING + GET_IMPACT_MAPPING.replace("{id}", id);
    }

    public static String buildGetImpactsUri() {
        logger.debug("Build Get Impacts Uri");
        return IMPACT_REST_CONTROLLER_MAPPING + GET_IMPACTS_MAPPING;
    }

    public static String buildPostImpactUri() {
        logger.debug("Build Post Impact Uri");
        return IMPACT_REST_CONTROLLER_MAPPING + POST_IMPACT_MAPPING;
    }

    public static String buildPutImpactUri(String id) {
        logger.debug("Build Put Impact Uri");
        return IMPACT_REST_CONTROLLER_MAPPING + PUT_IMPACT_MAPPING.replace("{id}", id);
    }

    public static String buildDeleteImpactUri(String id) {
        logger.debug("Build Delete Impact Uri");
        return IMPACT_REST_CONTROLLER_MAPPING + DELETE_IMPACT_MAPPING.replace("{id}", id);
    }

    public static String buildGetImpactRel() {
        logger.debug("Build Get Impact Rel");
        return GET_REL_PREFIX + " " + IMPACTS;
    }

    public static String buildGetImpactsRel() {
        logger.debug("Build Get Impacts Rel");
        return GET_ALL_REL_PREFIX + " " + IMPACTS;
    }

    public static String buildPostImpactRel() {
        logger.debug("Build Post Impact Rel");
        return POST_REL_PREFIX + " " + IMPACTS;
    }

    public static String buildPutImpactRel() {
        logger.debug("Build Put Impact Rel");
        return PUT_REL_PREFIX + " " + IMPACTS;
    }

    public static String buildDeleteImpactRel() {
        logger.debug("Build Delete Impact Rel");
        return DELETE_REL_PREFIX + " " + IMPACTS;
    }
}
