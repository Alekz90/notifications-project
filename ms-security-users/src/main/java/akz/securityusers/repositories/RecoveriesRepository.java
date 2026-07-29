package akz.securityusers.repositories;

import akz.securityusers.entities.Recovery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RecoveriesRepository extends JpaRepository<Recovery, Long> {
  Optional<Recovery> findByEmailAndUsedFalseAndExpirationDateAfter(String email, LocalDateTime currentDateTime);
}
