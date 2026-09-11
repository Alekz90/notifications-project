package akz.notifications.services;

import akz.notifications.dto.kafka.KafkaNotificationResponseDto;
import akz.notifications.dto.kafka.KafkaNotificationSendingDto;
import akz.notifications.services.interfaces.INotificationService;
import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static akz.notifications.util.Constants.*;

@Slf4j
@Service(EMAIL_SERVICE_NAME)
@RequiredArgsConstructor
public class EmailService implements INotificationService {

  private final JavaMailSender mailSender;

  @Override
  public KafkaNotificationResponseDto send(KafkaNotificationSendingDto notification) {

    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, UTF_8);
      String htmlBody = "<html><body><p style='color:blue'>" + notification.getBody() + "</p></body></html>";
      helper.setSubject(notification.getTitle());
      helper.setFrom(notification.getFrom());
      helper.setTo(notification.getTo());
      helper.setText(htmlBody, true);
      mailSender.send(message);

      return KafkaNotificationResponseDto.from(notification);
    } catch (Exception e) {
      log.error("Failed to send email from {} to {}: {}", notification.getFrom(), notification.getTo(), e.getMessage());
      return KafkaNotificationResponseDto.buildFail(notification);
    }
  }
}
