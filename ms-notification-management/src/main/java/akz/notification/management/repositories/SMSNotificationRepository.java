package akz.notification.management.repositories;

import akz.notification.management.entities.SMSNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SMSNotificationRepository extends JpaRepository<SMSNotification, Long> {

  Optional<SMSNotification> findByIdAndDeletedFalse(Long id);
  Page<SMSNotification> findByUserIdAndDeletedFalse(Long userId,  Pageable pageable);
}
