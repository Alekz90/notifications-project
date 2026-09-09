package akz.notification.management.dto;

import akz.notification.management.entities.EmailNotification;
import akz.notification.management.entities.PushNotification;
import akz.notification.management.entities.SMSNotification;
import akz.notification.management.util.enums.EMessageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationModel {
  private Long id;
  private String title;
  private String body;
  private String from;
  private String to;
  private EMessageStatus status;
  private Long userId;
  private boolean deleted;
  private LocalDateTime sentAt;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  /**
   * Converts an EmailNotification entity to a NotificationModel DTO.
   *
   * @param email the EmailNotification entity to convert
   * @return a NotificationModel DTO representing the email notification
   */
  public static NotificationModel fromEntity(EmailNotification email) {
    return NotificationModel.builder()
      .userId(email.getUserId())
      .id(email.getId())
      .title(email.getTitle())
      .body(email.getBody())
      .from(email.getSender())
      .to(email.getReceiver())
      .status(email.getStatus())
      .deleted(email.isDeleted())
      .sentAt(email.getSentAt())
      .createdAt(email.getCreatedAt())
      .updatedAt(email.getUpdatedAt())
      .build();
  }

  /**
   * Converts a NotificationModel DTO to an EmailNotification entity.
   *
   * @param model the NotificationModel DTO to convert
   * @return an EmailNotification entity representing the notification model
   */
  public static EmailNotification toEntityEmail(NotificationModel model) {
    return new EmailNotification(model.getUserId(), model.getTitle(), model.getBody(), model.getFrom(), model.getTo());
  }

  /**
   * Converts an SMSNotification entity to a NotificationModel DTO.
   *
   * @param sms the SMSNotification entity to convert
   * @return a NotificationModel DTO representing the SMS notification
   */
  public static NotificationModel fromEntity(SMSNotification sms) {
    return NotificationModel.builder()
      .userId(sms.getUserId())
      .id(sms.getId())
      .title(sms.getTitle())
      .body(sms.getBody())
      .from(sms.getSender())
      .to(sms.getReceiver())
      .status(sms.getStatus())
      .deleted(sms.isDeleted())
      .sentAt(sms.getSentAt())
      .createdAt(sms.getCreatedAt())
      .updatedAt(sms.getUpdatedAt())
      .build();
  }

  /**
   * Converts a NotificationModel DTO to an SMSNotification entity.
   *
   * @param model the NotificationModel DTO to convert
   * @return an SMSNotification entity representing the notification model
   */
  public static SMSNotification toEntitySMS(NotificationModel model) {
    return new SMSNotification(model.getUserId(), model.getTitle(), model.getBody(), model.getFrom(), model.getTo());
  }

  /**
   * Converts a PushNotification entity to a NotificationModel DTO.
   *
   * @param push the PushNotification entity to convert
   * @return a NotificationModel DTO representing the push notification
   */
  public static NotificationModel fromEntity(PushNotification push) {
    return NotificationModel.builder()
      .userId(push.getUserId())
      .id(push.getId())
      .title(push.getTitle())
      .body(push.getBody())
      .from(push.getSender())
      .to(push.getReceiver())
      .status(push.getStatus())
      .deleted(push.isDeleted())
      .sentAt(push.getSentAt())
      .createdAt(push.getCreatedAt())
      .updatedAt(push.getUpdatedAt())
      .build();
  }

  /**
   * Converts a NotificationModel DTO to a PushNotification entity.
   *
   * @param model the NotificationModel DTO to convert
   * @return a PushNotification entity representing the notification model
   */
  public static PushNotification toEntityPush(NotificationModel model) {
    return new PushNotification(model.getUserId(), model.getTitle(), model.getBody(), model.getFrom(), model.getTo());
  }

  /**
   * Modifies the current notification model with the details from the provided NotificationDto.Register object.
   *
   * @param notificationDto the DTO containing notification details
   */
  public void modify(NotificationDto.Register notificationDto) {
    this.title = notificationDto.title();
    this.body = notificationDto.body();
    this.from = notificationDto.from();
    this.to = notificationDto.to();
  }
}
