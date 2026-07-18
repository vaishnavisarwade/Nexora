package com.vaishnavi.nexora.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public ResourceNotFoundException(String message) {
        super(message);
    }


    public ResourceNotFoundException(
            String resourceName,
            String fieldName,
            Object fieldValue
    ) {
        super(
                resourceName
                        + " not found with "
                        + fieldName
                        + " : "
                        + fieldValue
        );
    }
}