package com.gpsolutions.marklogic_demo.repository.impl;

import com.gpsolutions.marklogic_demo.entity.impl.CarEntity;
import com.gpsolutions.marklogic_demo.repository.GenericRepository;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.pojo.PojoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class MarkLogicPojoCarRepository implements GenericRepository<CarEntity> {
    private final DatabaseClient databaseClient;
    private final PojoRepository<CarEntity, String> pojoRepository;

    public MarkLogicPojoCarRepository(final DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
        this.pojoRepository = databaseClient.newPojoRepository(CarEntity.class, String.class);
    }

    @Override
    public void create(final CarEntity carEntity) {
        pojoRepository.write(carEntity);
    }

    @Override
    public CarEntity read(final String id) {
        return pojoRepository.read(id);
    }

    @Override
    public List<CarEntity> readAll() {
        return StreamSupport.stream(pojoRepository.readAll(0).spliterator(), false).
                collect(Collectors.toList());
    }

    @PreDestroy
    public void release() {
        databaseClient.release();
    }
}
