package com.gpsolutions.marklogic_demo.util;

public class CamelCaseToHumanizeConverter {
    public String convert(final String camelCaseString) {
        final StringBuilder humanizeStringBuilder = new StringBuilder(camelCaseString);
        for (int i = 0; i < humanizeStringBuilder.length(); i++) {
            final char currentChar = humanizeStringBuilder.charAt(i);
            if (i == 0 && Character.isLowerCase(currentChar)) {
                humanizeStringBuilder.replace(i, i + 1, String.valueOf(Character.valueOf(currentChar)).toUpperCase());
            } else if (i > 0 && Character.isUpperCase(currentChar)) {
                humanizeStringBuilder.replace(i, i + 1, (" " + currentChar).toLowerCase());
            }
        }
        return humanizeStringBuilder.toString();
    }
}
