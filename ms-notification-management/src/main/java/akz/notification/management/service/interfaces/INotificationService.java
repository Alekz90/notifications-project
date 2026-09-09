package akz.notification.management.service.interfaces;


import akz.notification.management.dto.NotificationDto;
import akz.notification.management.util.enums.ECanal;

import java.time.LocalDateTime;

public interface INotificationService {

  /**
   * Get a notification by its ID.
   * @param id notification ID
   * @return notification detail response
   */
  NotificationDto.ResponseDetail getById(Long id, ECanal canal);

  /**
   * Get all notifications for a specific user by their user ID.
   * @param userId user ID
   * @return paginated notification responses
   */
  NotificationDto.PaginationResponse getAllByUserId(Long userId, int size, int page, ECanal canal);

  /**
   * Create a new notification.
   * @param userId user ID
   * @param notificationDto notification data
   * @return notification response
   */
  NotificationDto.ResponseDetail create(Long userId, NotificationDto.Register notificationDto);

  /**
   * Update an existing notification by its ID.
   * @param id notification ID
   * @param notificationDto notification data
   * @return notification detail response
   */
  NotificationDto.ResponseDetail update(Long id, NotificationDto.Register notificationDto);

  /**
   * Send a notification by its ID.
   * @param id notification ID
   * @param updatedAt updated date
   * @param canal notification channel
   */
  void send(Long id, LocalDateTime updatedAt, ECanal canal);

  /**
   * Delete a notification by its ID.
   * @param id notification ID
   * @param updatedAt updated date
   * @param canal notification channel
   */
  void delete(Long id, LocalDateTime updatedAt, ECanal canal);
}
