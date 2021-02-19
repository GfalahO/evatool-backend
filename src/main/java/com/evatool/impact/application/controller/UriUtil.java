package com.evatool.impact.application.controller;

public class UriUtil {

    private UriUtil() {

    }

    public static final String STAKEHOLDERS = "/stakeholders";
    public static final String GET_STAKEHOLDER = "get stakeholder";

    public static final String IMPACTS_URI = "/impacts";
    public static final String IMPACTS_ID = "/impacts/{id}";

    public static final String DIMENSIONS_URI = "/dimensions";
    public static final String DIMENSIONS_ID = "/dimensions/{id}";

    public static final String DIMENSIONS = "dimensions";
    public static final String GET_DIMENSIONS = "get " + DIMENSIONS;
    public static final String GET_DIMENSIONS_BY_TYPE = "get " + DIMENSIONS + " by type";
    public static final String GET_ALL_DIMENSIONS = "get all " + DIMENSIONS;
    public static final String CREATE_DIMENSIONS = "create " + DIMENSIONS;
    public static final String UPDATE_DIMENSIONS = "update " + DIMENSIONS;
    public static final String DELETE_DIMENSIONS = "delete " + DIMENSIONS;

    public static final String IMPACTS = "impacts";
    public static final String GET_IMPACTS = "get " + IMPACTS;
    public static final String GET_ALL_IMPACTS = "get all " + IMPACTS;
    public static final String CREATE_IMPACTS = "create " + IMPACTS;
    public static final String UPDATE_IMPACTS = "update " + IMPACTS;
    public static final String DELETE_IMPACTS = "delete " + IMPACTS;
}
