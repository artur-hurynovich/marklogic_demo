package com.gpsolutions.marklogic_demo.service.search_service.exception;

public class SearchCriteriaException extends RuntimeException {
    public SearchCriteriaException() {
        super();
    }

    public SearchCriteriaException(String message) {
        super(message);
    }

    public SearchCriteriaException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchCriteriaException(Throwable cause) {
        super(cause);
    }
}
