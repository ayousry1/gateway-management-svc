package com.magement.gateway.resources.gateway.entity;

import com.magement.gateway.common.validation.ip.ValidIP;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class GatewayModel {
    private String serialNumber;
    private String name;
    @ValidIP
    private String ip4Address;
    @Size(max = 10,message = "size of devices must be less than 10")
    private List<DeviceModel> peripheralDevices;
}
