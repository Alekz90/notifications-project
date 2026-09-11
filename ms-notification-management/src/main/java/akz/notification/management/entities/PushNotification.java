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
  private String sender;

  @Column(length = 100)
  private String receiver;

  /**
   * Constructor for creating a new PushNotification instance.
   *
   * @param userId    the ID of the user associated with the notification
   * @param title     the title of the notification
   * @param body      the body content of the notification
   * @param from      the from's identifier for the push notification
   * @param to        the to's identifier for the push notification
   */
  public PushNotification(Long userId, String title, String body, String from, String to) {
    super(userId, title);
    this.body = body;
    this.sender = from;
    this.receiver = to;
  }

  /**
   * Updates the properties of this PushNotification with the specified values.
   * @param model the NotificationModel containing the updated values
   */
  public PushNotification(NotificationModel model) {
    super(model);
    this.body = model.getBody();
    this.sender = model.getTo();
    this.receiver = model.getFrom();
  }

  /**
   * Updates the properties of this PushNotification with the specified values.
   * @param model the NotificationModel containing the updated values
   */
  public void update(NotificationModel model) {
    super.update(model);
    this.setBody(model.getBody());
    this.setSender(model.getTo());
    this.setReceiver(model.getFrom());
  }
}
