package akz.securityusers.configurations;

import akz.securityusers.services.interfaces.IJwtService;
import akz.securityusers.utils.enums.EError;
import akz.commonutils.dto.ResultDto;
import akz.commonutils.exception.CustomCommonException;
import akz.commonutils.util.CommonUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static akz.securityusers.utils.Constants.BEARER;
import static akz.securityusers.utils.PathConstants.WHITELIST_PATTERNS;
import static akz.commonutils.util.CommonConstants.*;

@Log4j2
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final IJwtService jwtService;
  private final UserDetailsService userDetailsService;

  public JwtAuthenticationFilter(IJwtService jwtService, @Lazy UserDetailsService userDetailsService) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(token) && token.startsWith(BEARER)) {
      token = token.replace(BEARER, EMPTY_STRING);
    }

    if (!existsPathInWhitelist(request.getRequestURI())) {
      if (!StringUtils.hasText(token)) {
        this.customResponseError(response);
      } else {
        if (this.isTokenInvalid(request, token)){
          this.customResponseError(response);
        }
      }
    }

    filterChain.doFilter(request, response);
  }

  private boolean existsPathInWhitelist(String requestPath) {
    if (requestPath.contains(QUESTION_MARK)) {
      requestPath = requestPath.replace(QUESTION_MARK, EMPTY);
    }
    return WHITELIST_PATTERNS.parallelStream().anyMatch(requestPath::matches);
  }

  private boolean isTokenInvalid(HttpServletRequest request, String token) {
    try {
      final String username = jwtService.getUsernameFromToken(token);

      if (StringUtils.hasText(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtService.isInvalidToken(token, userDetails)) {
          return true;
        }

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return true;
    }
    return false;
  }

  private void customResponseError(HttpServletResponse response) throws IOException {
    ResultDto<CustomCommonException> resultDto =
        new ResultDto<>(new CustomCommonException(HttpStatus.FORBIDDEN, EError.USER_FORBIDDEN));

    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.getWriter().print(CommonUtils.getJson(resultDto));
    response.getWriter().flush();
  }
}
