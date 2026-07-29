package akz.securityusers.dtos;

import akz.securityusers.entities.User;
import akz.commonutils.annotation.ValidPassword;
import akz.commonutils.annotation.ValidPhone;
import akz.commonutils.annotation.ValidUsername;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {

  @Schema(name = "UserDto.Login", description = "DTO for user login")
  public record Login(
      @NotBlank @ValidUsername @Size(max = 100) String username,
      @NotBlank @ValidPassword String password) {}

  @Schema(name = "UserDto.Register", description = "DTO for user registration")
  public record Register(
      @NotBlank @ValidUsername @Size(max = 100) String username,
      @NotBlank @ValidPassword String password,
      @NotBlank @Email @Size(max = 100) String email,
      @NotBlank @ValidPhone @Size(max = 100) String phone,
      @NotNull boolean acceptTerms
  ) { }

  @Schema(name = "UserDto.ChangePassword", description = "DTO for changing user password")
  public record ChangePassword(
      @NotBlank @ValidPassword String oldPassword,
      @NotBlank @ValidPassword String newPassword) { }

  @Schema(name = "UserDto.UserResponse", description = "DTO for user response")
  public record UserResponse(Long id, String username, String email, boolean active, String role, boolean blocked,
      boolean verified, String creationDate) {

    public static UserResponse build(User user) {
      return new UserResponse(
          user.getId(),
          user.getUsername(),
          user.getEmail(),
          user.isActive(),
          user.getRole().name(),
          user.isBlocked(),
          user.isVerified(),
          user.getCreationDate().toString()
      );
    }
  }

  @Schema(name = "UserDto.RecoveryPassword", description = "DTO for recovering user password")
  public record RecoveryPassword(
      @NotBlank @ValidPassword String newPassword) { }

  @Schema(name = "UserDto.Authentication", description = "DTO for user authentication")
  public record Authentication(String token, UserResponse user) {

    public static Authentication build(String token, User user) {
      return new Authentication(token, UserResponse.build(user));
    }
  }
}
