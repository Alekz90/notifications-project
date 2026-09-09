package akz.notification.management.controller;

import akz.commonutils.dto.ResultDto;
import akz.commonutils.util.CommonUtils;
import akz.notification.management.dto.NotificationDto;
import akz.notification.management.dto.validations.NotificationGroup.DefaultGroup;
import akz.notification.management.service.interfaces.INotificationService;
import akz.notification.management.util.enums.ECanal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static akz.notification.management.util.Constants.V1;

@RestController
@RequiredArgsConstructor
@RequestMapping("notifications")
@Tag(name = "Notifications", description = "Notification endpoints management")
public class NotificationController {

  private final INotificationService service;

  @GetMapping(V1 + "/{id}")
  @Operation(summary = "Get notification by ID")
  public ResponseEntity<ResultDto<NotificationDto.ResponseDetail>> getById(
    @PathVariable Long id, @RequestParam(required = false, defaultValue = "NONE") ECanal canal
  ) {
    return ResponseEntity.ok(new ResultDto<>(service.getById(id, canal)));
  }

  @GetMapping(V1 + "/users/{userId}")
  @Operation(summary = "Get all notifications by user ID")
  public ResponseEntity<ResultDto<NotificationDto.PaginationResponse>> getAllByUserId(
    @PathVariable Long userId,
    @RequestParam(required = false, defaultValue = "10") int size,
    @RequestParam(required = false, defaultValue =  "1") int page,
    @RequestParam(required = false, defaultValue = "NONE") ECanal canal
  ) {
    return ResponseEntity.ok(new ResultDto<>(service.getAllByUserId(userId, size, page, canal)));
  }

  @PostMapping(V1 + "/users/{userId}")
  @Operation(summary = "Create notification")
  public ResponseEntity<ResultDto<NotificationDto.ResponseDetail>> create(
    @PathVariable Long userId,
    @RequestBody @Validated(DefaultGroup.class) NotificationDto.Register notificationDto
  ) {
    NotificationDto.ResponseDetail response = service.create(userId, notificationDto);
    return ResponseEntity
      .created(CommonUtils.buildUriPost("/notifications/v1/", response.id()))
      .body(new ResultDto<>(response));
  }

  @PutMapping(V1 + "/{id}")
  @Operation(summary = "Update notification")
  public ResponseEntity<ResultDto<NotificationDto.ResponseDetail>> update(
    @PathVariable Long id,
    @RequestBody @Validated(DefaultGroup.class) NotificationDto.Register notificationDto
  ) {
    NotificationDto.ResponseDetail response = service.update(id, notificationDto);
    return ResponseEntity.ok(new ResultDto<>(response));
  }

  @DeleteMapping(V1 + "/{id}")
  @Operation(summary = "Delete notification")
  public ResponseEntity<Void> delete(
    @PathVariable Long id, @RequestHeader LocalDateTime updatedAt,
    @RequestParam(required = false, defaultValue = "NONE") ECanal canal
  ) {
    service.delete(id, updatedAt, canal);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping(V1 + "/{id}/send")
  @Operation(summary = "Send notification")
  public ResponseEntity<Void> send(
    @PathVariable Long id, @RequestHeader LocalDateTime updatedAt,
    @RequestParam(required = false, defaultValue = "NONE") ECanal canal
  ) {
    service.send(id,  updatedAt, canal);
    return ResponseEntity.noContent().build();
  }
}
