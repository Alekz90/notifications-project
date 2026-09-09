package akz.notification.management.entities;

import akz.notification.management.entities.abstracts.AbstractNotification;
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

  @Column(length = 15)
  private String sender;

  @Column(length = 15)
  private String receiver;

  /**
   * Constructor for creating a new SMSNotification instance.
   *
   * @param userId    the ID of the user associated with the notification
   * @param title     the title of the notification
   * @param body      the body content of the notification
   * @param from the from's phone number for the SMS notification
   * @param to the to's phone number for the SMS notification
   */
  public SMSNotification(Long userId, String title, String body, String from, String to) {
    super(userId, title);
    this.body = body;
    this.sender = from;
    this.receiver = to;
  }
}
