package akz.notifications.services.interfaces;

import akz.notifications.dto.kafka.KafkaNotificationResponseDto;
import akz.notifications.dto.kafka.KafkaNotificationSendingDto;

public interface INotificationService {
  KafkaNotificationResponseDto send(KafkaNotificationSendingDto notification);
}
