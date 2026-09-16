package akz.securityusers.services.interfaces;

import akz.securityusers.dtos.UserDto;

public interface IAuthenticationService {
  UserDto.Authentication register(UserDto.Register request);
  UserDto.Authentication login(UserDto.Login request);
  UserDto.Authentication checkStatus(String tokenHeader);
}
