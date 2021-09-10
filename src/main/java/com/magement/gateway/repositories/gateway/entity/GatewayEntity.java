package com.magement.gateway.repositories.gateway.entity;

import com.magement.gateway.repositories.device.entity.DeviceEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "gateway")
public class GatewayEntity {
    @Id
    private String serialNumber;
    private String name;
    private String ip4Address;
    @OneToMany(mappedBy = "gateway", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceEntity> peripheralDevices;
}
