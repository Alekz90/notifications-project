package akz.notification.management.dto;

import akz.commonutils.annotation.ValidEmail;
import akz.commonutils.annotation.ValidPhone;
import akz.commonutils.annotation.ValidSpecialText;
import akz.commonutils.annotation.ValidTitleText;
import akz.notification.management.dto.validations.NotificationGroup.*;
import akz.notification.management.util.enums.ECanal;
import akz.notification.management.util.enums.EMessageStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationDto {

  @Schema(name = "NotificationDto.Register", description = "DTO for registering a new notification")
  public record Register(
    @NotBlank(groups = DefaultGroup.class, message = "Title is required")
    @Size(groups = DefaultGroup.class, max = 150, message = "Title must be less than 150 characters")
    @ValidTitleText(groups = DefaultGroup.class, message = "Title contains invalid characters")
    String title,

    @NotBlank(groups = DefaultGroup.class, message = "Body is required")
    @Size(groups = SaveEmail.class, max = 500, message = "Body must be less than 500 characters")
    @Size(groups = SaveSMS.class, max = 160, message = "Body must be less than 160 characters")
    @Size(groups = SavePush.class, max = 200, message = "Body must be less than 200 characters")
    @ValidSpecialText(groups = DefaultGroup.class, message = "Body contains invalid characters")
    String body,

    @NotBlank(groups = DefaultGroup.class, message = "Sender is required")
    @Size(max = 150, groups = {SaveEmail.class, SavePush.class}, message = "Sender must be less than 150 characters")
    @ValidEmail(groups = SaveEmail.class, message = "Sender must be a valid email address")
    @ValidPhone(groups = SaveSMS.class, message = "Sender must be a valid phone number")
    @Size(max = 100, groups = {SavePush.class}, message = "Sender must be less than 100 characters")
    String from,

    @NotBlank(groups = DefaultGroup.class, message = "Recipient is required")
    @Size(max = 150, groups = {SaveEmail.class, SavePush.class}, message = "Recipient must be less than 150 characters")
    @ValidEmail(groups = SaveEmail.class, message = "Recipient must be a valid email address")
    @ValidPhone(groups = SaveSMS.class, message = "Recipient must be a valid phone number")
    @Size(max = 100, groups = {SavePush.class}, message = "Recipient must be less than 100 characters")
    String to,

    @NotNull(groups = DefaultGroup.class, message = "Canal is required")
    ECanal canal,

    @NotNull(groups = { UpdateEmail.class, UpdateSMS.class, UpdatePush.class }, message = "Updated date is required")
    LocalDateTime updatedAt
  ) {
    public static NotificationModel toNotificationModel(Long userId, NotificationDto.Register register) {
      return NotificationModel.builder()
        .userId(userId)
        .title(register.title())
        .body(register.body())
        .from(register.from())
        .to(register.to())
        .build();
    }
  }

  @Schema(name = "NotificationDto.ResponseAll", description = "DTO for replying with the notification data")
  public record ResponseAll(Long id, String title, String body, String to, EMessageStatus status, LocalDateTime updatedAt) {

    public static ResponseAll fromEntity(NotificationModel model) {
      return new ResponseAll(
        model.getId(), model.getTitle(), model.getBody(), model.getTo(), model.getStatus(), model.getUpdatedAt()
      );
    }
  }

  @Schema(name = "NotificationDto.PaginationResponse", description = "DTO for replying with the notification data in a paginated response")
  public record PaginationResponse(Long totalElements, Integer totalPages, List<ResponseAll> notifications) {

    public static PaginationResponse build(PaginationNotificationModel paginationModel) {
      return new PaginationResponse(
        paginationModel.getTotalElements(),
        paginationModel.getTotalPages(),
        paginationModel.getNotifications()
          .stream()
          .map(ResponseAll::fromEntity)
          .toList()
      );
    }
  }

  @Schema(name = "NotificationDto.ResponseDetail", description = "DTO for replying with the notification data")
  public record ResponseDetail(Long id, String title, String body, String from, String to, EMessageStatus status,
                               LocalDateTime sentAt, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static ResponseDetail fromEntity(NotificationModel email) {
      return new ResponseDetail(
        email.getId(), email.getTitle(), email.getBody(), email.getFrom(), email.getTo(), email.getStatus(), email.getSentAt(),
        email.getCreatedAt(), email.getUpdatedAt()
      );
    }
  }
}
