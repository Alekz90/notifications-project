package akz.notificationsmanagement.service.interfaces;

import akz.notificationsmanagement.remote.dto.NotificationDto;

public interface INotificationService {

  NotificationDto getNotification(Long id);
}
