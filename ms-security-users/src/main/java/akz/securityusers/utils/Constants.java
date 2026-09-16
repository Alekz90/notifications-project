package akz.securityusers.utils;

import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.enums.ECommonError;

public class Constants {
  public static final String BEARER = "Bearer ";
  public static final String ROLE_NAME = "ROLE_";
  public static final int INT_UNO = 1;
  public static final int INT_CERO = 0;
  public static final int VERIFICATION_EXPIRATION_HOURS = 24;
  public static final int VERIFICATION_CODE_LENGTH = 8;
  public static final String VERIFICATION_CODE_PATTERN = "^\\d{" + VERIFICATION_CODE_LENGTH + "}$";


  private Constants() {
    throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
