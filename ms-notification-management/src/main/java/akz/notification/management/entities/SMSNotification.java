package akz.notification.management.entities;

import akz.notification.management.dto.NotificationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Notification entity class representing a sms record in the database.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sms_notifications")
@Table(
  comment = "Table for storing sms records",
  indexes = {
    @Index(name = "sms_idx_user_id", columnList = "user_id, deleted"),
    @Index(name = "sms_idx_created_at", columnList = "created_at")
  }
)
public class SMSNotification extends AbstractNotification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 160)
  private String body;

  @Column(length = 10)
  private String recipient;

  public SMSNotification(Long userId, NotificationDto.Register notificationDto) {
    super(userId, notificationDto.title());
    this.body = notificationDto.body();
    this.recipient = notificationDto.recipient();
  }

  /**
   * Builds a SMS notification entity from the provided user ID and NotificationDto.Register object.
   *
   * @param userId the ID of the user associated with the notification
   * @param notificationDto the DTO containing notification details
   * @return a new Notification entity
   */
  public static SMSNotification create(Long userId, NotificationDto.Register notificationDto) {
    return new SMSNotification(userId, notificationDto);
  }

  /**
   * Modifies the current SMS notification entity with the details from the provided NotificationDto.Register object.
   * @param notificationDto the DTO containing notification details
   */
  public void modify(NotificationDto.Register notificationDto) {
    this.title = notificationDto.title();
    this.body = notificationDto.body();
    this.recipient = notificationDto.recipient();
  }
}
