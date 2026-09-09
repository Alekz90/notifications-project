package akz.notification.management.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EMessageStatus {
  PENDING, SENDING, SENT, FAILED
}
