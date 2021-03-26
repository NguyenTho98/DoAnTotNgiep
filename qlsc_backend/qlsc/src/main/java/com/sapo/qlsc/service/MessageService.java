package com.sapo.qlsc.service;

import com.sapo.qlsc.dto.MessageDTO;
import com.sapo.qlsc.exception.commonException.NotFoundException;

import java.util.List;
import java.util.Map;

public interface MessageService {

    int countMessageByUserId(Long userId);
    Map<String, Object> getListMessage(String email, int page, int size);
    void readMessage(int id, String email) throws NotFoundException;

}
