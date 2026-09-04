package akz.securityusers.services;

import akz.securityusers.entities.User;
import akz.securityusers.services.interfaces.IJwtService;
import akz.securityusers.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

import static akz.securityusers.utils.Constants.ROLE_NAME;

@Service
public class JwtService implements IJwtService {

  @Value("${jwt-configuration.secret-key}")
  private String secretKey;
  @Value("${jwt-configuration.expiration-time}")
  private Long expirationTime;

  public String generateToken(User user) {
    Date currentDate = Utils.getCurrentDateTime();
    return Jwts.builder()
        .subject(user.getUsername())
        .claim(ROLE_NAME, user.getRole().name())
        .issuedAt(currentDate)
        .expiration(Utils.plusSecondsDateTime(currentDate, this.expirationTime))
        .signWith(this.getKey())
        .compact();
  }

  private Claims getAllClaims(String token) {
    return Jwts
        .parser()
        .verifyWith(this.getKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private SecretKey getKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secretKey));
  }

  public String getUsernameFromToken(String token) {
    return this.getClaim(token, Claims::getSubject);
  }

  public String getExtraClaimFromToken(String token, String claimName) {
    return this.getClaim(token, claims ->  claims.get(claimName, String.class));
  }

  public boolean isInvalidToken(String token, UserDetails user) {
    return !user.getUsername().equals(this.getUsernameFromToken(token))
        || !BooleanUtils.isFalse(isTokenExpired(token));
  }

  public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = this.getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Date getExpiration(String token) {
    return this.getClaim(token, Claims::getExpiration);
  }

  private boolean isTokenExpired(String token) {
    return this.getExpiration(token).before(Utils.getCurrentDateTime());
  }
}
