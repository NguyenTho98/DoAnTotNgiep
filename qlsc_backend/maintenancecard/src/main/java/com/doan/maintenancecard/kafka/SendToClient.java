package com.doan.maintenancecard.kafka;

import com.doan.maintenancecard.entity.MaintenanceCard;
import com.doan.maintenancecard.model.MessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendToClient {

    private final SimpMessagingTemplate template;
    private final ObjectMapper json;
    private static final String BASE_URL = "http://localhost:8686/maintenance-card/detail/";
    private static final String MC = "Phiếu sửa chữa";

    public void sendNotificationToClient(MaintenanceCard maintenanceCard, int type) {
        MessageModel messageModel = new MessageModel();
        messageModel.setRepairmanId(maintenanceCard.getRepairmanId());
        messageModel.setId(maintenanceCard.getId());
        messageModel.setMaintenanceCardCode(maintenanceCard.getCode());
        messageModel.setAuthor(maintenanceCard.getCoordinatorEmail());
        messageModel.setCoordinatorEmail(maintenanceCard.getCoordinatorEmail());
        messageModel.setRepairmanEmail(maintenanceCard.getRepairmanEmail());
        if (type == 1) titleRepairman(messageModel, maintenanceCard.getCode());
        if (type == 2) titleManager(messageModel, maintenanceCard.getCode());
        messageModel.setUrl(BASE_URL + maintenanceCard.getId());
        try {
            template.convertAndSend("/topic/message", json.writeValueAsString(messageModel));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void titleManager(MessageModel model, String code) {
        StringBuilder strTitle = new StringBuilder(MC).append(" ").append(code.toUpperCase());
        StringBuilder strContent = new StringBuilder(MC).append(" ").append(code.toUpperCase());
        model.setTitle(strTitle.append(" đang chờ thanh toán").toString());
        model.setContent(strContent.append(" đã hoàn thành sửa chữa và đang chờ thanh toán").toString());
    }

    private void titleRepairman(MessageModel model, String code) {
        StringBuilder strTitle = new StringBuilder(MC).append(" ").append(code.toUpperCase());
        StringBuilder strContent = new StringBuilder(MC).append(" ").append(code.toUpperCase());
        model.setTitle(strTitle.append(" đã được tạo mới").toString());
        model.setContent(strContent.append(" đã được tạo mới. Hãy bắt đầu tiến hành sửa chữa").toString());
    }
}
