package akz.securityusers.services.interfaces;

import akz.securityusers.entities.User;
import akz.securityusers.entities.Verification;

public interface IVerificationsService {
  Verification create(User userId);
  void markVerificationAsUsed(Long id, String code);
}
