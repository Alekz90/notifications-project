package akz.notification.management.dto.kafka;

import akz.notification.management.util.enums.ECanal;
import akz.notification.management.util.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * NotificationResponseKafkaDto is a Data Transfer Object (DTO) that represents a notification response message to be sent or received via Kafka.
 * It contains the necessary fields to describe a notification response, including its ID, status, and the timestamp when it was sent.
 * This DTO is used for communication between microservices in a Kafka-based architecture, allowing services to exchange notification response information efficiently.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaNotificationResponseDto {
  private Long id;
  private EStatus status;
  private LocalDateTime sentAt;
  private ECanal canal;
}
