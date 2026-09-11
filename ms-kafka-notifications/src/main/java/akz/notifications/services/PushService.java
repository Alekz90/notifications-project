package akz.notifications.services;

import akz.notifications.dto.kafka.KafkaNotificationResponseDto;
import akz.notifications.dto.kafka.KafkaNotificationSendingDto;
import akz.notifications.services.interfaces.INotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static akz.notifications.util.Constants.PUSH_SERVICE_NAME;

@Slf4j
@Service(PUSH_SERVICE_NAME)
public class PushService implements INotificationService {

  @Override
  public KafkaNotificationResponseDto send(KafkaNotificationSendingDto notification) {
    log.info("Sending push notification: {}", notification.toString());
    return KafkaNotificationResponseDto.from(notification);
  }
}
