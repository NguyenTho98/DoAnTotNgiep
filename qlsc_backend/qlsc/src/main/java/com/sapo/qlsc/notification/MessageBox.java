package com.sapo.qlsc.notification;

import java.util.ArrayList;
import java.util.List;

public class MessageBox {

    private Boolean status;
    private List<String> messages = new ArrayList<String>();
    private Object object;


    public MessageBox(Boolean status, List<String> message) {
        this.status = status;
        this.messages = message;
    }

    public MessageBox(Boolean status, String message, Object object){
        this.status = status;
        this.messages.add(message);
        this.object = object;
    }
    public MessageBox(Boolean status, String message){
        this.status = status;
        this.messages.add(message);
    }

    public void addMessageBoxError(String errorMessage){
        this.messages.add(errorMessage);
    }


    public void setMessageBoxSuccess(String message){
        this.messages.add(message);
        this.status = true;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<String> getMessage() {
        return messages;
    }

    public void setMessage(List<String> messages) {
        this.messages = messages;
    }
}
