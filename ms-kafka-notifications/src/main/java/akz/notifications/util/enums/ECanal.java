package akz.notifications.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static akz.notifications.util.Constants.*;

@Getter
@AllArgsConstructor
public enum ECanal {
  NONE  (""),
  EMAIL (EMAIL_SERVICE_NAME),
  SMS   (SMS_SERVICE_NAME),
  PUSH  (PUSH_SERVICE_NAME);

  private final String serviceName;
}
