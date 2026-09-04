package akz.notification.management.service;

import akz.commonutils.util.CommonUtils;
import akz.notification.management.dto.NotificationDto;
import akz.notification.management.dto.NotificationModel;
import akz.notification.management.dto.validations.NotificationGroup;
import akz.notification.management.exceptions.CustomException;
import akz.notification.management.facades.INotificationFacade;
import akz.notification.management.service.interfaces.INotificationService;
import akz.notification.management.util.enums.ECanal;
import akz.notification.management.util.enums.EError;
import akz.notification.management.util.enums.EMessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

  private final Map<String, INotificationFacade> strategies;

  /**
   * Get a notification by its ID.
   *
   * @param id notification ID
    * @param canal notification channel
   * @return notification response
   */
  @Override
  public NotificationDto.Response getById(Long id, ECanal canal) {
    return NotificationDto.Response.fromEntity(this.findById(id, canal));
  }

  /**
   * Get all notifications for a specific user by their user ID.
   *
   * @param userId user ID
   * @param size page size
   * @param page page number
   * @param canal notification channel
   * @return list of notification responses
   */
  @Override
  public List<NotificationDto.Response> getAllByUserId(Long userId, int size, int page, ECanal canal) {
    return strategies.get(canal.name()).getAllByUserId(userId, size, page)
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
    validateConstraintsCreation(notificationDto);
    return NotificationDto.Response.fromEntity(
              strategies.get(notificationDto.canal().name()).save(
                NotificationDto.Register.toNotificationModel(userId, notificationDto)));
  }

  /**
   * Update an existing notification by its ID.
   *
   * @param id notification ID
   * @param notificationDto notification data
   * @return notification response
   */
  @Override
  public NotificationDto.Response update(Long id, NotificationDto.Register notificationDto) {
    validateConstraintsActualization(notificationDto);
    NotificationModel notification = this.findById(id, notificationDto.canal());
    this.validateModify(notification, notificationDto.updatedAt());
    notification.modify(notificationDto);

    return NotificationDto.Response.fromEntity(this.update(notification, notificationDto.canal()));
  }

  /**
   * Send a notification by its ID.
   *
   * @param updatedAt updated date
   * @param id notification ID
   * @param canal notification channel
   */
  @Override
  public void send(Long id, LocalDateTime updatedAt, ECanal canal) {
    NotificationModel notification = this.findById(id, canal);
    this.validateModify(notification, updatedAt);
    notification.setStatus(EMessageStatus.SENT);
    notification.setUpdatedAt(CommonUtils.getCurrentLocalDateTime());

    this.update(notification, canal);
  }

  /**
   * Delete a notification by its ID.
   *
   * @param id notification ID
   * @param updatedAt updated date
   * @param canal notification channel
   */
  @Override
  public void delete(Long id, LocalDateTime updatedAt, ECanal canal) {
    NotificationModel notification = this.findById(id, canal);
    this.validateModify(notification, updatedAt);
    notification.setDeleted(true);
    notification.setUpdatedAt(CommonUtils.getCurrentLocalDateTime());

    this.update(notification, canal);
  }

  /**
   * Find a notification by its ID.
   *
   * @param id notification ID
   * @return notification model
   * @throws CustomException if the notification is not found
   */
  private NotificationModel findById(Long id, ECanal canal) {
    return strategies.get(canal.name()).findById(id)
      .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, EError.NOTIFICATION_NOT_FOUND));
  }

  /**
   * Validate if a notification can be modified based on its status and deletion state.
   * @param notification the notification model to validate
   * @param updatedAt the timestamp to compare with the notification's updatedAt
   */
  private void validateModify(NotificationModel notification, LocalDateTime updatedAt) {
    CommonUtils.validateUpdatedRecord(notification.getUpdatedAt(), updatedAt);
    if (EMessageStatus.PENDING != notification.getStatus()) {
      throw new CustomException(HttpStatus.CONFLICT, EError.SENT_NOTIFICATION);
    }
    if (notification.isDeleted()) {
      throw new CustomException(HttpStatus.CONFLICT, EError.DELETED_NOTIFICATION);
    }
  }

  /**
   * Update a notification model in the database.
   *
   * @param notification the notification model to update
   * @param canal the canal type of the notification
   * @return the updated notification model
   */
  private NotificationModel update(NotificationModel notification, ECanal canal) {
    notification.setUpdatedAt(CommonUtils.getCurrentLocalDateTime());
    return strategies.get(canal.name()).save(notification);
  }

  /**
   * Validate the constraints of a notification DTO based on its canal type.
   *
   * @param notificationDto the notification DTO to validate
   */
  private void validateConstraintsCreation(NotificationDto.Register notificationDto) {
    switch (notificationDto.canal()) {
      case EMAIL -> CommonUtils.validateConstraintsDto(notificationDto, NotificationGroup.SaveEmail.class);
      case SMS -> CommonUtils.validateConstraintsDto(notificationDto, NotificationGroup.SaveSMS.class);
      case PUSH -> CommonUtils.validateConstraintsDto(notificationDto, NotificationGroup.SavePush.class);
      default -> throw new CustomException(HttpStatus.BAD_REQUEST, EError.INVALID_CANAL);
    }
  }

  /**
   * Validate the constraints of a notification DTO based on its canal type.
   *
   * @param notificationDto the notification DTO to validate
   */
  private void validateConstraintsActualization(NotificationDto.Register notificationDto) {
    switch (notificationDto.canal()) {
      case EMAIL -> CommonUtils.validateConstraintsDto(notificationDto, NotificationGroup.UpdateEmail.class);
      case SMS -> CommonUtils.validateConstraintsDto(notificationDto, NotificationGroup.UpdateSMS.class);
      case PUSH -> CommonUtils.validateConstraintsDto(notificationDto, NotificationGroup.UpdatePush.class);
      default -> throw new CustomException(HttpStatus.BAD_REQUEST, EError.INVALID_CANAL);
    }
  }
}
