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

  @Column(name = "creation_date")
  protected LocalDateTime creationDate;
  @Column(name = "modification_date")
  protected LocalDateTime modificationDate;

  protected AbstractLocalDateFields() {
    this.creationDate = CommonUtils.getCurrentLocalDateTime();
    this.modificationDate = CommonUtils.getCurrentLocalDateTime();
  }
}
