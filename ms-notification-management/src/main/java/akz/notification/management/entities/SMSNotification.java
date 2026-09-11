package akz.notification.management.entities;

import akz.notification.management.dto.NotificationModel;
import akz.notification.management.entities.abstracts.AbstractNotification;
import akz.notification.management.util.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

  /**
   * Updates the properties of this SMSNotification with the specified values.
   * @param model the NotificationModel containing the updated values
   */
  public SMSNotification(NotificationModel model) {
    super(model);
    this.body = model.getBody();
    this.sender = model.getTo();
    this.receiver = model.getFrom();
  }

  /**
   * Updates the properties of this SMSNotification with the specified values.
   * @param model the NotificationModel containing the updated values
   */
  public void update(NotificationModel model) {
    super.update(model);
    this.setBody(model.getBody());
    this.setSender(model.getTo());
    this.setReceiver(model.getFrom());
  }
}
