package com.doan.maintenancecard.exception.customerException;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Long id, String entityName){
        super("not found id "+ entityName +" : " + id);
    }

    public EntityNotFoundException(String name){
        super("not found customer : " + name);
    }
}
