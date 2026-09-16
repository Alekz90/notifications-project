package akz.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsKafkaNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsKafkaNotificationsApplication.class, args);
	}

}
