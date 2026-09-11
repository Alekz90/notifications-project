package akz.notification.management.service.interfaces;

import akz.notification.management.dto.kafka.KafkaNotificationSendingDto;

public interface IKafkaNotificationProducerService {
  void sendNotificationEvent(KafkaNotificationSendingDto notificationData);
}
