package com.doan.user.kafka;

import com.doan.user.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.doan.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserConsumer {

    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = {"lhw3k9sy-user"},groupId = "Group_id_1")
    @Transactional
    public void consume(@Payload String message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) throws JsonProcessingException {
        System.out.println(message);
        User user = userRepository.getOne(Long.parseLong(key));
        user.setTotalMaintenanceCard(user.getTotalMaintenanceCard()+Integer.parseInt(message));
        userRepository.save(user);
    }
//    @KafkaListener(topics = {"Kafka_json5"},groupId = "Group_json",containerFactory = "productDTOKafkaListenerContainerFactory")
//    public void consumeJson(ProductDTO productDTO){
//        System.out.println(productDTO);
////        productService.insertProduct(productDTO);
//    }

}
