package akz.securityusers.exceptions;

import akz.commonutils.exception.CommonExceptionHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends CommonExceptionHandler {

}
