package akz.push.notifications.exceptions;

import akz.commonutils.exception.CustomCommonException;
import akz.push.notifications.utils.enums.EError;
import org.springframework.http.HttpStatus;

public class CustomException extends CustomCommonException {

  public CustomException(HttpStatus status, EError errorEnum) {
    super(status, errorEnum);
  }

  public CustomException(EError errorEnum) {
    super(errorEnum);
  }

  public CustomException() {
    super();
  }
}
