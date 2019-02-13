package com.gpsolutions.marklogic_demo.service.impl;

import com.gpsolutions.marklogic_demo.entity.car.CarEntity;
import com.gpsolutions.marklogic_demo.service.GenericService;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.pojo.PojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MarkLogicCarEntityService implements GenericService<CarEntity> {
    private final DatabaseClient databaseClient;
    private final PojoRepository<CarEntity, String> repository;

    @Autowired
    public MarkLogicCarEntityService(final DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
        this.repository = databaseClient.newPojoRepository(CarEntity.class, String.class);
    }

    @Override
    public void create(CarEntity carEntity) {
        repository.write(carEntity);
    }

    @Override
    public List<CarEntity> readAll() {
        return StreamSupport.stream(repository.readAll(1).spliterator(), false).
                collect(Collectors.toList());
    }

    @PreDestroy
    public void release() {
        databaseClient.release();
    }
}
