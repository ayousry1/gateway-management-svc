package com.magement.gateway.resources.gateway.boundary;

import com.magement.gateway.common.exceptions.DeviceNotFoundException;
import com.magement.gateway.common.exceptions.GatewayNotFoundException;
import com.magement.gateway.repositories.device.boundary.DeviceRepository;
import com.magement.gateway.repositories.device.entity.DeviceEntity;
import com.magement.gateway.repositories.gateway.boundary.GatewayRepository;
import com.magement.gateway.repositories.gateway.entity.GatewayEntity;
import com.magement.gateway.resources.gateway.control.GatewayResourceCtrl;
import com.magement.gateway.resources.gateway.entity.DeviceModel;
import com.magement.gateway.resources.gateway.entity.GatewayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class GatewayResource {

    private GatewayRepository gatewayRepository;
    private DeviceRepository deviceRepository;
    private GatewayResourceCtrl gatewayResourceCtrl;
    private final String CONTEXT_ROOT = "/gateways/";

    @Autowired
    public GatewayResource(GatewayRepository gatewayRepository, DeviceRepository deviceRepository, GatewayResourceCtrl gatewayResourceCtrl) {
        this.gatewayRepository = gatewayRepository;
        this.deviceRepository = deviceRepository;
        this.gatewayResourceCtrl = gatewayResourceCtrl;
    }

    @GetMapping
    public ResponseEntity<List<GatewayModel>> getAllGateways() {
        List<GatewayEntity> gatewayEntities = new ArrayList<>();
        gatewayRepository.findAll().forEach(gatewayEntities::add);
        return ResponseEntity.ok(gatewayResourceCtrl.mapFromListFromEntities(gatewayEntities));
    }

    @PostMapping
    public ResponseEntity addGateway(@RequestBody @Valid GatewayModel body) {
        gatewayRepository.save(gatewayResourceCtrl.getGatewayEntity(body));
        return ResponseEntity.created(URI.create(CONTEXT_ROOT + body.getSerialNumber())).build();
    }

    @GetMapping("/{serial}")
    public ResponseEntity<GatewayModel> getGatewayBySerial(@PathVariable("serial") @NotEmpty String serial) {
        Optional<GatewayEntity> gatewayEntity = gatewayRepository.findById(serial);
        if (gatewayEntity.isPresent()) {
            return ResponseEntity.ok(gatewayResourceCtrl.mapModelFromEntity(gatewayEntity.get()));
        } else {
            throw new GatewayNotFoundException(serial);
        }
    }

    @PostMapping("/{serial}/devices")
    public ResponseEntity addDeviceToGateway(@PathVariable("serial") @NotEmpty String serial,
                                             @RequestBody DeviceModel body) {
        Optional<GatewayEntity> gatewayOptional = gatewayRepository.findById(serial);
        if (gatewayOptional.isPresent()) {
            DeviceEntity deviceEntity = gatewayResourceCtrl.getDeviceEntity(body);
            deviceEntity.setGateway(gatewayOptional.get());
            deviceRepository.save(deviceEntity);
            return ResponseEntity.ok().build();
        } else {
            throw new GatewayNotFoundException(serial);
        }
    }

    @DeleteMapping("/{serial}/devices/{uid}")
    public ResponseEntity removeDeviceFromGateway(@PathVariable("serial") @NotEmpty String serial,
                                                  @PathVariable("uid") @NotEmpty int uid) {
        Optional<GatewayEntity> gatewayEntity = gatewayRepository.findById(serial);
        if (gatewayEntity.isPresent()) {
            GatewayEntity foundGateway = gatewayEntity.get();
            Optional<DeviceEntity> deviceEntity = foundGateway.getPeripheralDevices().stream()
                    .filter(element -> element.getUID() == uid).findFirst();
            if (deviceEntity.isPresent()) {
                foundGateway.removeChild(deviceEntity.get());
                gatewayRepository.save(foundGateway);
                return ResponseEntity.ok().build();
            } else {
                throw new DeviceNotFoundException(uid);
            }
        } else {
            throw new GatewayNotFoundException(serial);
        }
    }
}
