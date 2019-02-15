package com.gpsolutions.marklogic_demo.service.search_service.factory;

import com.gpsolutions.marklogic_demo.service.search_service.SearchCriteria;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;
import com.gpsolutions.marklogic_demo.service.search_service.exception.SearchCriteriaException;
import org.springframework.stereotype.Service;

@Service
public class SearchCriteriaFactory {
    public SearchCriteria buildCriteria(final String searchPattern,
                                        final MatchType matchType,
                                        final String fieldName) {
        if (invalidMatchTypeForStringCriteria(matchType)) {
            throw new SearchCriteriaException("Match type " + matchType.name() +
                    " is not applicable for search pattern of type " + searchPattern.getClass().getName() + "!");
        } else if (fieldName == null) {
            throw new SearchCriteriaException("Field name can't be null!");
        } else {
            return new SearchCriteria(searchPattern, matchType, fieldName);
        }
    }

    public SearchCriteria buildCriteria(final Integer searchPattern,
                                        final MatchType matchType,
                                        final String fieldName) {
        return buildCriteriaForNumberSearchPattern(searchPattern, matchType, fieldName);
    }

    public SearchCriteria buildCriteria(final Double searchPattern,
                                        final MatchType matchType,
                                        final String fieldName) {
        return buildCriteriaForNumberSearchPattern(searchPattern, matchType, fieldName);
    }

    private boolean invalidMatchTypeForStringCriteria(final MatchType matchType) {
        return (matchType.equals(MatchType.LESS_THAN) || matchType.equals(MatchType.GREATER_THAN));
    }

    private boolean invalidMatchTypeForNumberCriteria(final MatchType matchType) {
        return matchType.equals(MatchType.PARTIAL_MATCH);
    }

    private SearchCriteria buildCriteriaForNumberSearchPattern(final Number searchPattern,
                                                               final MatchType matchType,
                                                               final String fieldName) {
        if (invalidMatchTypeForNumberCriteria(matchType)) {
            throw new SearchCriteriaException("Match type " + matchType.name() +
                    " is not applicable for search pattern of type " + searchPattern.getClass().getName() + "!");
        } else if (fieldName == null) {
            throw new SearchCriteriaException("Field name can't be null!");
        } else {
            return new SearchCriteria(searchPattern, matchType, fieldName);
        }
    }
}
