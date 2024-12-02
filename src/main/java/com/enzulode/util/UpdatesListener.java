package com.enzulode.util;

import com.enzulode.dto.EntityUpdateNotificationDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdatesListener {

  private final SimpMessagingTemplate messagingTemplate;

  public UpdatesListener(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  @RabbitListener(queues = "#{updatesQueue.name}")
  public void listenUpdates(Message<EntityUpdateNotificationDto> updateData) {
    messagingTemplate.convertAndSend("/topic/updates", updateData.getPayload());
  }
}
