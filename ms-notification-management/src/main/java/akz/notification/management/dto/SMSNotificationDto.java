package akz.notification.management.dto;

import akz.commonutils.annotation.ValidPhone;
import akz.commonutils.annotation.ValidSpecialText;
import akz.commonutils.annotation.ValidTittleText;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SMSNotificationDto {

  @Schema(name = "PushNotificationDto.Register", description = "DTO for registering a new notification")
  public record Register (
    @NotBlank @ValidTittleText @Size(max = 150) String title,
    @NotBlank @ValidSpecialText @Size(max = 160) String body,
    @NotBlank @ValidPhone String recipient
  ) { }

  public static NotificationDto.Register fromRecord(Register data) {
    return new NotificationDto.Register(data.title, data.body, data.recipient);
  }
}
