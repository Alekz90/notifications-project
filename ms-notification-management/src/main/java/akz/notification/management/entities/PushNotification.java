package akz.notification.management.entities;

import akz.notification.management.dto.NotificationDto;
import akz.notification.management.entities.abstracts.AbstractNotification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Notification entity class representing a notification record in the database.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "push_notifications")
@Table(
  comment = "Table for storing push notification records",
  indexes = {
    @Index(name = "push_idx_user_id", columnList = "user_id, deleted"),
    @Index(name = "push_idx_created_at", columnList = "created_at")
  }
)
public class PushNotification extends AbstractNotification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 200)
  private String body;
  @Column(length = 100)
  private String recipient;

  public PushNotification(Long userId, NotificationDto.Register notificationDto) {
    super(userId, notificationDto.title());
    this.body = notificationDto.body();
    this.recipient = notificationDto.recipient();
  }

  /**
   * Builds a push notification entity from the provided user ID and NotificationDto.Register object.
   *
   * @param userId the ID of the user associated with the notification
   * @param notificationDto the DTO containing notification details
   * @return a new Notification entity
   */
  public static PushNotification create(Long userId, NotificationDto.Register notificationDto) {
    return new PushNotification(userId, notificationDto);
  }

  /**
   * Modifies the current push notification entity with the details from the provided NotificationDto.Register object.
   * @param notificationDto the DTO containing notification details
   */
  public void modify(NotificationDto.Register notificationDto) {
    this.title = notificationDto.title();
    this.body = notificationDto.body();
    this.recipient = notificationDto.recipient();
  }
}
