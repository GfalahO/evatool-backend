package com.evatool.impact.application.controller.uri;

import com.evatool.impact.ModuleSettings;

public class StakeholderRestUri {
    public static final String IMPACT_STAKEHOLDER_REST_CONTROLLER_URI = ModuleSettings.BASE_URI;
    public static final String GET_STAKEHOLDER_URI = "/stakeholder/{id}";
    public static final String GET_ALL_STAKEHOLDERS_URI = "/stakeholders";
    public static final String CREATE_STAKEHOLDER_URI = "/stakeholder";
    public static final String UPDATE_STAKEHOLDER_URI = "/stakeholder/{id}";
    public static final String DELETE_STAKEHOLDER_URI = "/stakeholder/{id}";

    public static String buildGetStakeholderUri(String id) {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_URI + GET_STAKEHOLDER_URI.replace("{id}", id);
    }

    public static String buildGetStakeholdersUri() {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_URI + GET_ALL_STAKEHOLDERS_URI;
    }

    public static String buildCreateStakeholderUri() {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_URI + CREATE_STAKEHOLDER_URI;
    }

    public static String buildUpdateStakeholderUri(String id) {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_URI + UPDATE_STAKEHOLDER_URI.replace("{id}", id);
    }

    public static String buildDeleteStakeholderUri(String id) {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_URI + DELETE_STAKEHOLDER_URI.replace("{id}", id);
    }
}
