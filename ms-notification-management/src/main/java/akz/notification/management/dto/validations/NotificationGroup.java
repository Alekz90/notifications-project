package akz.notification.management.dto.validations;

public interface NotificationGroup {
  interface DefaultGroup {}
  interface SaveEmail extends DefaultGroup {}
  interface SaveSMS extends DefaultGroup {}
  interface SavePush extends DefaultGroup {}
  interface UpdateEmail extends SaveEmail {}
  interface UpdateSMS extends SaveSMS {}
  interface UpdatePush extends SavePush {}
}
