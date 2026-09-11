package akz.notifications.remoto.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMSMessageResponseDto {
   private String id;
   private String status;
   @JsonAlias("created_at")
   private LocalDateTime createdAt;
   private SMSMessageDto message;
}
