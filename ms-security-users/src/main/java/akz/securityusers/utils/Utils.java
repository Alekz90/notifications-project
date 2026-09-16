package akz.securityusers.utils;

import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.CommonConstants;
import akz.commonutils.util.enums.ECommonError;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static akz.commonutils.util.CommonConstants.*;
import static akz.securityusers.utils.Constants.*;

/**
 * Utility class for common operations
 */
public final class Utils {

  /**
   * Convert an array of paths with variables to regex patterns
   * @param paths array of paths with variables
   * @return list of regex patterns
   */
  public static List<String> convertPatterEndpoints(String[] paths) {
    List<String> pathsCopy = List.of(paths);

    return pathsCopy.stream().map(Utils::convertPatternPath).toList();
  }

  /**
   * Convert a path with variables to a regex pattern
   * @param path path with variables
   * @return regex pattern
   */
  private static String convertPatternPath(String path) {
    while (path.contains(OPENING_BRACE) && path.contains(CLOSING_BRACE)) {
      int start = path.indexOf(OPENING_BRACE);
      int end = path.indexOf(CLOSING_BRACE);
      String toReplace = path.substring(start, end + INT_UNO);
      path = path.replace(toReplace, PATH_VARIABLE_PATTERN);
    }
    if (path.contains(SLASH_ALL)) {
      path = path.replace(SLASH_ALL, PATH_ALL_PATTERN);
    }
    return INIT_PATTERN + path + END_PATTERN;
  }

  /**
   * Generate a numeric verification code of length VERIFICATION_CODE_LENGTH
   * @return verification code
   */
  public static String generateCode() {
    StringBuilder code = new StringBuilder();
    while (true) {
      code.append((int) (Math.random() * 10));
      if (code.length() >= VERIFICATION_CODE_LENGTH) {
        return code.substring(INT_CERO, VERIFICATION_CODE_LENGTH);
      }
    }
  }

  /** Get current date time
   * @return Date Current date time
   */
  public static Date getCurrentDateTime() {
    return new Date();
  }

  /** Add seconds to a date time
   * @param dateTime Date time
   * @param seconds Seconds to add
   * @return Date Date time plus seconds
   */
  public static Date plusSecondsDateTime(Date dateTime, Long seconds) {
    return convertToDate(convertToLocalDate(dateTime).plusSeconds(seconds));
  }

  /** Convert Date to LocalDateTime
   * @param dateTime Date time
   * @return LocalDateTime Local date time
   */
  public static LocalDateTime convertToLocalDate(Date dateTime) {
    return dateTime.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime();
  }

  /** Convert LocalDateTime to Date
   * @param dateTime Local date time
   * @return Date Date time
   */
  public static Date convertToDate(LocalDateTime dateTime) {
    return Date.from(
      dateTime.atZone(ZoneId.systemDefault())
        .toInstant());
  }

  private Utils() {
    throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
