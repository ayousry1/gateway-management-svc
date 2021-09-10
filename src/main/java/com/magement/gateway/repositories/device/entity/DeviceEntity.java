package com.magement.gateway.repositories.device.entity;

import com.magement.gateway.common.enums.DeviceStatus;
import com.magement.gateway.repositories.gateway.entity.GatewayEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "device")
public class DeviceEntity {
    @Id
    private int UID;
    private String vendor;
    private LocalDateTime dateCreated;
    private DeviceStatus status;

    @ManyToOne
    private GatewayEntity gateway;
}
