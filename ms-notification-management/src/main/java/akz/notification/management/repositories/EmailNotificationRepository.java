package akz.notification.management.repositories;

import akz.notification.management.entities.EmailNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {

  Optional<EmailNotification> findByIdAndDeletedFalse(Long id);
  Page<EmailNotification> findByUserIdAndDeletedFalse(Long userId, Pageable pageable);
}
