package akz.notification.management.facades;

import akz.notification.management.dto.NotificationModel;

import java.util.List;
import java.util.Optional;

public interface INotificationFacade {
  Optional<NotificationModel> findById(Long id);
  List<NotificationModel> getAllByUserId(Long userId, int size, int page);
  NotificationModel save(NotificationModel notificationModel);
}
