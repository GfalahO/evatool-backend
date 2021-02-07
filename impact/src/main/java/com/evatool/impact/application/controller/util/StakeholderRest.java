package com.evatool.impact.application.controller.util;

import static com.evatool.impact.application.controller.util.RestSetting.*;

public class StakeholderRest {
    public static final String IMPACT_STAKEHOLDER_REST_CONTROLLER_MAPPING = BASE_URI;

    public static final String SINGULAR = "stakeholder";
    public static final String PLURAL = "stakeholders";

    public static final String GET_STAKEHOLDER = SINGULAR;
    public static final String GET_STAKEHOLDERS = PLURAL;
    public static final String POST_STAKEHOLDER = SINGULAR;
    public static final String PUT_STAKEHOLDER = SINGULAR;
    public static final String DELETE_STAKEHOLDER = SINGULAR;

    public static final String GET_STAKEHOLDER_MAPPING = "/" + GET_STAKEHOLDER + "/{id}";
    public static final String GET_STAKEHOLDERS_MAPPING = "/" + GET_STAKEHOLDERS;
    public static final String POST_STAKEHOLDER_MAPPING = "/" + POST_STAKEHOLDER;
    public static final String PUT_STAKEHOLDER_MAPPING = "/" + PUT_STAKEHOLDER + "/{id}";
    public static final String DELETE_STAKEHOLDER_MAPPING = "/" + DELETE_STAKEHOLDER + "/{id}";

    public static String buildGetStakeholderUri(String id) {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_MAPPING + GET_STAKEHOLDER_MAPPING.replace("{id}", id);
    }

    public static String buildGetStakeholdersUri() {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_MAPPING + GET_STAKEHOLDERS_MAPPING;
    }

    public static String buildPostStakeholderUri() {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_MAPPING + POST_STAKEHOLDER_MAPPING;
    }

    public static String buildPutStakeholderUri(String id) {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_MAPPING + PUT_STAKEHOLDER_MAPPING.replace("{id}", id);
    }

    public static String buildDeleteStakeholderUri(String id) {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_MAPPING + DELETE_STAKEHOLDER_MAPPING.replace("{id}", id);
    }

    public static String buildGetStakeholderRel() {
        return GET_REL_PREFIX + " " + SINGULAR;
    }

    public static String buildGetStakeholdersRel() {
        return GET_ALL_REL_PREFIX + " " + PLURAL;
    }

    public static String buildPostStakeholderRel() {
        return POST_REL_PREFIX + " " + SINGULAR;
    }

    public static String buildPutStakeholderRel() {
        return PUT_REL_PREFIX + " " + SINGULAR;
    }

    public static String buildDeleteStakeholderRel() {
        return DELETE_REL_PREFIX + " " + SINGULAR;
    }
}
