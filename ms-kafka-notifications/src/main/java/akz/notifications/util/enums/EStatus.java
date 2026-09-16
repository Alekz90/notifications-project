package akz.notifications.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EStatus {
  PENDING, SENDING, SENT, FAILED
}
