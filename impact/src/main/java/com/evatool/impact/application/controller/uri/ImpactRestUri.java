package com.evatool.impact.application.controller.uri;

import com.evatool.impact.ModuleSettings;

public class ImpactRestUri {
    public static final String IMPACT_REST_CONTROLLER_MAPPING = ModuleSettings.BASE_URI;

    public static final String GET_IMPACT = "impact";
    public static final String GET_IMPACTS = "impacts";
    public static final String POST_IMPACT = "impact";
    public static final String PUT_IMPACT = "impact";
    public static final String DELETE_IMPACT = "impact";

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
}
