package akz.securityusers.controllers;

import akz.commonutils.dto.ResultDto;
import akz.commonutils.util.CommonConstants;
import akz.securityusers.services.interfaces.IVerificationsService;
import akz.securityusers.utils.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static akz.securityusers.utils.PathConstants.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(VERIFICATIONS)
@Tag(name = "Verifications", description = "Endpoints for user verifications")
public class VerificationsController {

  private final IVerificationsService service;

  @GetMapping(V1 + "/{id}/verify/{code}" )
  public ResponseEntity<ResultDto<Boolean>> verifyAccount(
    @PathVariable Long id,
    @PathVariable @Pattern(regexp = Constants.VERIFICATION_CODE_PATTERN) String code) {
    service.markVerificationAsUsed(id, code);
    return ResponseEntity.ok(new ResultDto<>(true));
  }
}
