package com.ecommerce.project.springbootecom.exception;

public class ResourceNotFoundException extends RuntimeException {

    String resource;
    String field;
    String fieldName;
    Long FieldId;


    public ResourceNotFoundException() {}
    public ResourceNotFoundException(String resource, String field, String fieldName) {
        super(String.format("%s not found with %s:%s",resource,field,fieldName));
        this.resource = resource;
        this.field = field;
        this.fieldName = fieldName;
    }
    public ResourceNotFoundException(String resource, String field, Long fieldId) {
        super(String.format("%s not found with %s:%d",resource,field,fieldId));
        this.resource = resource;
        this.field = field;
        this.FieldId = fieldId;
    }



}
