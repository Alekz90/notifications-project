package akz.notification.management.service;

import akz.notification.management.dto.kafka.KafkaNotificationResponseDto;
import akz.notification.management.service.interfaces.IKafkaNotificationConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaNotificationConsumerService implements IKafkaNotificationConsumerService {

  private final NotificationService notificationService;

  @Override
  public void receiveResponseEvent(KafkaNotificationResponseDto notificationData) {
    notificationService.updateStatusSending(notificationData);
  }
}
