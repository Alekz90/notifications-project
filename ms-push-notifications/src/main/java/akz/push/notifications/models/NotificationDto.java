package akz.push.notifications.models;

import akz.commonutils.annotation.ValidSpecialText;
import akz.commonutils.annotation.ValidTitleText;
import akz.commonutils.annotation.ValidUsername;
import akz.push.notifications.entities.Notification;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class NotificationDto {

  public record Create(
    @NotNull(message = "User ID cannot be null")
    @Schema(example = "1") long userId,

    @ValidUsername
    @Size(max = 100, message = "Recipient username cannot exceed 100 characters")
    @NotBlank(message = "Recipient username cannot be empty")
    @Schema(example = "john_doe") String to,

    @ValidTitleText
    @Size(max = 150, message = "Notification title cannot exceed 150 characters")
    @NotBlank(message = "Notification title cannot be empty")
    @Schema(example = "Notification Title") String title,

    @ValidSpecialText
    @Size(max = 150, message = "Notification message cannot exceed 150 characters")
    @NotBlank(message = "Notification message cannot be empty")
    @Schema(example = "This is a notification message.") String message
  ) {}

  public record Response(
    @Schema(example = "1") String id,
    @Schema(example = "1") long userId,
    @Schema(example = "john_doe") String to,
    @Schema(example = "Notification Title") String title,
    @Schema(example = "This is a notification message.") String message,
    @Schema(example = "false") boolean read,
    @Schema(example = "2024-06-01T12:00:00Z") LocalDateTime sentAt
  ) {
    public static Response fromEntity(Notification notification) {
      return new Response(
        notification.getId(),
        notification.getUserId(),
        notification.getTo(),
        notification.getTitle(),
        notification.getMessage(),
        notification.isRead(),
        notification.getSentAt()
      );
    }
  }
}
