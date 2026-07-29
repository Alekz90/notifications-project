package akz.securityusers.services;

import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.CommonUtils;
import akz.securityusers.entities.Recovery;
import akz.securityusers.entities.User;
import akz.securityusers.dtos.UserDto;
import akz.securityusers.repositories.RecoveriesRepository;
import akz.securityusers.services.interfaces.IRecoveriesService;
import akz.securityusers.services.interfaces.IUsersService;
import akz.securityusers.utils.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecoveriesService implements IRecoveriesService {

  private final IUsersService usersService;
  private final RecoveriesRepository repository;

  @Override
  public void sendingRecoveryPassword(String email) {
    User user = usersService.findByEmail(email);

    Recovery recovery = repository.findByEmailAndUsedFalseAndExpirationDateAfter(email, CommonUtils.getCurrentLocalDateTime())
        .orElse(new Recovery(email, user));

    repository.save(recovery);

    // Additional actions like sending recovery email could go here
  }

  @Override
  public void recoveryPassword(Long id, UserDto.RecoveryPassword request) {
    Recovery recovery = repository.findById(id)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.RECOVERY_NOT_FOUND));

    if (recovery.isUsed() || recovery.getExpirationDate().isBefore(CommonUtils.getCurrentLocalDateTime())) {
      throw new CustomCommonException(HttpStatus.BAD_REQUEST, EError.RECOVERY_INVALID);
    }

    usersService.recoveryPassword(recovery.getUser(), request);

    recovery.setUsed(true);
    recovery.setUsedDate(CommonUtils.getCurrentLocalDateTime());
    repository.save(recovery);

    // Additional actions like sending confirmation email could go here
  }
}
