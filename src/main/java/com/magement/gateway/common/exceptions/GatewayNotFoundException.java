package com.magement.gateway.common.exceptions;

public class GatewayNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -4767114094036009815L;

    public GatewayNotFoundException(String serial) {
        super("no gateway found with serial : " + serial);
    }
}
