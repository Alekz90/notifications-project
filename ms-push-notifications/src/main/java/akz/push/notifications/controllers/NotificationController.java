package akz.push.notifications.controllers;

import akz.commonutils.dto.ResultDto;
import akz.push.notifications.models.NotificationDto;
import akz.push.notifications.services.interfaces.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static akz.push.notifications.utils.Constants.V1;

@RestController
@RequiredArgsConstructor
@RequestMapping("push-notifications")
@Tag(name = "Push Notifications", description = "Push notification endpoints")
public class NotificationController {

    private final NotificationService service;

    @GetMapping(V1 + "/users/{userId}")
    public ResponseEntity<ResultDto<List<NotificationDto.Response>>> getAll(@PathVariable long userId) {
        return ResponseEntity.ok(new ResultDto<>(service.getAll(userId)));
    }

    @PostMapping(V1)
    public ResponseEntity<ResultDto<NotificationDto.Response>> create(@RequestBody NotificationDto.Create note) {
        return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(new ResultDto<>(service.create(note)));
    }

    @PatchMapping(V1 + "/{id}/read")
    public ResponseEntity<ResultDto<Void>> read(@PathVariable String id) {
        service.read(id);
        return ResponseEntity.noContent().build();
    }
}
