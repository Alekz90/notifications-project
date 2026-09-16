package akz.securityusers.utils;

import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.enums.ECommonError;

import java.util.List;

import static akz.commonutils.util.CommonConstants.SLASH;
import static akz.commonutils.util.CommonConstants.SLASH_ALL;

public final class PathConstants {

  public static final String AUTHENTICATIONS      = "authentications";
  public static final String VERIFICATIONS        = "verifications";
  public static final String RECOVERIES           = "recoveries";
  public static final String USERS                = "users";

  public static final String V1 = "/v1";
  public static final String REGISTER = "/register";
  public static final String LOGIN = "/login";

  public static final String[] WHITELIST = {
      "/v3/api-docs/**",
      "/v3/api-docs",
      "/swagger-ui/**",
      SLASH + AUTHENTICATIONS + V1 + REGISTER,
      SLASH + AUTHENTICATIONS + V1 + LOGIN,
      SLASH + VERIFICATIONS + SLASH_ALL,
      SLASH + RECOVERIES + SLASH_ALL
  };
  public static final List<String> WHITELIST_PATTERNS = Utils.convertPatterEndpoints(WHITELIST);

  private PathConstants() {
    throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
