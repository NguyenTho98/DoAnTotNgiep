package com.doan.maintenancecard.kafka;

import com.doan.maintenancecard.converter.MaintenanceCardConverter;
import com.doan.maintenancecard.entity.MaintenanceCard;
import com.doan.maintenancecard.model.MessageModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendToClient {

    private final ObjectMapper json;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MaintenanceCardConverter maintenanceCardConverter;
    private static final String TOPIC_MESSAGE = "dk3w4sws-message";

    public void sendNotificationToClient(MaintenanceCard maintenanceCard, int type, String email) {
        try {
            MessageModel messageModel = new MessageModel();
            messageModel.setMaintenanceCard(json.writeValueAsString(maintenanceCardConverter.convertToDTO(maintenanceCard)));
            messageModel.setMaintenanceCardCode(maintenanceCard.getCode());
            messageModel.setAuthor(email);
            messageModel.setCoordinatorEmail(maintenanceCard.getCoordinatorEmail());
            messageModel.setRepairmanEmail(maintenanceCard.getRepairmanEmail());
            messageModel.setType(type);
            String message = json.writeValueAsString(messageModel);
            kafkaTemplate.send(TOPIC_MESSAGE, String.valueOf(maintenanceCard.getId()), message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
