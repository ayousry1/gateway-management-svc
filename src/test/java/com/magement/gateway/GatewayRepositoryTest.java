package com.magement.gateway;

import com.magement.gateway.repositories.gateway.boundary.GatewayRepository;
import com.magement.gateway.repositories.gateway.entity.GatewayEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GatewayRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GatewayRepository gatewayRepository;

    private final String GATEWAY_IP = "10.0.0.1";

    @Test
    public void whenFindAll_thenReturnPersistedGateways() {
        GatewayEntity expected = new GatewayEntity();
        expected.setName("name");
        expected.setSerialNumber("serial");
        expected.setIp4Address(GATEWAY_IP);
        entityManager.persist(expected);
        entityManager.flush();

        ArrayList<GatewayEntity> found = new ArrayList<>();
        gatewayRepository.findAll().forEach(found::add);

        Assertions.assertTrue(found.size() > 0);
        Assertions.assertEquals(expected,found.get(0));
    }
}
