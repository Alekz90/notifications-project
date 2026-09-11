package akz.notification.management.util.enums;

import akz.notification.management.exceptions.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum EStatus {
  PENDING, SENDING, SENT, FAILED;

  /**
   * Validates if the notification status is valid for sending.
   * @param status the status to validate
   * @throws CustomException if the status is not PENDING or FAILED
   */
  public static void validToSend(EStatus status) {
    if (EStatus.PENDING != status && EStatus.FAILED != status) {
      throw new CustomException(HttpStatus.CONFLICT, EError.SENT_NOTIFICATION);
    }
  }
}
