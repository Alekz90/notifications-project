package akz.notification.management.service;

import akz.commonutils.util.CommonUtils;
import akz.notification.management.dto.NotificationDto;
import akz.notification.management.entities.SMSNotification;
import akz.notification.management.exceptions.CustomException;
import akz.notification.management.repositories.SMSNotificationRepository;
import akz.notification.management.service.interfaces.INotificationService;
import akz.notification.management.util.enums.EError;
import akz.notification.management.util.enums.EMessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static akz.notification.management.util.Constants.SMS_SERVICE_NAME;

@Service(SMS_SERVICE_NAME)
@RequiredArgsConstructor
public class SMSNotificationService implements INotificationService {

  private final SMSNotificationRepository repository;

  /**
   * Get a notification by its ID.
   *
   * @param id notification ID
   * @return notification response
   */
  @Override
  public NotificationDto.Response getById(Long id) {
    return NotificationDto.Response.fromEntity(this.findById(id));
  }

  /**
   * Get all notifications for a specific user by their user ID.
   *
   * @param userId user ID
   * @return list of notification responses
   */
  @Override
  public List<NotificationDto.Response> getAllByUserId(Long userId) {
    return repository.findByUserIdAndDeletedFalse(userId).stream()
      .map(NotificationDto.Response::fromEntity)
      .toList();
  }

  /**
   * Create a new notification.
   *
   * @param userId User ID
   * @param notificationDto notification data
   * @return notification response
   */
  @Override
  public NotificationDto.Response create(Long userId, NotificationDto.Register notificationDto) {
    return NotificationDto.Response.fromEntity(repository.save(SMSNotification.create(userId, notificationDto)));
  }

  /**
   * Update an existing notification by its ID.
   *
   * @param id notification ID
   * @param notificationDto notification data
   * @param updatedAt updated date
   * @return notification response
   */
  @Override
  public NotificationDto.Response update(Long id, LocalDateTime updatedAt, NotificationDto.Register notificationDto) {
    SMSNotification notification = this.findById(id);
    this.validateModify(notification, updatedAt);
    notification.modify(notificationDto);

    return NotificationDto.Response.fromEntity(this.update(notification));
  }

  /**
   * Send a notification by its ID.
   * @param updatedAt updated date
   *
   * @param id notification ID
   */
  @Override
  public void send(Long id, LocalDateTime updatedAt) {
    SMSNotification notification = this.findById(id);
    this.validateModify(notification, updatedAt);
    notification.setStatus(EMessageStatus.SENT);
    notification.setUpdatedAt(CommonUtils.getCurrentLocalDateTime());

    this.update(notification);
  }

  /**
   * Delete a notification by its ID.
   *
   * @param id notification ID
   * @param updatedAt updated date
   */
  @Override
  public void delete(Long id, LocalDateTime updatedAt) {
    SMSNotification notification = this.findById(id);
    this.validateModify(notification, updatedAt);
    notification.setDeleted(true);
    notification.setUpdatedAt(CommonUtils.getCurrentLocalDateTime());

    this.update(notification);
  }

  private SMSNotification findById(Long id) {
    return repository.findByIdAndDeletedFalse(id)
      .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, EError.NOTIFICATION_NOT_FOUND));
  }

  private SMSNotification update(SMSNotification notification) {
    notification.setUpdatedAt(CommonUtils.getCurrentLocalDateTime());
    return repository.save(notification);
  }

  private void validateModify(SMSNotification notification, LocalDateTime updatedAt) {
    CommonUtils.validateUpdatedRecord(notification.getUpdatedAt(), updatedAt);
    if (EMessageStatus.PENDING != notification.getStatus()) {
      throw new CustomException(HttpStatus.CONFLICT, EError.SENT_NOTIFICATION);
    }
    if (notification.isDeleted()) {
      throw new CustomException(HttpStatus.CONFLICT, EError.DELETED_NOTIFICATION);
    }
  }
}
