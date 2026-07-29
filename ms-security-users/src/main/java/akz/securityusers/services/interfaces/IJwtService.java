package akz.securityusers.services.interfaces;

import akz.securityusers.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
  String getUsernameFromToken(String token);
  String generateToken(User user);
  boolean isInvalidToken(String token, UserDetails user);
}
