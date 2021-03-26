package com.sapo.qlsc.service.impl;

import com.sapo.qlsc.converter.MaintenanceCardConverter;
import com.sapo.qlsc.converter.MaintenanceCardDetailConverter;
import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.dto.MaintenanceCardDetailDTO;
import com.sapo.qlsc.entity.*;
import com.sapo.qlsc.exception.commonException.NotFoundException;
import com.sapo.qlsc.exception.maintenanceCardException.NotFoundRepairmanException;
import com.sapo.qlsc.model.MessageModel;
import com.sapo.qlsc.repository.MaintenanceCardDetailRepository;
import com.sapo.qlsc.repository.MessageRepository;
import com.sapo.qlsc.repository.UserRepository;
import com.sapo.qlsc.service.MaintenanceCardDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MaintenanceCardDetailServiceImpl implements MaintenanceCardDetailService {

    @Autowired
    private MaintenanceCardDetailRepository maintenanceCardDetailRepository;

    @Autowired
    private MaintenanceCardDetailConverter maintenanceCardDetailConverter;

    @Autowired
    private MaintenanceCardConverter maintenanceCardConverter;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public MaintenanceCardDTO updateStatusMaintenanceCardDetail(Long id,String email) throws NotFoundException, NotFoundRepairmanException {
        Date now = new Date();
        MaintenanceCardDetail maintenanceCardDetail = maintenanceCardDetailRepository.findById(id).orElse(null);
        MaintenanceCard maintenanceCard = maintenanceCardDetail.getMaintenanceCard();
        if(maintenanceCard.getRepairman() == null || maintenanceCard.getRepairman().getEmail() == email){
            throw new NotFoundException("Not found maintenance card detail");
        }
        byte status = 1;
        boolean check = true;
        if(maintenanceCardDetail != null && maintenanceCard.getRepairman() != null){
            if(maintenanceCardDetail.getStatus() <2 && maintenanceCardDetail.getProduct().getType() == 2){
                maintenanceCardDetail.setStatus((byte) (maintenanceCardDetail.getStatus()+1));
                MaintenanceCardDetailStatusHistory maintenanceCardDetailStatusHistory = new MaintenanceCardDetailStatusHistory();
                maintenanceCardDetailStatusHistory.setCreatedDate(now);
                maintenanceCardDetailStatusHistory.setModifiedDate(now);
                maintenanceCardDetailStatusHistory.setMaintenanceCardDetail(maintenanceCardDetail);
                maintenanceCardDetailStatusHistory.setStatus((byte) (maintenanceCardDetail.getStatus()));
                maintenanceCardDetail.getMaintenanceCardDetailStatusHistories().add(maintenanceCardDetailStatusHistory);
                if(maintenanceCardDetail.getProduct().getType() == 2) {
                    if (maintenanceCardDetail.getStatus() == 1 || maintenanceCardDetail.getStatus() == 2) {
                        status = 1;
                    }
                    if (maintenanceCardDetail.getStatus() != 2) {
                        check = false;
                    }
                }
            }
            for(MaintenanceCardDetail maintenanceCardDetail1 : maintenanceCard.getMaintenanceCardDetails()){
                if(maintenanceCardDetail1.getId() != maintenanceCardDetail.getId()){
                    if(maintenanceCardDetail1.getProduct().getType() == 2) {
                        if (maintenanceCardDetail1.getStatus() == 1 || maintenanceCardDetail1.getStatus() == 2) {
                            status = 1;
                        }
                        if (maintenanceCardDetail1.getStatus() != 2) {
                            check = false;
                        }
                    }
                }
            }
            if(check){
                maintenanceCard.setWorkStatus((byte) 2);
            }
            else{
                maintenanceCard.setWorkStatus(status);
            }
            MaintenanceCardDetail maintenanceCardDetail1 = maintenanceCardDetailRepository.save(maintenanceCardDetail);
            MaintenanceCard maintenanceCard1 =  maintenanceCardDetail1.getMaintenanceCard();
            MessageModel messageModel = new MessageModel();
            if(maintenanceCard1.getWorkStatus() ==2 && maintenanceCard1.getPayStatus() == 0 ){
                messageModel.setType(2);
                messageModel.setMessage(maintenanceCard.getId().toString());
                for(User user : userRepository.getAllManager()){
                    Message message = new Message();
                    message.setStatus((byte) 1);
                    message.setUrl("/admin/maintenanceCards/"+maintenanceCard.getId().toString());
                    message.setTitle("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() +" đã được cập nhật");
                    message.setContent("Phiếu sửa chữa "+ maintenanceCard.getCode().toUpperCase() +" đã được cập nhật");
                    message.setUser(user);
                    message.setCreatedDate(now);
                    message.setModifiedDate(now);
                    messageRepository.save(message);
                    simpMessagingTemplate.convertAndSend("/topic/messages/" + user.getId(), messageModel);
                }
            }
            else{
                messageModel.setType(3);
                messageModel.setMessage(maintenanceCard.getId().toString());
                messageModel.setCode(maintenanceCard.getCode().toString());
                for(User user : userRepository.getAllManager()){
                    Message message = new Message();
                    message.setStatus((byte) 1);
                    message.setUrl("/admin/maintenanceCards/"+maintenanceCard.getId().toString());
                    message.setTitle("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() +" đang chờ thanh toán");
                    message.setContent("Phiếu sửa chữa "+ maintenanceCard.getCode().toUpperCase() +" đã hoàn thành sửa chữa và đang chờ thanh toán");
                    message.setUser(user);
                    message.setCreatedDate(now);
                    message.setModifiedDate(now);
                    messageRepository.save(message);
                    simpMessagingTemplate.convertAndSend("/topic/messages/" + user.getId(), messageModel);
                }
            }
            messageModel.setType(3);
            messageModel.setMessage(maintenanceCard.getId().toString());
            messageModel.setCode(maintenanceCard.getCode().toString());
//            if(maintenanceCard1.getRepairman() != null){
//                simpMessagingTemplate.convertAndSend("/topic/messages/" + maintenanceCard1.getRepairman().getId(), messageModel);
//            }
            if(maintenanceCard1.getCoordinator() != null){
                simpMessagingTemplate.convertAndSend("/topic/messages/" + maintenanceCard1.getCoordinator().getId(), messageModel);
            }
            return maintenanceCardConverter.convertAllToDTO(maintenanceCard1);
        }
        else if(maintenanceCard.getRepairman() != null) {
            throw new NotFoundException("Not found maintenance card detail");
        }
        else{
            throw new NotFoundRepairmanException("");
        }
    }
}
