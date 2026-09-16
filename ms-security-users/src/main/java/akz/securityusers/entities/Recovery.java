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
@Entity(name = "recoveries")
@Table(comment = "Table for storing recovery codes")
public class Recovery extends AbstractLocalDateFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  @Column(name = "expired_at")
  private LocalDateTime expiredAt;
  @Column(name = "used_at")
  private LocalDateTime usedAt;
  private boolean used;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  /**
   * Constructor for Recovery entity.
   * @param email
   * @param user
   */
  public Recovery(String email, User user) {
    super();
    this.email = email;
    this.user = user;
    this.expiredAt = CommonUtils.getCurrentLocalDateTime().plusHours(Constants.VERIFICATION_EXPIRATION_HOURS);
    this.usedAt = CommonUtils.getDateTimeZero();
    this.used = false;
  }
}
