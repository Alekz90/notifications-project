package akz.notifications.remoto.services;

import akz.notifications.exception.CustomException;
import akz.notifications.remoto.dto.SMSMessageDto;
import akz.notifications.remoto.feign.ISMSRemoteFeign;
import akz.notifications.util.enums.EError;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SMSRemoteService {

  private static final String NEWS_INSTANCE = "sms-instance";
  private final ISMSRemoteFeign smsRemoteFeign;

  /**
   * Sends an SMS message using the remote SMS API.
   *
   * @param message The SMS message to be sent.
   */
  @CircuitBreaker(name = NEWS_INSTANCE, fallbackMethod = "failSendSMS")
  public void sendSMS(SMSMessageDto message) {
    smsRemoteFeign.sendSMS(message);
  }

  /**
   * Fallback method for sendSMS in case of failure.
   *
   * @param message The SMS message that failed to send.
   * @param t       The throwable that caused the failure.
   */
  void failSendSMS(SMSMessageDto message, Throwable t) {
    log.error("Failed to send SMS with message: {}, error: {}", message.toString(), t.getMessage());
    throw new CustomException(EError.ERROR_SMS_SENDING);
  }
}
