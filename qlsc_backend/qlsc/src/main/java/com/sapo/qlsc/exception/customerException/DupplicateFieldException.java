package com.sapo.qlsc.exception.customerException;

public class DupplicateFieldException extends RuntimeException{

    public DupplicateFieldException(String fieldName, String objectName){
        super(fieldName + " của "+ objectName+ "bị trùng");
    }
}
