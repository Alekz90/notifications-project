package akz.securityusers.repositories;

import akz.securityusers.entities.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationsRepository extends JpaRepository<Verification, Long> {
}
