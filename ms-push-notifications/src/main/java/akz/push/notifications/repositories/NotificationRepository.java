package akz.push.notifications.repositories;

import akz.push.notifications.entities.Notification;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends ListCrudRepository<Notification, String> {
    List<Notification> findAllByUserId(long userId);
}
