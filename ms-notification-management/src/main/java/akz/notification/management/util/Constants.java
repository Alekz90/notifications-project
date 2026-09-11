package akz.notification.management.util;

import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.enums.ECommonError;

public final class Constants {

  // API Constants
  public static final String V1 = "/v1";
  public static final String SMS_CANAL_NAME = "SMS";
  public static final String PUSH_CANAL_NAME = "PUSH";
  public static final String EMAIL_CANAL_NAME = "EMAIL";

  // Kafka Constants
  public static final String KAFKA_GROUP_ID = "notification-group";
  public static final String KAFKA_TOPIC_SENDING_NAME = "notification-sending-topic";
  public static final String KAFKA_TOPIC_STATUS_NAME = "notification-status-topic";

  private Constants() {
    throw new CustomCommonException(ECommonError.UTILITY_CLASS);
  }
}
