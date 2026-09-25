package akz.push.notifications.services;

import akz.push.notifications.models.NotificationDto;
import akz.push.notifications.services.interfaces.WSNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WSNotificationServiceImpl implements WSNotificationService {

  private final SimpMessagingTemplate simpMessagingTemplate;

  @Override
  public void sendNotificationToUser(long userId, NotificationDto.Response note) {
    simpMessagingTemplate.convertAndSendToUser(Long.toString(userId), "/topic/push-notifications", note);
  }
}
