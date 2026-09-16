package akz.notifications.dto.kafka;

import akz.notifications.util.enums.ECanal;
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
}
