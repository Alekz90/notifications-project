package akz.notification.management.facades;

import akz.notification.management.dto.NotificationModel;
import akz.notification.management.dto.PaginationNotificationModel;
import akz.notification.management.entities.SMSNotification;
import akz.notification.management.exceptions.CustomException;
import akz.notification.management.repositories.SMSNotificationRepository;
import akz.notification.management.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static akz.notification.management.util.Constants.SMS_SERVICE_NAME;

@Component(SMS_SERVICE_NAME)
@RequiredArgsConstructor
public class SMSNotificationFacade implements INotificationFacade {

  private final SMSNotificationRepository repository;

  /**
   * Find an SMS notification by its ID.
   *
   * @param id the ID of the SMS notification
   * @return the found NotificationModel DTO, or an empty Optional if not found
   */
  @Override
  public Optional<NotificationModel> findById(Long id) {
    return repository.findByIdAndDeletedFalse(id)
      .map(NotificationModel::fromEntity);
  }

  /**
   * Get all SMS notifications for a specific user by their user ID.
   *
   * @param userId the ID of the user
   * @param size the number of notifications per page
   * @param page the page number (1-based)
   * @return a PaginationNotificationModel containing the list of NotificationModel DTOs for the specified user
   */
  @Override
  public PaginationNotificationModel getAllByUserId(Long userId, int size, int page) {
    Page<SMSNotification> smsNotifications =
      repository.findByUserIdAndDeletedFalse(userId, PageRequest.of(page - 1, size));

    return PaginationNotificationModel.fromSMSEntities(
      smsNotifications.getContent(),
      smsNotifications.getTotalElements(),
      smsNotifications.getTotalPages()
    );
  }

  /**
   * Save a new SMS notification or update an existing one.
   *
   * @param model the NotificationModel DTO to be saved
   * @return the saved NotificationModel DTO
   */
  @Override
  public NotificationModel save(NotificationModel model) {
    return NotificationModel.fromEntity(repository.save(new SMSNotification(model)));
  }

  /**
   * Update an existing SMS notification.
   * @param model the NotificationModel DTO to be updated
   * @return the updated NotificationModel DTO
   */
  @Override
  public NotificationModel update(NotificationModel model) {
    SMSNotification entity = repository.findByIdAndDeletedFalse(model.getId())
      .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, EError.NOTIFICATION_NOT_FOUND));
    entity.update(model);
    return NotificationModel.fromEntity(repository.save(entity));
  }
}
