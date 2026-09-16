package akz.securityusers.utils.enums;

import akz.commonutils.util.enums.GenericEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EError implements GenericEnum<String> {

  USER_FORBIDDEN                ("0001", "Access denied. You do not have permission to access this resource."),
  WRONG_CREDENTIALS             ("0002", "Wrong User and/or Password."),
  USER_NOT_FOUND                ("0003", "User wasn't found."),
  USERNAME_FOUND                ("0004", "This username already have been registered."),
  EMAIL_FOUND                   ("0005", "This E-Mail already have been registered."),
  VERIFICATION_NOT_FOUND        ("0006", "Verification wasn't found."),
  VERIFICATION_EXPIRED          ("0007", "Verification code already has expired."),
  VERIFICATION_INVALID_CODE     ("0008", "Verification code is invalid."),
  USER_VERIFIED                 ("0009", "This user already has been verified."),
  INVALID_OLD_PASSWORD          ("0010", "The old password is incorrect."),
  PROFILE_FOUND                 ("0013", "This user already has a profile."),
  PROFILE_NOT_FOUND             ("0014", "Profile not found for this user."),
  PHONE_FOUND                   ("0015", "Phone number already exists."),
  TERMS_NOT_ACCEPTED            ("0016", "Terms and conditions must be accepted."),
  RECOVERY_NOT_FOUND            ("0017", "Password recovery not found."),
  RECOVERY_INVALID              ("0018", "Password recovery has already been used or expired."),;

  private final String id;
  private final String message;
}
