package akz.notification.management.util.enums;

import akz.commonutils.util.enums.GenericEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EError implements GenericEnum<String> {

  NOTIFICATION_NOT_FOUND                ("0001", "Notification wasn't found."),
  BAD_EMAIL_FORMAT                      ("0002", "Email format is invalid."),
  BAD_PHONE_FORMAT                      ("0003", "Phone format is invalid."),
  SENT_NOTIFICATION                     ("0004", "This notification already sends."),
  DELETED_NOTIFICATION                  ("0005", "This notification already deleted."),;

  private final String id;
  private final String message;
}
