package com.magement.gateway.common.exceptions;

public class DeviceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 6630858679465111387L;

    public DeviceNotFoundException(int uid) {
        super("no device found with uid : " + uid);
    }
}
