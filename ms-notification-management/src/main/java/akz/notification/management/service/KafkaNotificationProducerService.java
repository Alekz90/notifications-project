package akz.notification.management.service;

import akz.notification.management.dto.kafka.KafkaNotificationSendingDto;
import akz.notification.management.service.interfaces.IKafkaNotificationProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static akz.notification.management.util.Constants.KAFKA_TOPIC_SENDING_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaNotificationProducerService implements IKafkaNotificationProducerService {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void sendNotificationEvent(KafkaNotificationSendingDto notificationData) {
      kafkaTemplate.send(KAFKA_TOPIC_SENDING_NAME, notificationData.getId().toString(), notificationData);
  }
}
