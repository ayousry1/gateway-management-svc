package com.magement.gateway.resources.gateway.boundary;

import com.magement.gateway.repositories.gateway.boundary.GatewayRepository;
import com.magement.gateway.repositories.gateway.entity.GatewayEntity;
import com.magement.gateway.resources.gateway.control.GatewayResourceCtrl;
import com.magement.gateway.resources.gateway.entity.GatewayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GatewayResource {

    private GatewayRepository gatewayRepository;
    private GatewayResourceCtrl gatewayResourceCtrl;
    private final String CONTEXT_ROOT = "/gateways/";

    @Autowired
    public GatewayResource(GatewayRepository gatewayRepository, GatewayResourceCtrl gatewayResourceCtrl) {
        this.gatewayRepository = gatewayRepository;
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
        gatewayRepository.save(gatewayResourceCtrl.getEntity(body));
        return ResponseEntity.created(URI.create(CONTEXT_ROOT + body.getSerialNumber())).build();
    }
}
