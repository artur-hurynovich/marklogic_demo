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
public class MarklogicPojoCarEntityRepository implements GenericRepository<CarEntity> {
    private final DatabaseClient databaseClient;
    private final PojoRepository<CarEntity, String> repository;

    public MarklogicPojoCarEntityRepository(final DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
        this.repository = databaseClient.newPojoRepository(CarEntity.class, String.class);
    }

    @Override
    public void create(final CarEntity carEntity) {
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
