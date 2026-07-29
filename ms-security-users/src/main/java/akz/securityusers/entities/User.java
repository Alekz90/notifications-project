package akz.securityusers.entities;

import akz.securityusers.dtos.UserDto;
import akz.securityusers.entities.abstracts.AbstractLocalDateFields;
import akz.securityusers.utils.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Table(comment = "Table for storing user information")
public class User extends AbstractLocalDateFields implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String email;
  private String phone;
  private String password;
  @Enumerated(EnumType.STRING)
  private ERole role;
  private boolean active;
  private boolean blocked;
  private boolean verified;
  @Column(name = "accept_terms")
  private boolean acceptTerms;

  /**
   * Build a new User from UserDto.Register
   * @param userDto
   */
  public User(UserDto.Register userDto) {
    super();
    this.username = userDto.username();
    this.email = userDto.email();
    this.phone = userDto.phone();
    this.password = userDto.password();
    this.role = ERole.USER;
    this.active = true;
    this.blocked = false;
    this.verified = false;
    this.acceptTerms = userDto.acceptTerms();
  }

  @Override
  public List<SimpleGrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }
}
