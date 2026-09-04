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
  @Column(name = "expired_at")
  private LocalDateTime expiredAt;
  @Column(name = "used_at")
  private LocalDateTime usedAt;
  private boolean used;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public Verification(User user, String code) {
    super();
    this.user = user;
    this.code = code;
    this.expiredAt = CommonUtils.getCurrentLocalDateTime().plusHours(Constants.VERIFICATION_EXPIRATION_HOURS);
    this.usedAt = CommonUtils.getDateTimeZero();
    this.used = false;
  }
}
