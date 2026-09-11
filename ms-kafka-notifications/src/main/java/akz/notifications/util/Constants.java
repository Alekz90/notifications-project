package akz.notifications.util;

public final class Constants {

  // API Constants
  public static final String EMAIL_SERVICE_NAME = "emailService";
  public static final String SMS_SERVICE_NAME   = "smsService";
  public static final String PUSH_SERVICE_NAME  = "pushService";

  // Kafka Constants
  public static final String KAFKA_GROUP_ID = "notification-group";
  public static final String KAFKA_TOPIC_SENDING_NAME = "notification-sending-topic";
  public static final String KAFKA_TOPIC_STATUS_NAME = "notification-status-topic";
  public static final String UTF_8 = "utf-8";
  public static final String SUB_TYPE_HTML = "html";

  private Constants() {
    //TODO: throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
