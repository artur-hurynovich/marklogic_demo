package com.gpsolutions.marklogic_demo.service.search_service.impl;

import com.gpsolutions.marklogic_demo.entity.impl.CarEntity;
import com.gpsolutions.marklogic_demo.service.search_service.AbstractSingleCriteriaSearchProcessor;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;
import com.marklogic.client.pojo.PojoQueryBuilder;
import com.marklogic.client.pojo.PojoRepository;
import com.marklogic.client.query.StructuredQueryDefinition;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CarEntityStringSearchProcessor extends AbstractSingleCriteriaSearchProcessor<CarEntity, String> {
    private final PojoQueryBuilder<CarEntity> queryBuilder;

    public CarEntityStringSearchProcessor(final PojoRepository<CarEntity, String> pojoRepository) {
        super(pojoRepository);
        queryBuilder = getPojoRepository().getQueryBuilder();
    }

    @Override
    public List<CarEntity> getSearchResult(final StringSearchCriteria searchCriteria) {
        if (searchCriteria.getMatchType().equals(MatchType.FULL_MATCH)) {
            return convertQueryDefinitionToList(queryBuilder.value(searchCriteria.getFieldName(),
                    searchCriteria.getSearchPattern()));
        } else {
            return convertQueryDefinitionToList(queryBuilder.word(searchCriteria.getFieldName(),
                    "*" + searchCriteria.getSearchPattern() + "*"));
        }
    }

    @Override
    public List<CarEntity> getSearchResult(final IntegerSearchCriteria searchCriteria) {
        return processSearchingForNumericSearchPattern(searchCriteria.getMatchType(), searchCriteria.getFieldName(),
                searchCriteria.getSearchPattern());
    }

    @Override
    public List<CarEntity> getSearchResult(final DoubleSearchCriteria searchCriteria) {
        return processSearchingForNumericSearchPattern(searchCriteria.getMatchType(), searchCriteria.getFieldName(),
                searchCriteria.getSearchPattern());
    }

    private List<CarEntity> processSearchingForNumericSearchPattern(final MatchType matchType, final String fieldName,
                                                                    final Number searchPattern) {
        if (matchType.equals(MatchType.FULL_MATCH)) {
            return convertQueryDefinitionToList(queryBuilder.value(fieldName, searchPattern));
        } else if (matchType.equals(MatchType.GREATER_THAN)){
            return convertQueryDefinitionToList(queryBuilder.range(fieldName,
                    PojoQueryBuilder.Operator.GT, searchPattern));
        } else {
            return convertQueryDefinitionToList(queryBuilder.range(fieldName,
                    PojoQueryBuilder.Operator.LT, searchPattern));
        }
    }

    private List<CarEntity> convertQueryDefinitionToList(final StructuredQueryDefinition queryDefinition) {
        return StreamSupport.stream(getPojoRepository().search(queryDefinition, 1).spliterator(), false).
                collect(Collectors.toList());
    }
}
