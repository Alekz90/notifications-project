package akz.notification.management.entities.abstracts;

import akz.commonutils.util.CommonUtils;
import akz.notification.management.util.enums.EMessageStatus;
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
  protected EMessageStatus status;
  @Column(name = "user_id")
  protected Long userId;
  protected boolean deleted;
  @Column(name = "sent_at")
  protected LocalDateTime sentAt;
  @Column(name = "created_at")
  protected LocalDateTime createdAt;
  @Column(name = "updated_at")
  protected LocalDateTime updatedAt;

  protected AbstractNotification() {
    this.deleted = false;
    this.status = EMessageStatus.PENDING;
    this.sentAt = CommonUtils.getDateTimeZero();
    this.createdAt = CommonUtils.getCurrentLocalDateTime();
    this.updatedAt = CommonUtils.getCurrentLocalDateTime();
  }

  protected AbstractNotification(Long userId, String title) {
    this();
    this.userId = userId;
    this.title = title;
  }
}
