package akz.notification.management.service.interfaces;


import akz.notification.management.dto.NotificationDto;
import akz.notification.management.util.enums.ECanal;

import java.time.LocalDateTime;
import java.util.List;

public interface INotificationService {

  /**
   * Get a notification by its ID.
   * @param id notification ID
   * @return notification response
   */
  NotificationDto.Response getById(Long id, ECanal canal);

  /**
   * Get all notifications for a specific user by their user ID.
   * @param userId user ID
   * @return list of notification responses
   */
  List<NotificationDto.Response> getAllByUserId(Long userId, int size, int page, ECanal canal);

  /**
   * Create a new notification.
   * @param userId user ID
   * @param notificationDto notification data
   * @return notification response
   */
  NotificationDto.Response create(Long userId, NotificationDto.Register notificationDto);

  /**
   * Update an existing notification by its ID.
   * @param id notification ID
   * @param notificationDto notification data
   * @return notification response
   */
  NotificationDto.Response update(Long id, NotificationDto.Register notificationDto);

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
