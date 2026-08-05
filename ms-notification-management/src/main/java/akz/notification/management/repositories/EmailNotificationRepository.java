package akz.notification.management.repositories;

import akz.notification.management.entities.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {

  Optional<EmailNotification> findByIdAndDeletedFalse(Long id);
  List<EmailNotification> findByUserIdAndDeletedFalse(Long userId);
}
