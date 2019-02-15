package com.gpsolutions.marklogic_demo.service.search_service.impl;

import com.gpsolutions.marklogic_demo.entity.impl.CarEntity;
import com.gpsolutions.marklogic_demo.service.search_service.AbstractSingleCriteriaSearchProcessor;
import com.gpsolutions.marklogic_demo.service.search_service.SearchCriteria;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;
import com.gpsolutions.marklogic_demo.service.search_service.exception.SearchCriteriaException;
import com.marklogic.client.pojo.PojoQueryBuilder;
import com.marklogic.client.pojo.PojoRepository;
import com.marklogic.client.query.StructuredQueryDefinition;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CarEntitySingleCriteriaSearchProcessor extends AbstractSingleCriteriaSearchProcessor<CarEntity, String> {
    public CarEntitySingleCriteriaSearchProcessor(final PojoRepository<CarEntity, String> pojoRepository) {
        super(pojoRepository);
    }

    @Override
    public List<CarEntity> getSearchResult() {
        final SearchCriteria searchCriteria = getSearchCriteria();
        if (searchCriteria == null) {
            throw new SearchCriteriaException("Search criteria is null! Set search criteria!");
        }
        final PojoQueryBuilder<CarEntity> queryBuilder = getPojoRepository().getQueryBuilder();
        if (getSearchCriteria().getSearchPattern() instanceof String) {
            return getSearchResultByStringPattern(queryBuilder);
        } else {
            return getSearchResultByNumberPattern(queryBuilder);
        }
    }

    private List<CarEntity> getSearchResultByStringPattern(final PojoQueryBuilder<CarEntity> queryBuilder) {
        if (getSearchCriteria().getMatchType().equals(MatchType.FULL_MATCH)) {
            return convertQueryDefinitionToList(queryBuilder.value(getSearchCriteria().getFieldName(),
                    String.valueOf(getSearchCriteria().getSearchPattern())));
        } else {
            return convertQueryDefinitionToList(queryBuilder.word(getSearchCriteria().getFieldName(),
                    "*" + getSearchCriteria().getSearchPattern() + "*"));
        }
    }

    private List<CarEntity> getSearchResultByNumberPattern(final PojoQueryBuilder<CarEntity> queryBuilder) {
        if (getSearchCriteria().getMatchType().equals(MatchType.FULL_MATCH)) {
            return convertQueryDefinitionToList(queryBuilder.value(getSearchCriteria().getFieldName(),
                    Double.valueOf(getSearchCriteria().getSearchPattern().toString())));
        } else if (getSearchCriteria().getMatchType().equals(MatchType.GREATER_THAN)){
            return convertQueryDefinitionToList(queryBuilder.range(getSearchCriteria().getFieldName(),
                    PojoQueryBuilder.Operator.GT, Integer.valueOf(getSearchCriteria().getSearchPattern().toString())));
        } else {
            return convertQueryDefinitionToList(queryBuilder.range(getSearchCriteria().getFieldName(),
                    PojoQueryBuilder.Operator.LT, Integer.valueOf(getSearchCriteria().getSearchPattern().toString())));
        }
    }

    private List<CarEntity> convertQueryDefinitionToList(final StructuredQueryDefinition queryDefinition) {
        return StreamSupport.stream(getPojoRepository().search(queryDefinition, 1).spliterator(), false).
                collect(Collectors.toList());
    }
}
