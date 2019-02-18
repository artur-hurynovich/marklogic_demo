package com.gpsolutions.marklogic_demo.util;

import com.gpsolutions.marklogic_demo.exception.SearchCriteriaException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SearchPatternClassQualifier {
    private static final String STRING_REGEX = "[a-zA-Z]";
    private static final String INTEGER_REGEX = "[0-9]+";
    private static final String DOUBLE_REGEX = "[0-9]+.[0-9]+";
    private final Pattern stringPattern;

    public SearchPatternClassQualifier() {
        stringPattern = Pattern.compile(STRING_REGEX);
    }

    public Class<?> qualifyClass(final String searchPattern) {
        final Matcher matcher = stringPattern.matcher(searchPattern);
        if (matcher.find()) {
            return String.class;
        } else if (searchPattern.matches(INTEGER_REGEX)) {
            return Integer.class;
        } else if (searchPattern.matches(DOUBLE_REGEX)) {
            return Double.class;
        } else {
            throw new SearchCriteriaException("Failed to qualify class for search pattern '" + searchPattern + "'!");
        }
    }
}
