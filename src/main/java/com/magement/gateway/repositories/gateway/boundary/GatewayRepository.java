package com.magement.gateway.repositories.gateway.boundary;

import com.magement.gateway.repositories.gateway.entity.GatewayEntity;
import org.springframework.data.repository.CrudRepository;

public interface GatewayRepository extends CrudRepository<GatewayEntity, String> {
}
