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
  private String sender;

  @Column(length = 150)
  private String receiver;

  /**
   * Updates the properties of this EmailNotification with the specified values.
   * @param model the NotificationModel containing the updated values
   */
  public EmailNotification(NotificationModel model) {
    super(model);
    this.body = model.getBody();
    this.sender = model.getFrom();
    this.receiver = model.getTo();
  }

  /**
   * Updates the properties of this EmailNotification with the specified values.
   * @param model the NotificationModel containing the updated values
   */
  @Override
  public void update(NotificationModel model) {
    super.update(model);
    this.setBody(model.getBody());
    this.setSender(model.getFrom());
    this.setReceiver(model.getTo());
  }
}
