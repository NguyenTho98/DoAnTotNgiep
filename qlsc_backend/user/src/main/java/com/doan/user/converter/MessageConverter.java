package com.doan.user.converter;

import com.doan.user.dto.MessageDTO;
import com.doan.user.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {

    public MessageDTO convertToDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(message.getContent());
        messageDTO.setStatus(message.getStatus());
        messageDTO.setTitle(message.getTitle());
        messageDTO.setUrl(message.getUrl());
        messageDTO.setCreatedDate(message.getCreatedDate());
        messageDTO.setId(message.getId());
        messageDTO.setModifiedDate(message.getModifiedDate());
        return messageDTO;
    }

}
