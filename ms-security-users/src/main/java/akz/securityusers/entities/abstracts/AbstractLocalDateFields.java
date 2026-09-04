package akz.securityusers.entities.abstracts;

import akz.commonutils.util.CommonUtils;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractLocalDateFields {

  @Column(name = "created_at")
  protected LocalDateTime createdAt;
  @Column(name = "updated_at")
  protected LocalDateTime updatedAt;

  protected AbstractLocalDateFields() {
    this.createdAt = CommonUtils.getCurrentLocalDateTime();
    this.updatedAt = CommonUtils.getCurrentLocalDateTime();
  }
}
