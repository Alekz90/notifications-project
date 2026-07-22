package akz.notificationsmanagement.service;

import akz.notificationsmanagement.remote.dto.NotificationDto;
import akz.notificationsmanagement.service.interfaces.INotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {

  public NotificationDto getNotification(Long id) {
    return buildNotifications().stream()
      .filter(notification -> notification.id().equals(id))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Notification not found"));

  }

  private List<NotificationDto> buildNotifications() {
    // Implement the logic to build a list of notifications
    return List.of(
      new NotificationDto(1L, "Notification 1", "This is the first notification", "recipient1", "unread"),
      new NotificationDto(2L, "Notification 2", "This is the second notification", "recipient2", "read"),
      new NotificationDto(3L, "Notification 3", "This is the third notification", "recipient3", "unread")
    );
  }
}
