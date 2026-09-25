package akz.push.notifications.services;

import akz.push.notifications.entities.Notification;
import akz.push.notifications.exceptions.CustomException;
import akz.push.notifications.models.NotificationDto;
import akz.push.notifications.repositories.NotificationRepository;
import akz.push.notifications.services.interfaces.NotificationService;
import akz.push.notifications.services.interfaces.WSNotificationService;
import akz.push.notifications.utils.enums.EError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final WSNotificationService wsNotificationService;

    @Override
    public List<NotificationDto.Response> getAll(long userId) {
        return repository.findAllByUserId(userId)
          .stream()
          .map(NotificationDto.Response::fromEntity)
          .toList();
    }

    @Override
    public NotificationDto.Response create(NotificationDto.Create note) {
        NotificationDto.Response response =
          NotificationDto.Response.fromEntity(repository.save(Notification.fromDto(note)));
        wsNotificationService.sendNotificationToUser(note.userId(), response);
        return response;
    }

    @Override
    public void read(String id) {
        Notification notification = repository.findById(id)
          .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, EError.NOTIFICATION_NOT_FOUND));
        notification.setRead(true);
        repository.save(notification);
    }
}
