package com.sapo.qlsc.service.impl;

import com.sapo.qlsc.converter.MaintenanceCardConvert;
import com.sapo.qlsc.converter.MaintenanceCardConverter;
import com.sapo.qlsc.converter.PaymentHistoryConverter;
import com.sapo.qlsc.dto.MaintenanceCardDTO;
import com.sapo.qlsc.dto.PaymentHistoryDTO;
import com.sapo.qlsc.entity.MaintenanceCard;
import com.sapo.qlsc.entity.Message;
import com.sapo.qlsc.entity.PaymentHistory;
import com.sapo.qlsc.exception.commonException.NotFoundException;
import com.sapo.qlsc.exception.commonException.UnknownException;
import com.sapo.qlsc.exception.maintenanceCardException.MoneyExceedException;
import com.sapo.qlsc.model.MessageModel;
import com.sapo.qlsc.model.PaymentHistoryByIdCustomer;
import com.sapo.qlsc.repository.MaintenanceCardRepository;
import com.sapo.qlsc.repository.MessageRepository;
import com.sapo.qlsc.repository.PaymentHistoryRepository;
import com.sapo.qlsc.service.PaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Autowired
    private MaintenanceCardRepository maintenanceCardRepository;

    @Autowired
    private MaintenanceCardConverter maintenanceCardConverter;

    @Autowired
    private PaymentHistoryConverter paymentHistoryConverter;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public MaintenanceCardDTO insertPaymentHistory(List<PaymentHistoryDTO> paymentHistoryDTOs) throws NotFoundException, MoneyExceedException {
        Long total = Long.valueOf(0);
//        Long maintenanceCardId = Long.valueOf(0);
//        for (PaymentHistoryDTO paymentHistoryDTO : paymentHistoryDTOs) {
//            if (maintenanceCardId == 0) {
//                maintenanceCardId = paymentHistoryDTO.getMaintenanceCard().getId();
//            }
//            if (maintenanceCardId != paymentHistoryDTO.getMaintenanceCard().getId()) {
//                maintenanceCardId = Long.valueOf(-1);
//            }
//        }
        Date now = new Date();
        MaintenanceCard maintenanceCard = maintenanceCardRepository.findById(paymentHistoryDTOs.get(0).getMaintenanceCard().getId()).orElse(null);
        if (maintenanceCard != null) {
            for (PaymentHistory paymentHistory1 : maintenanceCard.getPaymentHistories()) {
                total += paymentHistory1.getMoney().longValue();
                System.out.println(total);
            }
            for (PaymentHistoryDTO paymentHistoryDTO : paymentHistoryDTOs) {
                PaymentHistory paymentHistory = paymentHistoryConverter.convertToEntity(paymentHistoryDTO);
                paymentHistory.setCreatedDate(now);
                paymentHistory.setModifiedDate(now);
                total += paymentHistory.getMoney().longValue();
                if (maintenanceCard.getPaymentHistories() == null) {
                    List<PaymentHistory> paymentHistories = new ArrayList<>();
                    paymentHistories.add(paymentHistory);
                    maintenanceCard.setPaymentHistories(paymentHistories);
                } else {
                    maintenanceCard.getPaymentHistories().add(paymentHistory);
                }
            }


            System.out.println(total);
            byte status = 1;
            if (total == maintenanceCard.getPrice().longValue()) {
                maintenanceCard.setPayStatus(status);
            }
            else if(total > maintenanceCard.getPrice().longValue()){
                throw new MoneyExceedException();
            }
            try {
                MaintenanceCard maintenanceCard1 = maintenanceCardRepository.save(maintenanceCard);
                MessageModel messageModel = new MessageModel();
                messageModel.setType(3);
                messageModel.setMessage(maintenanceCard.getId().toString());
                messageModel.setCode(maintenanceCard.getCode().toString());
                if (maintenanceCard1.getRepairman() != null) {
                    Message message = new Message();
                    message.setStatus((byte) 1);
                    message.setUrl("/admin/maintenanceCards/"+maintenanceCard.getId().toString());
                    message.setTitle("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() +" đã được cập nhật");
                    message.setContent("Phiếu sửa chữa "+ maintenanceCard.getCode().toUpperCase() +" đã được cập nhật");
                    message.setUser(maintenanceCard1.getRepairman());
                    message.setCreatedDate(now);
                    message.setModifiedDate(now);
                    messageRepository.save(message);
                    simpMessagingTemplate.convertAndSend("/topic/messages/" + maintenanceCard1.getRepairman().getId(), messageModel);
                }
                if (maintenanceCard1.getCoordinator() != null && maintenanceCard1.getCoordinator().getRole() == 1) {
                    Message message = new Message();
                    message.setStatus((byte) 1);
                    message.setUrl("/admin/maintenanceCards/"+maintenanceCard.getId().toString());
                    message.setTitle("Phiếu sửa chữa " + maintenanceCard.getCode().toUpperCase() +" đã được cập nhật");
                    message.setContent("Phiếu sửa chữa "+ maintenanceCard.getCode().toUpperCase() +" đã được cập nhật");
                    message.setUser(maintenanceCard1.getCoordinator());
                    message.setCreatedDate(now);
                    message.setModifiedDate(now);
                    messageRepository.save(message);
                    simpMessagingTemplate.convertAndSend("/topic/messages/" + maintenanceCard1.getCoordinator().getId(), messageModel);
                }
                return maintenanceCardConverter.convertAllToDTO(maintenanceCard1);
            } catch (Exception e) {
                e.printStackTrace();
                throw new UnknownException();
            }

        } else {
            throw new NotFoundException("Not found maintenance card");
        }
    }

    @Override
    public Map<String, Object> getPaymentHistoryByIdCustomer(PaymentHistoryByIdCustomer paymentHistoryByIdCustomer) {

        int pageNumber = paymentHistoryByIdCustomer.getPage();
        int size = paymentHistoryByIdCustomer.getSize();
        String search = paymentHistoryByIdCustomer.getSearch();
        Long[] payMethods = paymentHistoryByIdCustomer.getPayMethods();
        Pageable paging = PageRequest.of(pageNumber - 1, size, Sort.by("modifiedDate").descending());
        Long id = paymentHistoryByIdCustomer.getId();

        Page<PaymentHistory> historyPage = paymentHistoryRepository.getPaymentHistoryByIdCustomer(paging, id, search, payMethods);
        List<PaymentHistoryDTO> paymentHistoryDTOS = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        List<PaymentHistory> paymentHistories = historyPage.getContent();

        for (PaymentHistory paymentHistory : paymentHistories) {
            paymentHistoryDTOS.add(paymentHistoryConverter.convertPaymentHistoryDTO(paymentHistory));
        }
        map.put("paymentHistories", paymentHistoryDTOS);
        map.put("currentPage", historyPage.getNumber() + 1);
        map.put("totalItems", historyPage.getTotalElements());
        map.put("totalPages", historyPage.getTotalPages());
        return map;
    }
}






