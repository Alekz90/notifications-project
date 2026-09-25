package akz.push.notifications.services.interfaces;

import akz.push.notifications.models.NotificationDto;

public interface WSNotificationService {
    void sendNotificationToUser(long userId, NotificationDto.Response note);
}
