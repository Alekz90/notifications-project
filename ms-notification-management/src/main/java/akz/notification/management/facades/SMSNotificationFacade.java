package akz.notification.management.facades;

import akz.notification.management.dto.NotificationModel;
import akz.notification.management.repositories.SMSNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static akz.notification.management.util.Constants.SMS_CANAL_NAME;

@Component(SMS_CANAL_NAME)
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
   * @return a list of NotificationModel DTOs for the specified user
   */
  @Override
  public List<NotificationModel> getAllByUserId(Long userId, int size, int page) {
    return repository.findByUserIdAndDeletedFalse(userId, PageRequest.of(page - 1, size))
      .getContent()
      .stream()
      .map(NotificationModel::fromEntity)
      .toList();
  }

  /**
   * Save a new SMS notification or update an existing one.
   *
   * @param notificationModel the NotificationModel DTO to be saved
   * @return the saved NotificationModel DTO
   */
  @Override
  public NotificationModel save(NotificationModel notificationModel) {
    return NotificationModel.fromEntity(repository.save(NotificationModel.toEntitySMS(notificationModel)));
  }
}
