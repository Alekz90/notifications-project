package akz.notification.management.entities;

import akz.notification.management.dto.NotificationDto;
import akz.notification.management.entities.abstracts.AbstractNotification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Notification entity class representing a email record in the database.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "email_notifications")
@Table(
  comment = "Table for storing email records",
  indexes = {
    @Index(name = "email_idx_user_id", columnList = "user_id, deleted"),
    @Index(name = "email_idx_created_at", columnList = "created_at")
  }
)
public class EmailNotification extends AbstractNotification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 500)
  private String body;

  @Column(length = 150)
  private String recipient;

  public EmailNotification(Long userId, NotificationDto.Register notificationDto) {
    super(userId, notificationDto.title());
    this.body = notificationDto.body();
    this.recipient = notificationDto.recipient();
  }

  /**
   * Builds a email notification entity from the provided user ID and NotificationDto.Register object.
   *
   * @param userId the ID of the user associated with the notification
   * @param notificationDto the DTO containing notification details
   * @return a new Notification entity
   */
  public static EmailNotification create(Long userId, NotificationDto.Register notificationDto) {
    return new EmailNotification(userId, notificationDto);
  }

  /**
   * Modifies the current email notification entity with the details from the provided NotificationDto.Register object.
   * @param notificationDto the DTO containing notification details
   */
  public void modify(NotificationDto.Register notificationDto) {
    this.title = notificationDto.title();
    this.body = notificationDto.body();
    this.recipient = notificationDto.recipient();
  }
}
