package com.sat.student.exception;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s not found for the %s = '%s'", resourceName, fieldName, fieldValue));
    }
}
