package com.gpsolutions.marklogic_demo.service.crud_service.impl;

import com.gpsolutions.marklogic_demo.converter.EntityDTOConverter;
import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import com.gpsolutions.marklogic_demo.entity.impl.CarEntity;
import com.gpsolutions.marklogic_demo.exception.SearchCriteriaException;
import com.gpsolutions.marklogic_demo.service.crud_service.GenericService;
import com.gpsolutions.marklogic_demo.service.search_service.SingleCriteriaSearchProcessor;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;
import com.gpsolutions.marklogic_demo.service.search_service.factory.SearchCriteriaFactory;
import com.gpsolutions.marklogic_demo.service.search_service.factory.SingleCriteriaSearchProcessorFactory;
import com.marklogic.client.pojo.PojoPage;
import com.marklogic.client.pojo.PojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MarkLogicCarService implements GenericService<CarDTO> {
    private final PojoRepository<CarEntity, String> carRepository;
    private final EntityDTOConverter<CarEntity, CarDTO> carConverter;
    private final SearchCriteriaFactory searchCriteriaFactory;
    private final SingleCriteriaSearchProcessor<CarEntity> searchProcessor;

    @Autowired
    public MarkLogicCarService(final PojoRepository<CarEntity, String> carRepository,
                               final EntityDTOConverter<CarEntity, CarDTO> carConverter,
                               final SearchCriteriaFactory searchCriteriaFactory,
                               final SingleCriteriaSearchProcessorFactory searchProcessorFactory) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
        this.searchCriteriaFactory = searchCriteriaFactory;
        this.searchProcessor = searchProcessorFactory.buildCarEntitySearchProcessor(carRepository);
    }

    @Override
    public void create(final CarDTO carDTO) {
        carDTO.setId(UUID.randomUUID().toString());
        carRepository.write(carConverter.convertToEntity(carDTO));
    }

    @Override
    public CarDTO read(final String id) {
        return carConverter.convertToDTO(carRepository.read(id));
    }

    @Override
    public List<CarDTO> readAll() {
        return convertPojoPajeToList(carRepository.readAll(0)).stream().map(carConverter::convertToDTO).
                collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> search(final String searchPattern, final Class<?> searchPatternClass,
                               final MatchType matchType, final String fieldName) {
        if (searchPatternClass.isAssignableFrom(String.class)) {
            return searchProcessor.getSearchResult(
                    searchCriteriaFactory.buildCriteria(matchType, fieldName, searchPattern)).stream().
                    map(carConverter::convertToDTO).collect(Collectors.toList());
        } else if (searchPatternClass.isAssignableFrom(Integer.class)) {
            return searchProcessor.getSearchResult(
                    searchCriteriaFactory.buildCriteria(matchType, fieldName, Integer.valueOf(searchPattern))).stream().
                    map(carConverter::convertToDTO).collect(Collectors.toList());
        } else if (searchPatternClass.isAssignableFrom(Double.class)) {
            return searchProcessor.getSearchResult(
                    searchCriteriaFactory.buildCriteria(matchType, fieldName, Double.valueOf(searchPattern))).stream().
                    map(carConverter::convertToDTO).collect(Collectors.toList());
        } else {
            throw new SearchCriteriaException("Failed to qualify search pattern class!");
        }
    }

    @Override
    public void update(final CarDTO carDTO) {
        carRepository.write(carConverter.convertToEntity(carDTO));
    }

    @Override
    public void delete(final String id) {
        carRepository.delete(id);
    }

    private List<CarEntity> convertPojoPajeToList(final PojoPage<CarEntity> pojoPage) {
        return StreamSupport.stream(pojoPage.spliterator(), false).collect(Collectors.toList());
    }
}
