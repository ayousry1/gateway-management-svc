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
    private long UID;
    private String vendor;
    private LocalDateTime dateCreated;
    private DeviceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="serialNumber", nullable=false)
    @JoinColumn(name = "serialNumber")
    private GatewayEntity gateway;
}
