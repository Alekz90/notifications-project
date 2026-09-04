package akz.notification.management.entities;

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

  /**
   * Constructs a new EmailNotification with the specified user ID, title, body, and recipient.
   *
   * @param userId    the ID of the user associated with this notification
   * @param title     the title of the notification
   * @param body      the body content of the email notification
   * @param recipient the recipient's email address
   */
  public EmailNotification(Long userId, String title, String body, String recipient) {
    super(userId, title);
    this.body = body;
    this.recipient = recipient;
  }
}
