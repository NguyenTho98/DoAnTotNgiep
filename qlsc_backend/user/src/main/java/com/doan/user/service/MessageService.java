package com.doan.user.service;


import com.doan.user.exception.commonException.NotFoundException;

import java.util.Map;

public interface MessageService {

    int countMessageByUserId(Long userId);
    Map<String, Object> getListMessage(String email, int page, int size);
    void readMessage(int id, String email) throws NotFoundException;

}
