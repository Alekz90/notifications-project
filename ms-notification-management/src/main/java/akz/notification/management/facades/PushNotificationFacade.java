package akz.notification.management.facades;

import akz.notification.management.dto.NotificationModel;
import akz.notification.management.dto.PaginationNotificationModel;
import akz.notification.management.entities.EmailNotification;
import akz.notification.management.entities.PushNotification;
import akz.notification.management.exceptions.CustomException;
import akz.notification.management.repositories.PushNotificationRepository;
import akz.notification.management.util.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static akz.notification.management.util.Constants.PUSH_CANAL_NAME;

@Component(PUSH_CANAL_NAME)
@RequiredArgsConstructor
public class PushNotificationFacade implements INotificationFacade {

  private final PushNotificationRepository repository;

  /**
   * Find a Push notification by its ID.
   *
   * @param id the ID of the Push notification
   * @return the found NotificationModel DTO, or an empty Optional if not found
   */
  @Override
  public Optional<NotificationModel> findById(Long id) {
    return repository.findByIdAndDeletedFalse(id)
      .map(NotificationModel::fromEntity);
  }

  /**
   * Get all Push notifications for a specific user by their user ID.
   *
   * @param userId the ID of the user
   * @param size the number of notifications per page
   * @param page the page number (1-based)
   * @return a PaginationNotificationModel containing the list of NotificationModel DTOs for the specified user
   */
  @Override
  public PaginationNotificationModel getAllByUserId(Long userId, int size, int page) {
    Page<PushNotification> pushNotifications =
      repository.findByUserIdAndDeletedFalse(userId, PageRequest.of(page - 1, size));

    return PaginationNotificationModel.fromPushEntities(
      pushNotifications.getContent(),
      pushNotifications.getTotalElements(),
      pushNotifications.getTotalPages()
    );
  }

  /**
   * Save a new Push notification or update an existing one.
   *
   * @param notificationModel the NotificationModel DTO to be saved
   * @return the saved NotificationModel DTO
   */
  @Override
  public NotificationModel save(NotificationModel notificationModel) {
    return NotificationModel.fromEntity(repository.save(NotificationModel.toEntityPush(notificationModel)));
  }

  /**
   * Update an existing Push notification.
   *
   * @param notificationModel the NotificationModel DTO to be updated
   * @return the updated NotificationModel DTO
   */
  @Override
  public NotificationModel update(NotificationModel notificationModel) {
    PushNotification entity = repository.findByIdAndDeletedFalse(notificationModel.getId())
      .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, EError.NOTIFICATION_NOT_FOUND));
    entity.update(notificationModel);
    return NotificationModel.fromEntity(repository.save(entity));
  }
}
