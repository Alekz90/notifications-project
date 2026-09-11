package akz.notification.management.entities.abstracts;

import akz.commonutils.util.CommonUtils;
import akz.notification.management.dto.NotificationModel;
import akz.notification.management.util.enums.EStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
public abstract class AbstractNotification {

  @Column(length = 150)
  protected String title;
  @Column(length = 10)
  @Enumerated(EnumType.STRING)
  protected EStatus status;
  @Column(name = "user_id")
  protected Long userId;
  protected boolean deleted;
  @Column(name = "sent_at")
  protected LocalDateTime sentAt;
  @Column(name = "created_at")
  protected LocalDateTime createdAt;
  @Column(name = "updated_at")
  protected LocalDateTime updatedAt;

  /**
   * Constructs a new AbstractNotification entity with default values.
   * The default values are:
   * - deleted: false
   * - status: PENDING
   * - sentAt: LocalDateTime with zero time (00:00)
   * - createdAt: current local date and time
   * - updatedAt: current local date and time
   */
  protected AbstractNotification() {
    this.deleted = false;
    this.status = EStatus.PENDING;
    this.sentAt = CommonUtils.getDateTimeZero();
    this.createdAt = CommonUtils.getCurrentLocalDateTime();
    this.updatedAt = CommonUtils.getCurrentLocalDateTime();
  }

  /**
   * Constructs a new AbstractNotification entity with the specified NotificationModel.
   * @param model the NotificationModel containing the user ID and title
   */
  protected AbstractNotification(NotificationModel model) {
    this();
    this.userId = model.getUserId();
    this.title = model.getTitle();
  }

  /**
   * Updates the properties of this AbstractNotification with the specified NotificationModel.
   * @param model the NotificationModel containing the updated values
   */
  protected void update(NotificationModel model) {
    this.title = model.getTitle();
    this.status = model.getStatus();
    this.deleted = model.isDeleted();
    this.sentAt = model.getSentAt();
    this.updatedAt = CommonUtils.getCurrentLocalDateTime();
  }
}
