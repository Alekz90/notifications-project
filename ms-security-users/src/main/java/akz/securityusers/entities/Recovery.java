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
  @Column(name = "expiration_date")
  private LocalDateTime expirationDate;
  @Column(name = "used_date")
  private LocalDateTime usedDate;
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
    this.expirationDate = CommonUtils.getCurrentLocalDateTime().plusHours(Constants.VERIFICATION_EXPIRATION_HOURS);
    this.usedDate = CommonUtils.getDateTimeZero();
    this.used = false;
  }
}
