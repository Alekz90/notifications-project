package akz.securityusers.controllers;

import akz.commonutils.dto.ResultDto;
import akz.securityusers.dtos.UserDto;
import akz.securityusers.services.interfaces.IAuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static akz.securityusers.utils.PathConstants.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(AUTHENTICATIONS)
@Tag(name = "Authentication", description = "Authentication management endpoints")
public class AuthenticationController {

  private final IAuthenticationService service;

  @PostMapping(V1 + "/register")
  public ResponseEntity<ResultDto<UserDto.Authentication>> register(@Valid @RequestBody UserDto.Register request) {
    return ResponseEntity.ok(new ResultDto<>(service.register(request)));
  }

  @PostMapping(V1 + "/login")
  public ResponseEntity<ResultDto<UserDto.Authentication>> login(@Valid @RequestBody UserDto.Login request) {
    return ResponseEntity.ok(new ResultDto<>(service.login(request)));
  }

  @GetMapping(V1 + "/check-status")
  public ResponseEntity<ResultDto<UserDto.Authentication>> checkStatus(
      @RequestHeader(value = "Authorization", required = true) String tokenHeader) {
    return ResponseEntity.ok(new ResultDto<>(service.checkStatus(tokenHeader)));
  }
}
