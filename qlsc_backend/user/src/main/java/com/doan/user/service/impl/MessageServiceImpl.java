package com.doan.user.service.impl;

import com.doan.user.dto.MessageDTO;
import com.doan.user.converter.MessageConverter;
import com.doan.user.entity.Message;
import com.doan.user.exception.commonException.NotFoundException;
import com.doan.user.repository.MessageRepository;
import com.doan.user.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageConverter messageConverter;

    @Override
    public Map<String, Object> getListMessage(String userId, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size, Sort.by("modifiedDate").descending());
        Page<Message> pageMessages = messageRepository.getMessagesByUserId(paging, Long.parseLong(userId));
        List<MessageDTO> messageDTOS = new ArrayList<>();
        List<Message> messages = pageMessages.getContent();
        messages.forEach(message -> messageDTOS.add(messageConverter.convertToDTO(message)));
        HashMap<String, Object> map = new HashMap<>();
        map.put("messages", messageDTOS);
        map.put("currentPage", pageMessages.getNumber() + 1);
        map.put("totalItems", pageMessages.getTotalElements());
        map.put("totalPages", pageMessages.getTotalPages());
        return map;
    }

    @Override
    public boolean readMessage(int id) {
        try {
            Optional<Message> message = messageRepository.findById(Long.parseLong(String.valueOf(id)));
            if (message.isPresent()) {
                Message newMessage = message.get();
                newMessage.setStatus((byte) 0);
                messageRepository.save(newMessage);
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
