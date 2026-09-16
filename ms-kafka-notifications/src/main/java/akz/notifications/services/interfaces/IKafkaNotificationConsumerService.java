package akz.notifications.services.interfaces;

import akz.notifications.dto.kafka.KafkaNotificationSendingDto;
import org.springframework.kafka.annotation.KafkaListener;

import static akz.notifications.util.Constants.KAFKA_GROUP_ID;
import static akz.notifications.util.Constants.KAFKA_TOPIC_SENDING_NAME;

/**
 * IKafkaNotificationConsumerService is an interface that defines the contract for receiving notification events from Kafka.
 */
public interface IKafkaNotificationConsumerService {

  /**
   * Receive notification event from Kafka topic.
   * @param notificationData KafkaNotificationSendingDto
   */
  @KafkaListener(topics = KAFKA_TOPIC_SENDING_NAME, groupId = KAFKA_GROUP_ID)
  void receiveNotificationEvent(KafkaNotificationSendingDto notificationData);
}
