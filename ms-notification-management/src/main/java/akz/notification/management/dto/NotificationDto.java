package akz.notification.management.dto;

import akz.commonutils.annotation.ValidEmail;
import akz.commonutils.annotation.ValidPhone;
import akz.commonutils.annotation.ValidSpecialText;
import akz.commonutils.annotation.ValidTitleText;
import akz.notification.management.entities.EmailNotification;
import akz.notification.management.entities.PushNotification;
import akz.notification.management.entities.SMSNotification;
import akz.notification.management.util.enums.ECanal;
import akz.notification.management.util.enums.EMessageStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import akz.notification.management.dto.validations.NotificationGroup.*;

public class NotificationDto {

  @Schema(name = "NotificationDto.Register", description = "DTO for registering a new notification")
  public record Register(
    @NotBlank(groups = DefaultGroup.class, message = "Title is required")
    @Size(groups = DefaultGroup.class, max = 150, message = "Title must be less than 150 characters")
    @ValidTitleText(groups = DefaultGroup.class, message = "Title contains invalid characters")
    String title,

    @NotBlank(groups = DefaultGroup.class, message = "Body is required")
    @Size(groups = SaveEmail.class, max = 500, message = "Body must be less than 500 characters")
    @Size(groups = SaveSMS.class, max = 160, message = "Body must be less than 160 characters")
    @Size(groups = SavePush.class, max = 200, message = "Body must be less than 200 characters")
    @ValidSpecialText(groups = DefaultGroup.class, message = "Body contains invalid characters")
    String body,

    @NotBlank(groups = DefaultGroup.class, message = "Recipient is required")
    @Size(max = 150, groups = {SaveEmail.class, SavePush.class}, message = "Recipient must be less than 150 characters")
    @ValidEmail(groups = SaveEmail.class, message = "Recipient must be a valid email address")
    @ValidPhone(groups = SaveSMS.class, message = "Recipient must be a valid phone number")
    String recipient,

    @NotNull(groups = DefaultGroup.class, message = "Canal is required")
    ECanal canal,

    @NotNull(groups = { UpdateEmail.class, UpdateSMS.class, UpdatePush.class }, message = "Updated date is required")
    LocalDateTime updatedAt
  ) {
    public static NotificationModel toNotificationModel(Long userId, NotificationDto.Register register) {
      return NotificationModel.builder()
        .userId(userId)
        .title(register.title())
        .body(register.body())
        .recipient(register.recipient())
        .build();
    }
  }

  @Schema(name = "NotificationDto.Entity", description = "DTO for representing the notification entity")
  public record Entity(Long id, String title, String body, String recipient, EMessageStatus status, Long userId,
                       boolean deleted, LocalDateTime sentAt, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static Entity fromEntity(PushNotification push) {
      return new Entity(
        push.getId(), push.getTitle(), push.getBody(), push.getRecipient(), push.getStatus(), push.getUserId(),
        push.isDeleted(), push.getSentAt(), push.getCreatedAt(), push.getUpdatedAt()
      );
    }

    public static Entity fromEntity(SMSNotification sms) {
      return new Entity(
        sms.getId(), sms.getTitle(), sms.getBody(), sms.getRecipient(), sms.getStatus(), sms.getUserId(),
        sms.isDeleted(), sms.getSentAt(), sms.getCreatedAt(), sms.getUpdatedAt()
      );
    }

    public static Entity fromEntity(EmailNotification email) {
      return new Entity(
        email.getId(), email.getTitle(), email.getBody(), email.getRecipient(), email.getStatus(), email.getUserId(),
        email.isDeleted(), email.getSentAt(), email.getCreatedAt(), email.getUpdatedAt()
      );
    }
  }

  @Schema(name = "NotificationDto.Response", description = "DTO for replying with the notification data")
  public record Response(Long id, String title, String body, String recipient, EMessageStatus status,
                         LocalDateTime sentAt, LocalDateTime updatedAt) {

    public static Response fromEntity(NotificationModel email) {
      return new Response(
        email.getId(), email.getTitle(), email.getBody(), email.getRecipient(), email.getStatus(), email.getSentAt(),
        email.getUpdatedAt()
      );
    }
  }
}
