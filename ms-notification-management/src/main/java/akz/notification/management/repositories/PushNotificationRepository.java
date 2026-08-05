package akz.notification.management.repositories;

import akz.notification.management.entities.PushNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {

  Optional<PushNotification> findByIdAndDeletedFalse(Long id);
  List<PushNotification> findByUserIdAndDeletedFalse(Long userId);
}
