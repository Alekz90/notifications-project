package akz.notifications.services;

import akz.notifications.dto.kafka.KafkaNotificationResponseDto;
import akz.notifications.services.interfaces.IKafkaNotificationProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static akz.notifications.util.Constants.KAFKA_TOPIC_STATUS_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaNotificationProducerService implements IKafkaNotificationProducerService {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  /**
   * Sends a notification event to the Kafka topic for sending notifications.
   * @param notificationData The data of the notification to be sent.
   */
  @Override
  public void sendResponseEvent(KafkaNotificationResponseDto notificationData) {
      kafkaTemplate.send(KAFKA_TOPIC_STATUS_NAME, notificationData.getId().toString(), notificationData);
  }
}
