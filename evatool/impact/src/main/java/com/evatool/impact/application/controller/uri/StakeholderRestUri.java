package com.evatool.impact.application.controller.uri;

import com.evatool.impact.ModuleSettings;

public class StakeholderRestUri {
    public static final String GET_STAKEHOLDER_URI = ModuleSettings.BASE_URI + "/stakeholder{id}";
    public static final String GET_ALL_STAKEHOLDERS_URI = ModuleSettings.BASE_URI + "/stakeholders";
    public static final String CREATE_STAKEHOLDER_URI = ModuleSettings.BASE_URI + "/stakeholder";
    public static final String UPDATE_STAKEHOLDER_URI = ModuleSettings.BASE_URI + "/stakeholder/{id}";
    public static final String DELETE_STAKEHOLDER_URI = ModuleSettings.BASE_URI + "/stakeholder/{id}";

    public static String getGetStakeholderUri(String id) {
        return GET_STAKEHOLDER_URI.replace("{id}", id);
    }

    public static String getGetStakeholdersUri() {
        return GET_ALL_STAKEHOLDERS_URI;
    }

    public static String getCreateStakeholderUri() {
        return CREATE_STAKEHOLDER_URI;
    }

    public static String getUpdateStakeholderUri(String id) {
        return UPDATE_STAKEHOLDER_URI.replace("{id}", id);
    }

    public static String getDeleteStakeholderUri(String id) {
        return DELETE_STAKEHOLDER_URI.replace("{id}", id);
    }
}
