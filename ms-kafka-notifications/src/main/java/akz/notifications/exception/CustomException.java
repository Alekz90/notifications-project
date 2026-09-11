package akz.notifications.exception;

import akz.commonutils.exception.CustomCommonException;
import akz.notifications.util.enums.EError;
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
