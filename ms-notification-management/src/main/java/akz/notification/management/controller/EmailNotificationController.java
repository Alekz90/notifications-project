package akz.notification.management.controller;

import akz.commonutils.dto.ResultDto;
import akz.commonutils.util.CommonUtils;
import akz.notification.management.dto.EmailNotificationDto;
import akz.notification.management.dto.NotificationDto;
import akz.notification.management.dto.PushNotificationDto;
import akz.notification.management.service.interfaces.INotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static akz.notification.management.util.Constants.EMAIL_SERVICE_NAME;
import static akz.notification.management.util.Constants.PUSH_SERVICE_NAME;

@Validated
@RestController
@RequestMapping("v1/email-notifications")
@Tag(name = "Email Notifications", description = "Email notification endpoints management")
public class EmailNotificationController {

  private final INotificationService service;

  public EmailNotificationController(@Qualifier(EMAIL_SERVICE_NAME) INotificationService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get notification by ID")
  public ResponseEntity<ResultDto<NotificationDto.Response>> getById(@PathVariable long id) {
    return ResponseEntity.ok(new ResultDto<>(service.getById(id)));
  }

  @GetMapping("/users/{userId}")
  @Operation(summary = "Get all notifications by user ID")
  public ResponseEntity<ResultDto<List<NotificationDto.Response>>> getAllByUserId(@PathVariable long userId) {
    return ResponseEntity.ok(new ResultDto<>(service.getAllByUserId(userId)));
  }

  @PostMapping("/users/{userId}")
  @Operation(summary = "Create notification")
  public ResponseEntity<ResultDto<NotificationDto.Response>> create(
    @PathVariable long userId, @RequestBody @Valid EmailNotificationDto.Register notificationDto) {
    NotificationDto.Response response = service.create(userId, EmailNotificationDto.fromRecord(notificationDto));
    return ResponseEntity
      .created(CommonUtils.buildUriPost("/email-notifications/v1/", response.id()))
      .body(new ResultDto<>(response));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update notification")
  public ResponseEntity<ResultDto<NotificationDto.Response>> update(
    @PathVariable long id, @RequestHeader LocalDateTime updatedAt,
    @RequestBody @Valid EmailNotificationDto.Register notificationDto) {
    NotificationDto.Response response = service.update(id, updatedAt, EmailNotificationDto.fromRecord(notificationDto));
    return ResponseEntity.ok(new ResultDto<>(response));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete notification")
  public ResponseEntity<Void> delete(
    @PathVariable long id, @RequestHeader LocalDateTime updatedAt) {
    service.delete(id, updatedAt);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/send")
  @Operation(summary = "Send notification")
  public ResponseEntity<Void> send(
    @PathVariable long id, @RequestHeader LocalDateTime updatedAt) {
    service.send(id,  updatedAt);
    return ResponseEntity.noContent().build();
  }
}
