package com.magement.gateway.resources.gateway.control;

import com.magement.gateway.common.Utilities;
import com.magement.gateway.repositories.gateway.entity.GatewayEntity;
import com.magement.gateway.resources.gateway.entity.GatewayModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GatewayResourceCtrl {
    public List<GatewayModel> mapFromListFromEntities(List<GatewayEntity> gatewayEntities) {
        List<GatewayModel> gatewayModels = new ArrayList<>();
        gatewayEntities.stream().map(this::mapModelFromEntity).
                forEach(gatewayModels::add);
        return gatewayModels;
    }

    public GatewayModel mapModelFromEntity(GatewayEntity gatewayEntity) {
        return Utilities.getModelMapper().map(gatewayEntity, GatewayModel.class);
    }

    public GatewayEntity getEntity(GatewayModel body) {
        GatewayEntity entity = Utilities.getModelMapper().map(body, GatewayEntity.class);
        entity.getPeripheralDevices().forEach(deviceEntity -> deviceEntity.setGateway(entity));
        return entity;
    }
}
