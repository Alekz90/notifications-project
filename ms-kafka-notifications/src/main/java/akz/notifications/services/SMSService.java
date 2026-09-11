package akz.notifications.services;

import akz.notifications.dto.kafka.KafkaNotificationResponseDto;
import akz.notifications.dto.kafka.KafkaNotificationSendingDto;
import akz.notifications.remoto.dto.SMSMessageDto;
import akz.notifications.remoto.services.SMSRemoteService;
import akz.notifications.services.interfaces.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static akz.notifications.util.Constants.SMS_SERVICE_NAME;

@Slf4j
@Service(SMS_SERVICE_NAME)
@RequiredArgsConstructor
public class SMSService implements INotificationService {

  private final SMSRemoteService smsRemoteService;

  @Override
  public KafkaNotificationResponseDto send(KafkaNotificationSendingDto notification) {
    try {
      smsRemoteService.sendSMS(SMSMessageDto.from(notification));
      return KafkaNotificationResponseDto.from(notification);
    } catch (Exception e) {
      log.error("Error sending SMS notification: {}", notification.toString(), e);
      return KafkaNotificationResponseDto.buildFail(notification);
    }
  }
}
