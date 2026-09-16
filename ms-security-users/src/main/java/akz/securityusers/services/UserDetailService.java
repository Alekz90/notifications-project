package akz.securityusers.services;

import akz.commonutils.exception.CustomCommonException;
import akz.securityusers.repositories.UsersRepository;
import akz.securityusers.utils.enums.EError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

  private final UsersRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return repository.findByUsername(username)
        .orElseThrow(() -> new CustomCommonException(HttpStatus.NOT_FOUND, EError.USER_NOT_FOUND));
  }
}
