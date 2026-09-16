package akz.securityusers.services.interfaces;

import akz.securityusers.dtos.UserDto;
import akz.securityusers.entities.User;

public interface IUsersService {
  User save(UserDto.Register userDto);
  void update(User user);
  User findById(Long id);
  void changePassword(Long id, UserDto.ChangePassword request);
  void recoveryPassword(User user, UserDto.RecoveryPassword request);
  UserDto.UserResponse getUserByUsername(String username);
  UserDto.UserResponse getUserById(Long id);
  User findByEmail(String email);
  User findByUsername(String username);
}
