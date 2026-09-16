package akz.notification.management.service.interfaces;

import akz.notification.management.dto.kafka.KafkaNotificationResponseDto;
import org.springframework.kafka.annotation.KafkaListener;

import static akz.notification.management.util.Constants.*;

public interface IKafkaNotificationConsumerService {

  @KafkaListener(topics = KAFKA_TOPIC_STATUS_NAME, groupId = KAFKA_GROUP_ID)
  void receiveResponseEvent(KafkaNotificationResponseDto notificationData);
}
