package akz.notifications.services;

import akz.notifications.dto.kafka.KafkaNotificationResponseDto;
import akz.notifications.dto.kafka.KafkaNotificationSendingDto;
import akz.notifications.services.interfaces.INotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static akz.notifications.util.Constants.SMS_SERVICE_NAME;

@Slf4j
@Service(SMS_SERVICE_NAME)
public class SMSService implements INotificationService {

  @Override
  public KafkaNotificationResponseDto send(KafkaNotificationSendingDto notification) {
    log.info("Sending SMS notification: {}", notification.toString());
    return KafkaNotificationResponseDto.from(notification);
  }
}
