package akz.notification.management.repositories;

import akz.notification.management.entities.SMSNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SMSNotificationRepository extends JpaRepository<SMSNotification, Long> {

  Optional<SMSNotification> findByIdAndDeletedFalse(Long id);
  List<SMSNotification> findByUserIdAndDeletedFalse(Long userId);
}
