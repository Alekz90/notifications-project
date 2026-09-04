package akz.notification.management.entities;

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

  /**
   * Constructor for creating a new PushNotification instance.
   *
   * @param userId    the ID of the user associated with the notification
   * @param title     the title of the notification
   * @param body      the body content of the notification
   * @param recipient the recipient's identifier for the push notification
   */
  public PushNotification(Long userId, String title, String body, String recipient) {
    super(userId, title);
    this.body = body;
    this.recipient = recipient;
  }
}
