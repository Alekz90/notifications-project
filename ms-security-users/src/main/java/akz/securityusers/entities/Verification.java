package akz.securityusers.entities;

import akz.securityusers.entities.abstracts.AbstractLocalDateFields;
import akz.securityusers.utils.Constants;
import akz.commonutils.util.CommonUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "verifications")
@Table(comment = "Table for storing verification codes")
public class Verification extends AbstractLocalDateFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String code;
  @Column(name = "expiration_date")
  private LocalDateTime expirationDate;
  @Column(name = "used_date")
  private LocalDateTime usedDate;
  private boolean used;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public Verification(User user, String code) {
    super();
    this.user = user;
    this.code = code;
    this.expirationDate = CommonUtils.getCurrentLocalDateTime().plusHours(Constants.VERIFICATION_EXPIRATION_HOURS);
    this.usedDate = CommonUtils.getDateTimeZero();
    this.used = false;
  }
}
