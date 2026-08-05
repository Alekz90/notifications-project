package akz.notification.management.dto;

import akz.notification.management.entities.EmailNotification;
import akz.notification.management.entities.PushNotification;
import akz.notification.management.entities.SMSNotification;
import akz.notification.management.util.enums.EMessageStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class NotificationDto {

  @Schema(name = "NotificationDto.Register", description = "DTO for registering a new notification")
  public record Register(String title, String body, String recipient) {}

  @Schema(name = "NotificationDto.Response", description = "DTO for replying with the notification data")
  public record Response(Long id, String title, String body, String recipient, EMessageStatus status, LocalDateTime sentAt, LocalDateTime updatedAt) {

    public static Response fromEntity(PushNotification push) {
      return new Response(
        push.getId(), push.getTitle(), push.getBody(), push.getRecipient(), push.getStatus(), push.getSendAt(),
        push.getUpdatedAt()
      );
    }

    public static Response fromEntity(SMSNotification sms) {
      return new Response(
        sms.getId(), sms.getTitle(), sms.getBody(), sms.getRecipient(), sms.getStatus(), sms.getSendAt(),
        sms.getUpdatedAt()
      );
    }

    public static Response fromEntity(EmailNotification email) {
      return new Response(
        email.getId(), email.getTitle(), email.getBody(), email.getRecipient(), email.getStatus(), email.getSendAt(),
        email.getUpdatedAt()
      );
    }
  }
}
