package akz.notification.management.facades;

import akz.notification.management.dto.NotificationModel;
import akz.notification.management.dto.PaginationNotificationModel;
import akz.notification.management.entities.EmailNotification;
import akz.notification.management.repositories.EmailNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static akz.notification.management.util.Constants.EMAIL_CANAL_NAME;

@Component(EMAIL_CANAL_NAME)
@RequiredArgsConstructor
public class EmailNotificationFacade implements INotificationFacade {

  private final EmailNotificationRepository repository;

  /**
   * Find an email notification by its ID.
   *
   * @param id the ID of the email notification
   * @return the found NotificationModel DTO, or an empty Optional if not found
   */
  @Override
  public Optional<NotificationModel> findById(Long id) {
    return repository.findByIdAndDeletedFalse(id)
      .map(NotificationModel::fromEntity);
  }

  /**
   * Get all email notifications for a specific user by their user ID.
   *
   * @param userId the ID of the user
   * @param size the number of notifications per page
   * @param page the page number (1-based)
   * @return a list of NotificationModel DTOs for the specified user
   */
  @Override
  public PaginationNotificationModel getAllByUserId(Long userId, int size, int page) {
    Page<EmailNotification> emailNotifications =
      repository.findByUserIdAndDeletedFalse(userId, PageRequest.of(page - 1, size));

    return PaginationNotificationModel.fromEmailEntities(
      emailNotifications.getContent(),
      emailNotifications.getTotalElements(),
      emailNotifications.getTotalPages()
    );
  }

  /**
   * Save a new email notification or update an existing one.
   *
   * @param notificationModel the NotificationModel DTO to be saved
   * @return the saved NotificationModel DTO
   */
  @Override
  public NotificationModel save(NotificationModel notificationModel) {
    return NotificationModel.fromEntity(repository.save(NotificationModel.toEntityEmail(notificationModel)));
  }
}
