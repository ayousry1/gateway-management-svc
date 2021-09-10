package com.magement.gateway.common.enums;

public enum DeviceStatus {
    ONLINE("ONLINE"),
    OFFLINE("OFFLINE");

    private final String valueEn;

    private DeviceStatus(String valueEn) {
        this.valueEn = valueEn;
    }

    public String getValueEn() {
        return this.valueEn;
    }
}
