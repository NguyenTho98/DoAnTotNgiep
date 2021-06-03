package com.doan.user.kafka;

import com.doan.user.entity.Message;
import com.doan.user.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.doan.user.repository.MessageRepository;
import com.doan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageConsumer {

    private final ObjectMapper json;
    private final SimpMessagingTemplate template;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private static final String MC = "Phiếu sửa chữa ";

    @KafkaListener(topics = {"dk3w4sws-message"}, groupId = "repair-manager")
    @Transactional
    public void consume(@Payload String message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        try {
            var messageModel = new ObjectMapper().readValue(message, MessageModel.class);
            if (messageModel.getType() == 2) {
                List<User> users = userRepository.getAllManager();
                if (users.isEmpty()) return;
                users.forEach(user -> {
                    if (!user.getEmail().equals(messageModel.getAuthor())) {
                        String title = MC + messageModel.getMaintenanceCardCode().toUpperCase() + " đang chờ thanh toán";
                        String content = MC + messageModel.getMaintenanceCardCode().toUpperCase() + " đã hoàn thành sửa chữa và đang chờ thanh toán";
                        setMessage(key, user, title, content);
                        sendToClient(messageModel);
                    }
                });
            } else if (messageModel.getType() == 1
                && StringUtils.isNotBlank(messageModel.getRepairmanEmail())
                && !messageModel.getRepairmanEmail().equals(messageModel.getAuthor())) {
                User user = userRepository.checkExistEmail(messageModel.getRepairmanEmail());
                if (ObjectUtils.isEmpty(user)) return;
                String title = MC + messageModel.getMaintenanceCardCode().toUpperCase() + " đã được tạo mới";
                String content = MC + messageModel.getMaintenanceCardCode().toUpperCase() + " đã được tạo mới. Hãy bắt đầu tiến hành sửa chữa";
                setMessage(key, user, title, content);
                sendToClient(messageModel);
            } else if (messageModel.getType() == 3) {
                User coordinator = userRepository.checkExistEmail(messageModel.getCoordinatorEmail());
                if (ObjectUtils.isEmpty(coordinator)) return;
                String title = MC + messageModel.getMaintenanceCardCode().toUpperCase() + " vừa được cập nhật";
                String content = MC + messageModel.getMaintenanceCardCode().toUpperCase() + " vừa được cập nhật";
                setMessage(key, coordinator, title, content);
                sendToClient(messageModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendToClient(MessageModel messageModel) {
        try {
            template.convertAndSend("/topic/message", json.writeValueAsString(messageModel));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void setMessage(String key, User user, String title, String content) {
        var newMessage = new Message();
        newMessage.setStatus((byte) 1);
        newMessage.setUrl("/admin/maintenanceCards/" + key);
        newMessage.setTitle(title);
        newMessage.setContent(content);
        newMessage.setUser(user);
        newMessage.setCreatedDate(new Date());
        newMessage.setModifiedDate(new Date());
        messageRepository.save(newMessage);
    }

}
