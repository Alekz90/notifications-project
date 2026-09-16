package akz.notifications.services.interfaces;

import akz.notifications.dto.kafka.KafkaNotificationResponseDto;

public interface IKafkaNotificationProducerService {

  /**
   * Sends a notification response event to the Kafka topic.
   * @param notificationData The notification response data to be sent.
   */
  void sendResponseEvent(KafkaNotificationResponseDto notificationData);
}
