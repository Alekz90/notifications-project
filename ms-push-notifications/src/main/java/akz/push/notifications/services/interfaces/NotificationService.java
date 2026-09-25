package akz.push.notifications.services.interfaces;

import akz.push.notifications.models.NotificationDto;

import java.util.List;

public interface NotificationService {

    List<NotificationDto.Response> getAll(long userId);
    NotificationDto.Response create(NotificationDto.Create notification);
    void read(String id);
}
