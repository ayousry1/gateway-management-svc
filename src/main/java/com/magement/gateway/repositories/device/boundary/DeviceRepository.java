package com.magement.gateway.repositories.device.boundary;

import com.magement.gateway.repositories.device.entity.DeviceEntity;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> {
}
