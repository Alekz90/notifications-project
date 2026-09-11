package akz.notification.management.facades;

import akz.notification.management.dto.NotificationModel;
import akz.notification.management.dto.PaginationNotificationModel;

import java.util.Optional;

public interface INotificationFacade {
  Optional<NotificationModel> findById(Long id);
  PaginationNotificationModel getAllByUserId(Long userId, int size, int page);
  NotificationModel save(NotificationModel notificationModel);
  NotificationModel update(NotificationModel notificationModel);
}
