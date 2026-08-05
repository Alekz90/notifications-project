package akz.notification.management.util;

import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.enums.ECommonError;

public final class Constants {

  public static final String V1 = "/v1";
  public static final String SMS_SERVICE_NAME = "smsService";
  public static final String PUSH_SERVICE_NAME = "pushService";
  public static final String EMAIL_SERVICE_NAME = "emailService";

  private Constants() {
    throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
