package akz.notifications.remoto.feign;

import akz.notifications.remoto.dto.SMSMessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sms-api", url = "${sms-api.url}")
public interface ISMSRemoteFeign {

  @PostMapping("/messages")
  void sendSMS(@RequestBody SMSMessageDto request);
}
