package akz.securityusers.controllers;

import akz.commonutils.annotation.ValidUsername;
import akz.commonutils.dto.ResultDto;
import akz.securityusers.dtos.UserDto;
import akz.securityusers.services.interfaces.IUsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static akz.securityusers.utils.PathConstants.USERS;
import static akz.securityusers.utils.PathConstants.V1;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(USERS)
@Tag(name = "Users", description = "User endpoints management")
public class UsersController {

  private final IUsersService service;

  @GetMapping(V1 + "/{id}")
  @Operation(summary = "Get user by ID", security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<ResultDto<UserDto.UserResponse>> getCurrentUser(@PathVariable Long id) {
    return ResponseEntity.ok(new ResultDto<>(service.getUserById(id)));
  }

  @GetMapping(V1 + "/username/{username}")
  @Operation(summary = "Get user by username", security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<ResultDto<UserDto.UserResponse>> getUserByUsername(@PathVariable @ValidUsername String username) {
    return ResponseEntity.ok(new ResultDto<>(service.getUserByUsername(username)));
  }

  @PatchMapping(V1 + "/change-password/{id}")
  @Operation(summary = "Change user password", security = @SecurityRequirement(name = "bearerAuth"))
  public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody UserDto.ChangePassword request) {
    service.changePassword(id, request);
    return ResponseEntity.noContent().build();
  }
}
