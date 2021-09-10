package com.magement.gateway.resources.gateway.entity;

import com.magement.gateway.common.enums.DeviceStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceModel {
    private int UID;
    private String vendor;
    private LocalDateTime dateCreated;
    private DeviceStatus status;
}
