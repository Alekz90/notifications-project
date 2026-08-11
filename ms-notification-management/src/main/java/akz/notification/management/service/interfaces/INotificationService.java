package akz.notification.management.service.interfaces;


import akz.notification.management.dto.NotificationDto;

import java.time.LocalDateTime;
import java.util.List;

public interface INotificationService {

  /**
   * Get a notification by its ID.
   * @param id notification ID
   * @return notification response
   */
  NotificationDto.Response getById(Long id);

  /**
   * Get all notifications for a specific user by their user ID.
   * @param userId user ID
   * @return list of notification responses
   */
  List<NotificationDto.Response> getAllByUserId(Long userId, int size, int page);

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
   * @param updatedAt updated date
   * @return notification response
   */
  NotificationDto.Response update(Long id, LocalDateTime updatedAt, NotificationDto.Register notificationDto);

  /**
   * Send a notification by its ID.
   * @param id notification ID
   * @param updatedAt updated date
   */
  void send(Long id, LocalDateTime updatedAt);

  /**
   * Delete a notification by its ID.
   * @param id notification ID
   * @param updatedAt updated date
   */
  void delete(Long id, LocalDateTime updatedAt);
}
