package akz.notifications.dto.kafka;

import akz.commonutils.util.CommonUtils;
import akz.notifications.util.enums.ECanal;
import akz.notifications.util.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * NotificationResponseKafkaDto is a Data Transfer Object (DTO) that represents a notification response message to be sent or received via Kafka.
 * It contains the necessary fields to describe a notification response, including its ID, status, and the timestamp when it was sent.
 * This DTO is used for communication between microservices in a Kafka-based architecture, allowing services to exchange notification response information efficiently.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaNotificationResponseDto {
  private Long id;
  private EStatus status;
  private LocalDateTime sentAt;
  private ECanal canal;

  /**
   * Creates a KafkaNotificationResponseDto object based on the provided KafkaNotificationSendingDto.
   * The status is set to SENT, and the sentAt timestamp is set to the current local date and time.
   *
   * @param sendingDto The KafkaNotificationSendingDto object containing the notification details.
   * @return A KafkaNotificationResponseDto object with a SENT status and the current timestamp.
   */
  public static KafkaNotificationResponseDto from(KafkaNotificationSendingDto sendingDto) {
    return KafkaNotificationResponseDto.builder()
        .id(sendingDto.getId())
        .status(EStatus.SENT)
        .sentAt(CommonUtils.getCurrentLocalDateTime())
        .canal(sendingDto.getCanal())
        .build();
  }

  /**
   * Builds a KafkaNotificationResponseDto object with a FAILED status based on the provided KafkaNotificationSendingDto.
   *
   * @param notification The KafkaNotificationSendingDto object containing the notification details.
   * @return A KafkaNotificationResponseDto object with a FAILED status and the current timestamp.
   */
  public static KafkaNotificationResponseDto buildFail(KafkaNotificationSendingDto notification) {
    return KafkaNotificationResponseDto.builder()
        .id(notification.getId())
        .status(EStatus.FAILED)
        .sentAt(CommonUtils.getDateTimeZero())
        .canal(notification.getCanal())
        .build();
  }
}
