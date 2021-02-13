package com.evatool.impact.application.controller.util;

import static com.evatool.impact.application.controller.util.RestSetting.*;

public class ImpactRest {

    public static final String IMPACT_REST_CONTROLLER_MAPPING = BASE_URI;

    public static final String IMPACTS = "impacts";

    public static final String GET_IMPACT_MAPPING = "/impacts/{id}";
    public static final String GET_IMPACTS_MAPPING = "/impacts";
    public static final String POST_IMPACT_MAPPING = "/impacts";
    public static final String PUT_IMPACT_MAPPING = "/impacts/{id}";
    public static final String DELETE_IMPACT_MAPPING = "/impacts/{id}";

    public static String buildGetImpactUri(String id) {
        return IMPACT_REST_CONTROLLER_MAPPING + GET_IMPACT_MAPPING.replace("{id}", id);
    }

    public static String buildGetImpactsUri() {
        return IMPACT_REST_CONTROLLER_MAPPING + GET_IMPACTS_MAPPING;
    }

    public static String buildPostImpactUri() {
        return IMPACT_REST_CONTROLLER_MAPPING + POST_IMPACT_MAPPING;
    }

    public static String buildPutImpactUri(String id) {
        return IMPACT_REST_CONTROLLER_MAPPING + PUT_IMPACT_MAPPING.replace("{id}", id);
    }

    public static String buildDeleteImpactUri(String id) {
        return IMPACT_REST_CONTROLLER_MAPPING + DELETE_IMPACT_MAPPING.replace("{id}", id);
    }

    public static String buildGetImpactRel() {
        return GET_REL_PREFIX + " " + IMPACTS;
    }

    public static String buildGetImpactsRel() {
        return GET_ALL_REL_PREFIX + " " + IMPACTS;
    }

    public static String buildPostImpactRel() {
        return POST_REL_PREFIX + " " + IMPACTS;
    }

    public static String buildPutImpactRel() {
        return PUT_REL_PREFIX + " " + IMPACTS;
    }

    public static String buildDeleteImpactRel() {
        return DELETE_REL_PREFIX + " " + IMPACTS;
    }
}
