package com.bannerlordonlineplayers.controller;

public enum PlayerOrder {

    NAME("name"), // default
    ARMY("army"),
    CLAN("clan");

    private final String fieldName;

    PlayerOrder(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
