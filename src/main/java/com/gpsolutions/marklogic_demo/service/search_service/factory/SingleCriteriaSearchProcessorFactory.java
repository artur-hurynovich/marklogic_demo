package com.gpsolutions.marklogic_demo.service.search_service.factory;

import com.gpsolutions.marklogic_demo.entity.impl.CarEntity;
import com.gpsolutions.marklogic_demo.service.search_service.SingleCriteriaSearchProcessor;
import com.gpsolutions.marklogic_demo.service.search_service.impl.CarEntityStringSearchProcessor;
import com.marklogic.client.pojo.PojoRepository;
import org.springframework.stereotype.Component;

@Component
public class SingleCriteriaSearchProcessorFactory {
    public SingleCriteriaSearchProcessor<CarEntity> buildCarEntitySearchProcessor(
            final PojoRepository<CarEntity, String> pojoRepository) {
        return new CarEntityStringSearchProcessor(pojoRepository);
    }
}
