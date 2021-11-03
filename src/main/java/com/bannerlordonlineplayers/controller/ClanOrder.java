package com.bannerlordonlineplayers.controller;

public enum ClanOrder {

    NAME("name"), // default
    ARMY_SIZE("army_size"),
    MEMBERS_COUNT("members_count");

    private final String fieldName;

    ClanOrder(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
