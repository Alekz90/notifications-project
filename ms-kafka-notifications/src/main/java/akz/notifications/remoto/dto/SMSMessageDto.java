package akz.notifications.remoto.dto;

import akz.notifications.dto.kafka.KafkaNotificationSendingDto;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static akz.notifications.util.Constants.NOTIFICATIONS_API;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMSMessageDto {
  private String id;
  private String to;
  private String from;
  private String message;
  private String project;
  private String[] tags;
  @JsonAlias("detected_otps")
  private String[] detectedOTPS;
  private boolean read;
  @JsonAlias("read_at")
  private LocalDateTime readAt;
  @JsonAlias("created_at")
  private LocalDateTime createdAt;

  /**
   * Converts a KafkaNotificationSendingDto to an SMSMessageDto.
   *
   * @param notification The KafkaNotificationSendingDto to convert.
   * @return An SMSMessageDto with the corresponding fields from the notification.
   */
  public static SMSMessageDto from(KafkaNotificationSendingDto notification) {
    return SMSMessageDto.builder()
        .to(notification.getTo())
        .from(notification.getFrom())
        .message(notification.getBody())
        .project(NOTIFICATIONS_API)
        .build();
  }
}
