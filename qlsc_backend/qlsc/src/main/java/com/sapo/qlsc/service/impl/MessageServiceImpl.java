package com.sapo.qlsc.service.impl;

import com.sapo.qlsc.converter.MessageConverter;
import com.sapo.qlsc.dto.MessageDTO;
import com.sapo.qlsc.entity.Message;
import com.sapo.qlsc.entity.User;
import com.sapo.qlsc.exception.commonException.NotFoundException;
import com.sapo.qlsc.repository.MessageRepository;
import com.sapo.qlsc.repository.UserRepository;
import com.sapo.qlsc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageConverter messageConverter;

    @Override
    public int countMessageByUserId(Long userId) {
        return messageRepository.countMessagesByUserId(userId);
    }

    @Override
    public Map<String, Object> getListMessage(String email, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size, Sort.by("created_date").descending());
        List<Message> messages = messageRepository.getMessagesByEmail(email,paging);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message: messages) {
            messageDTOS.add(messageConverter.convertToDTO(message));
        }
        int totalItems =  messageRepository.countMessagesByEmail(email);
        int totalPages =0;
        if(totalItems%size==0){
            totalPages = totalItems/size;
        }
        else {
            totalPages = totalItems/size+1;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("messages", messageDTOS);
        map.put("currentPage", page);
        map.put("totalItems",totalItems);

        map.put("totalPages",totalPages);
        return map;
    }

    @Override
    public void readMessage(int id, String email) throws NotFoundException {
        Message message = messageRepository.getMessageByEmailAndId(email,id);
        if(message == null){
            throw new NotFoundException("Not found message");
        }
        message.setStatus((byte) 0);
        messageRepository.save(message);
    }
}
