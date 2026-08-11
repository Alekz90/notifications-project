package akz.notification.management.service;

import akz.commonutils.util.CommonUtils;
import akz.notification.management.dto.NotificationDto;
import akz.notification.management.entities.PushNotification;
import akz.notification.management.exceptions.CustomException;
import akz.notification.management.repositories.PushNotificationRepository;
import akz.notification.management.service.interfaces.INotificationService;
import akz.notification.management.util.enums.EError;
import akz.notification.management.util.enums.EMessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static akz.notification.management.util.Constants.PUSH_SERVICE_NAME;

@Service(PUSH_SERVICE_NAME)
@RequiredArgsConstructor
public class PushNotificationService implements INotificationService {

  private final PushNotificationRepository repository;

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
   * @param size page size
   * @param page page number
   * @return list of notification responses
   */
  @Override
  public List<NotificationDto.Response> getAllByUserId(Long userId, int size, int page) {
    return repository.findByUserIdAndDeletedFalse(userId, PageRequest.of(page - 1, size))
      .getContent()
      .stream()
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
    return NotificationDto.Response.fromEntity(repository.save(PushNotification.create(userId, notificationDto)));
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
    PushNotification notification = this.findById(id);
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
    PushNotification notification = this.findById(id);
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
    PushNotification notification = this.findById(id);
    this.validateModify(notification, updatedAt);
    notification.setDeleted(true);
    notification.setUpdatedAt(CommonUtils.getCurrentLocalDateTime());

    this.update(notification);
  }

  private PushNotification findById(Long id) {
    return repository.findByIdAndDeletedFalse(id)
      .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, EError.NOTIFICATION_NOT_FOUND));
  }

  private PushNotification update(PushNotification notification) {
    notification.setUpdatedAt(CommonUtils.getCurrentLocalDateTime());
    return repository.save(notification);
  }

  private void validateModify(PushNotification notification, LocalDateTime updatedAt) {
    CommonUtils.validateUpdatedRecord(notification.getUpdatedAt(), updatedAt);
    if (EMessageStatus.PENDING != notification.getStatus()) {
      throw new CustomException(HttpStatus.CONFLICT, EError.SENT_NOTIFICATION);
    }
    if (notification.isDeleted()) {
      throw new CustomException(HttpStatus.CONFLICT, EError.DELETED_NOTIFICATION);
    }
  }
}
