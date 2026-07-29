package akz.securityusers.services;

import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.CommonUtils;
import akz.securityusers.entities.User;
import akz.securityusers.entities.Verification;
import akz.securityusers.repositories.VerificationsRepository;
import akz.securityusers.services.interfaces.IUsersService;
import akz.securityusers.services.interfaces.IVerificationsService;
import akz.securityusers.utils.Utils;
import akz.securityusers.utils.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationsService implements IVerificationsService {

  private final VerificationsRepository repository;
  private final IUsersService usersService;

  @Override
  public Verification create(User user) {
    Verification verification = new Verification(user, Utils.generateCode());
    return repository.save(verification);
  }

  @Override
  public void markVerificationAsUsed(Long id, String code) {
    Verification verification = repository.findById(id)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.VERIFICATION_NOT_FOUND));

    if (verification.getUser().isVerified()) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.USER_VERIFIED);
    }

    if (!verification.getCode().equals(code)) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.VERIFICATION_INVALID_CODE);
    }
    if (verification.getExpirationDate().isBefore(CommonUtils.getCurrentLocalDateTime())) {
      throw new CustomCommonException(HttpStatus.CONFLICT, EError.VERIFICATION_EXPIRED);
    }

    verification.setUsed(true);
    verification.setUsedDate(CommonUtils.getCurrentLocalDateTime());
    verification.getUser().setVerified(true);

    repository.save(verification);
    usersService.update(verification.getUser());
  }
}
