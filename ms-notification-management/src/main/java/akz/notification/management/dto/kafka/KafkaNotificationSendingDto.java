package akz.notification.management.dto.kafka;

import akz.notification.management.dto.NotificationModel;
import akz.notification.management.util.enums.ECanal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NotificationSendingKafkaDto is a Data Transfer Object (DTO) that represents a notification message to be sent via Kafka.
 * It contains the necessary fields to describe a notification, including its ID, title, body, sender, recipient, and the communication channel.
 * This DTO is used for communication between microservices in a Kafka-based architecture, allowing services to exchange notification information efficiently.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaNotificationSendingDto {
  private Long id;
  private String title;
  private String body;
  private String from;
  private String to;
  private ECanal canal;

  /**
   * Converts a NotificationModel DTO to a KafkaNotificationSendingDto for sending via Kafka.
   *
   * @param notification the NotificationModel DTO to convert
   * @param canal the communication channel (ECanal) for the notification
   * @return a KafkaNotificationSendingDto representing the notification to be sent via Kafka
   */
  public static KafkaNotificationSendingDto from(NotificationModel notification, ECanal canal) {
    return KafkaNotificationSendingDto.builder()
      .id(notification.getId())
      .title(notification.getTitle())
      .body(notification.getBody())
      .from(notification.getFrom())
      .to(notification.getTo())
      .canal(canal)
      .build();
  }
}
