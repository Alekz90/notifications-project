package akz.securityusers.services;

import akz.securityusers.dtos.UserDto;
import akz.securityusers.entities.User;
import akz.securityusers.services.interfaces.IAuthenticationService;
import akz.securityusers.services.interfaces.IJwtService;
import akz.securityusers.services.interfaces.IUsersService;
import akz.securityusers.services.interfaces.IVerificationsService;
import akz.securityusers.utils.enums.EError;
import akz.commonutils.exception.CustomCommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

  private final IUsersService usersService;
  private final IVerificationsService verificationsService;
  private final IJwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  @Transactional
  public UserDto.Authentication register(UserDto.Register request) {
    if (!request.acceptTerms()) {
      throw new CustomCommonException(HttpStatus.BAD_REQUEST, EError.TERMS_NOT_ACCEPTED);
    }

    User user = usersService.save(request);
    verificationsService.create(user);
    //TODO: Send verification email
    return UserDto.Authentication.build(jwtService.generateToken(user), user);
  }

  @Override
  public UserDto.Authentication login(UserDto.Login request) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password()));
    } catch (Exception e) {
      throw new CustomCommonException(HttpStatus.UNAUTHORIZED, EError.WRONG_CREDENTIALS);
    }
    User user = usersService.findByUsername(request.username());
    String token = jwtService.generateToken(user);
    return UserDto.Authentication.build(token, user);
  }

  @Override
  public UserDto.Authentication checkStatus(String tokenHeader) {
    String token =  tokenHeader.replace("Bearer ", "");
    String username = jwtService.getUsernameFromToken(token);
    User user = usersService.findByUsername(username);
    if (jwtService.isInvalidToken(token, user)) {
      throw new CustomCommonException(HttpStatus.FORBIDDEN, EError.USER_FORBIDDEN);
    }
    return UserDto.Authentication.build(token, user);
  }
}
