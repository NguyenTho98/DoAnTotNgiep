package com.doan.user.converter;

import com.doan.user.dto.MaintenanceCardsModel;
import com.doan.user.dto.MessageDTO;
import com.doan.user.dto.UserDTO;
import com.doan.user.entity.MaintenanceCard;
import com.doan.user.entity.Message;
import com.doan.user.kafka.MessageTmp;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {

    public MessageDTO convertToDTO(MessageTmp message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(message.getContent());
        messageDTO.setStatus(message.getStatus());
        messageDTO.setTitle(message.getTitle());
        messageDTO.setUrl(message.getUrl());
        messageDTO.setCreatedDate(message.getCreatedDate());
        messageDTO.setId(message.getId());
        messageDTO.setUnRead(message.getUnRead());
        messageDTO.setModifiedDate(message.getModifiedDate());
        messageDTO.setMaintenanceCard(message.getMaintenanceCard());

        if (message.getUser() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(message.getUser().getId());
            messageDTO.setUser(userDTO);
        }
        return messageDTO;
    }

    public MessageDTO convertToMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(message.getContent());
        messageDTO.setStatus(message.getStatus());
        messageDTO.setTitle(message.getTitle());
        messageDTO.setUrl(message.getUrl());
        messageDTO.setCreatedDate(message.getCreatedDate());
        messageDTO.setId(message.getId());
        messageDTO.setUnRead(message.getUnRead());
        messageDTO.setModifiedDate(message.getModifiedDate());

        if (message.getUser() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(message.getUser().getId());
            messageDTO.setUser(userDTO);
        }
        return messageDTO;
    }
    public MaintenanceCardsModel getMaintenanceCardsModel(MaintenanceCard maintenanceCard) {
        MaintenanceCardsModel maintenanceCardsModel = new MaintenanceCardsModel();
        maintenanceCardsModel.setId(Math.toIntExact(maintenanceCard.getId()));
        maintenanceCardsModel.setCode(maintenanceCard.getCode());
        maintenanceCardsModel.setColor(maintenanceCard.getColor());
        maintenanceCardsModel.setCoordinatorEmail(maintenanceCard.getCoordinator().getEmail());
        maintenanceCardsModel.setCoordinatorId(maintenanceCard.getCoordinator().getId());
        maintenanceCardsModel.setCoordinatorName(maintenanceCard.getCoordinator().getName());
        maintenanceCardsModel.setCustomerId(maintenanceCard.getCustomer().getId());
        maintenanceCardsModel.setCustomerName(maintenanceCard.getCustomer().getName());
        maintenanceCardsModel.setCustomerPhone(maintenanceCard.getCustomer().getPhone());
        maintenanceCardsModel.setDescription(maintenanceCard.getDescription());
        maintenanceCardsModel.setExpectedReturnDate(maintenanceCard.getExpectedReturnDate());
        maintenanceCardsModel.setModel(maintenanceCard.getModel());
        maintenanceCardsModel.setPayStatus(maintenanceCard.getPayStatus());
        maintenanceCardsModel.setPlatesNumber(maintenanceCard.getPlatesNumber());
        maintenanceCardsModel.setPrice(maintenanceCard.getPrice());
        maintenanceCardsModel.setRepairmanEmail(maintenanceCard.getRepairman().getEmail());
        maintenanceCardsModel.setReturnDate(maintenanceCard.getReturnDate());
        maintenanceCardsModel.setWorkStatus(maintenanceCard.getWorkStatus());
        maintenanceCardsModel.setModifiedDate(maintenanceCard.getModifiedDate());
        maintenanceCardsModel.setCreatedDate(maintenanceCard.getCreatedDate());
        maintenanceCardsModel.setRepairmanName(maintenanceCard.getRepairman().getName());
        return maintenanceCardsModel;
    }
}
