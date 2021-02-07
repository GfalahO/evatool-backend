package com.evatool.impact.application.controller.util;

import static com.evatool.impact.application.controller.util.RestSetting.*;

public class ImpactRest {
    public static final String IMPACT_REST_CONTROLLER_MAPPING = BASE_URI;

    public static final String SINGULAR = "impact";
    public static final String PLURAL = "impacts";

    public static final String GET_IMPACT = SINGULAR;
    public static final String GET_IMPACTS = PLURAL;
    public static final String POST_IMPACT = SINGULAR;
    public static final String PUT_IMPACT = SINGULAR;
    public static final String DELETE_IMPACT = SINGULAR;

    public static final String GET_IMPACT_MAPPING = "/" + GET_IMPACT + "/{id}";
    public static final String GET_IMPACTS_MAPPING = "/" + GET_IMPACTS;
    public static final String POST_IMPACT_MAPPING = "/" + POST_IMPACT;
    public static final String PUT_IMPACT_MAPPING = "/" + PUT_IMPACT + "/{id}";
    public static final String DELETE_IMPACT_MAPPING = "/" + DELETE_IMPACT + "/{id}";

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
        return GET_REL_PREFIX + " " + SINGULAR;
    }

    public static String buildGetAllImpactsRel() {
        return GET_ALL_REL_PREFIX + " " + PLURAL;
    }

    public static String buildPostImpactRel() {
        return POST_REL_PREFIX + " " + SINGULAR;
    }

    public static String buildPutImpactRel() {
        return PUT_REL_PREFIX + " " + SINGULAR;
    }

    public static String buildDeleteImpactRel() {
        return DELETE_REL_PREFIX + " " + SINGULAR;
    }
}
