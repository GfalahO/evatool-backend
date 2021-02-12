package com.evatool.impact.application.controller.util;

import static com.evatool.impact.application.controller.util.RestSetting.*;

public class StakeholderRest {
    public static final String IMPACT_STAKEHOLDER_REST_CONTROLLER_MAPPING = BASE_URI;

    public static final String SINGULAR = "stakeholder";
    public static final String PLURAL = "stakeholders";

    public static final String GET_STAKEHOLDERS = PLURAL;

    public static final String GET_STAKEHOLDERS_MAPPING = "/" + GET_STAKEHOLDERS;

    public static String buildGetStakeholdersUri() {
        return IMPACT_STAKEHOLDER_REST_CONTROLLER_MAPPING + GET_STAKEHOLDERS_MAPPING;
    }

    public static String buildGetStakeholdersRel() {
        return GET_ALL_REL_PREFIX + " " + PLURAL;
    }
}
