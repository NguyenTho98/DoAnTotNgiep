package com.doan.user.converter;

import com.doan.user.dto.UserDTO;
import com.doan.user.entity.User;
import com.doan.user.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private MessageService messageService;

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setCode(user.getCode());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
//        userDTO.setPassword(user.getPassword());
        userDTO.setStatus(user.getStatus());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setModifiedDate(user.getModifiedDate());
        userDTO.setId(user.getId());
        userDTO.setAddress(user.getAddress());
        userDTO.setRole(user.getRole());
        userDTO.setMessageNumber(messageService.countMessageByUserId(user.getId()));
        userDTO.setTotalMaintenanceCard(user.getTotalMaintenanceCard());
        return userDTO;
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setCode(userDTO.getCode());
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        user.setStatus(userDTO.getStatus());
        user.setCreatedDate(userDTO.getCreatedDate());
        user.setModifiedDate(userDTO.getModifiedDate());
        user.setId(userDTO.getId());
        user.setAddress(userDTO.getAddress());
        user.setRole(userDTO.getRole());
        return user;
    }

    public UserDTO convertToUserDTOGetAllDependencies(User user) {
        UserDTO DTO = new UserDTO();
        DTO.setId(user.getId());
        DTO.setCode(user.getCode());
        DTO.setEmail(user.getEmail());
        DTO.setFullName(user.getFullName());
        DTO.setPhoneNumber(user.getPhoneNumber());
//        DTO.setPassword(user.getPassword());
        DTO.setStatus(user.getStatus());
        DTO.setCreatedDate(user.getCreatedDate());
        DTO.setModifiedDate(user.getModifiedDate());
        DTO.setAddress(user.getAddress());
        DTO.setRole(user.getRole());
//        DTO.setRepairmanWarrantyCards(maintenanceCardConverter.convertToMaintenanceCardDTOList(user.getRepairmanMaintenanceCards()));
        return DTO;
    }
}
