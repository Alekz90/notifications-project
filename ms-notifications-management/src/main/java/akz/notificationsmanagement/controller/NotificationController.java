package akz.notificationsmanagement.controller;

import akz.notificationsmanagement.remote.dto.NotificationDto;
import akz.notificationsmanagement.service.interfaces.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

  private final INotificationService service;

  @GetMapping("/{id}")
  public NotificationDto getNotificationById(@PathVariable Long id) {
    return service.getNotification(id);
  }
}
