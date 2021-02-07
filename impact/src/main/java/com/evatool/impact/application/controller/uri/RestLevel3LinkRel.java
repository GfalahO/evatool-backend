package com.evatool.impact.application.controller.uri;

public class RestLevel3LinkRel {
    public static final String GET_REL = "get";
    public static final String GET_ALL_REL = "get all";
    public static final String POST_REL = "create";
    public static final String PUT_REL = "update";
    public static final String DELETE_REL = "delete";

    public static String buildGetRel(String name) {
        return GET_REL + " " + name;
    }

    public static String buildGetAllRel(String name) {
        return GET_ALL_REL + " " + name;
    }

    public static String buildPostRel(String name) {
        return POST_REL + " " + name;
    }

    public static String buildPutRel(String name) {
        return PUT_REL + " " + name;
    }

    public static String buildDeleteRel(String name) {
        return DELETE_REL + " " + name;
    }
}
