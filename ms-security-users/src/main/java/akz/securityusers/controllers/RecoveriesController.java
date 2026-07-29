package akz.securityusers.controllers;

import akz.commonutils.util.CommonConstants;
import akz.securityusers.dtos.UserDto;
import akz.securityusers.services.interfaces.IRecoveriesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static akz.securityusers.utils.PathConstants.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(RECOVERIES)
@Tag(name = "Recoveries", description = "Endpoints for account recoveries management")
public class RecoveriesController {

  private final IRecoveriesService service;

  @GetMapping(V1 + "/send-recovery-password")
  public ResponseEntity<Void> sendRecoverPassword(
      @RequestParam @Pattern(regexp = CommonConstants.EMAIL_PATTERN) String email) {
    service.sendingRecoveryPassword(email);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping(V1 + "/{id}/recovery-password")
  public ResponseEntity<Void> recoveryPassword(
      @PathVariable Long id,
      @RequestBody UserDto.RecoveryPassword request) {
    service.recoveryPassword(id, request);
    return ResponseEntity.noContent().build();
  }
}
