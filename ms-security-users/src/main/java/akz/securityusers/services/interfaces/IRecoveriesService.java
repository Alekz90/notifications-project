package akz.securityusers.services.interfaces;

import akz.securityusers.dtos.UserDto;

public interface IRecoveriesService {
  void sendingRecoveryPassword(String email);
  void recoveryPassword(Long id, UserDto.RecoveryPassword request);
}
