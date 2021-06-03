package com.doan.user.service;

import java.util.Map;

public interface MessageService {

    Map<String, Object> getListMessage(String userId, int page, int size);

    boolean readMessage(int id) ;

}
