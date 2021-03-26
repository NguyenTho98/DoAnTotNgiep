package com.sapo.qlsc.converter;

import com.sapo.qlsc.dto.MessageDTO;
import com.sapo.qlsc.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {

    public MessageDTO convertToDTO(Message message){
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
