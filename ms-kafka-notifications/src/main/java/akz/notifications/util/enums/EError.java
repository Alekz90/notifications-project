package akz.notifications.util.enums;

import akz.commonutils.util.enums.GenericEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EError implements GenericEnum<String> {

  ERROR_SMS_SENDING                ("0001", "Error sending SMS.");

  private final String id;
  private final String message;
}
