package akz.notification.management.repositories;

import akz.notification.management.entities.PushNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {

  Optional<PushNotification> findByIdAndDeletedFalse(Long id);
  Page<PushNotification> findByUserIdAndDeletedFalse(Long userId, Pageable pageable);
}
