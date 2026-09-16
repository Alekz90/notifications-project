package akz.securityusers.exceptions;

import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.enums.ECommonError;
import akz.commonutils.util.enums.GenericEnum;
import akz.securityusers.utils.enums.EError;
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
