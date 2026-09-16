package akz.notification.management.Configuration;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static akz.notification.management.util.Constants.*;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

  /**
   * Create Kafka topics if they do not exist.
   * @return NewTopic
   */
  @Bean
  public NewTopic sendingTopic() {
    return TopicBuilder
      .name(KAFKA_TOPIC_SENDING_NAME)
      .partitions(1)
      .replicas(1)
      .compact()
      .build();
  }

  /**
   * Create Kafka topics if they do not exist.
   * @return NewTopic
   */
  @Bean
  public NewTopic statusTopic() {
    return TopicBuilder
      .name(KAFKA_TOPIC_STATUS_NAME)
      .partitions(1)
      .replicas(1)
      .compact()
      .build();
  }
}
