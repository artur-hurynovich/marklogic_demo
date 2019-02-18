package com.gpsolutions.marklogic_demo.service.search_service.factory;

import com.gpsolutions.marklogic_demo.exception.SearchCriteriaException;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;
import com.gpsolutions.marklogic_demo.service.search_service.impl.DoubleSearchCriteria;
import com.gpsolutions.marklogic_demo.service.search_service.impl.IntegerSearchCriteria;
import com.gpsolutions.marklogic_demo.service.search_service.impl.StringSearchCriteria;
import org.springframework.stereotype.Service;

@Service
public class SearchCriteriaFactory {
    private static final String FIELD_NAME_EXCEPTION_MESSAGE = "Field name can't be null!";
    private static final String MATCH_TYPE_EXCEPTION_MESSAGE = "Match type %s is not applicable " +
            "for search pattern of class %s!";

    public StringSearchCriteria buildCriteria(final MatchType matchType,
                                                       final String fieldName,
                                                       final String searchPattern) {
        if (fieldName == null) {
            throw new SearchCriteriaException(FIELD_NAME_EXCEPTION_MESSAGE);
        } else if (invalidMatchTypeForStringCriteria(matchType)) {
            throw new SearchCriteriaException(
                    String.format(MATCH_TYPE_EXCEPTION_MESSAGE, matchType.name(), searchPattern.getClass().getName()));
        } else {
            return new StringSearchCriteria(matchType, fieldName, searchPattern);
        }
    }

    public IntegerSearchCriteria buildCriteria(final MatchType matchType,
                                                        final String fieldName,
                                                        final Integer searchPattern) {
        if (fieldName == null) {
            throw new SearchCriteriaException(FIELD_NAME_EXCEPTION_MESSAGE);
        } else if (invalidMatchTypeForNumberCriteria(matchType)) {
            throw new SearchCriteriaException(
                    String.format(MATCH_TYPE_EXCEPTION_MESSAGE, matchType.name(), searchPattern.getClass().getName()));
        } else {
            return new IntegerSearchCriteria(matchType, fieldName, searchPattern);
        }
    }

    public DoubleSearchCriteria buildCriteria(final MatchType matchType,
                                                       final String fieldName,
                                                       final Double searchPattern) {
        if (fieldName == null) {
            throw new SearchCriteriaException(FIELD_NAME_EXCEPTION_MESSAGE);
        } else if (invalidMatchTypeForNumberCriteria(matchType)) {
            throw new SearchCriteriaException(
                    String.format(MATCH_TYPE_EXCEPTION_MESSAGE, matchType.name(), searchPattern.getClass().getName()));
        } else {
            return new DoubleSearchCriteria(matchType, fieldName, searchPattern);
        }
    }

    private boolean invalidMatchTypeForStringCriteria(final MatchType matchType) {
        return (matchType.equals(MatchType.LESS_THAN) || matchType.equals(MatchType.GREATER_THAN));
    }

    private boolean invalidMatchTypeForNumberCriteria(final MatchType matchType) {
        return matchType.equals(MatchType.PARTIAL_MATCH);
    }
}
