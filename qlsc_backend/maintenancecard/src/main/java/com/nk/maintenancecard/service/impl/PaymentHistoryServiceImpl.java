package com.nk.maintenancecard.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nk.maintenancecard.converter.MaintenanceCardConverter;
import com.nk.maintenancecard.converter.PaymentHistoryConverter;
import com.nk.maintenancecard.dto.MaintenanceCardDTO;
import com.nk.maintenancecard.dto.PaymentHistoryDTO;
import com.nk.maintenancecard.entity.MaintenanceCard;
import com.nk.maintenancecard.entity.PaymentHistory;
import com.nk.maintenancecard.exception.commonException.NotFoundException;
import com.nk.maintenancecard.exception.commonException.UnknownException;
import com.nk.maintenancecard.exception.maintenanceCardException.MoneyExceedException;
import com.nk.maintenancecard.model.MessageModel;
import com.nk.maintenancecard.model.PaymentHistoryByIdCustomer;
import com.nk.maintenancecard.repository.MaintenanceCardRepository;
import com.nk.maintenancecard.repository.PaymentHistoryRepository;
import com.nk.maintenancecard.service.PaymentHistoryService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

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
    private KafkaTemplate<String,String> kafkaTemplate;

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
                messageModel.setType(2);
                messageModel.setMaintenanceCardCode(maintenanceCard1.getCode());
                messageModel.setAuthor(maintenanceCard1.getCoordinatorEmail());
                messageModel.setCoordinatorEmail(maintenanceCard1.getCoordinatorEmail());
                messageModel.setRepairmanEmail(maintenanceCard1.getRepairmanEmail());
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(messageModel);
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("lhw3k9sy-message", maintenanceCard1.getId()+"", jsonString);
                kafkaTemplate.send(record);
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






