package akz.notifications.services;

import akz.notifications.dto.kafka.KafkaNotificationResponseDto;
import akz.notifications.dto.kafka.KafkaNotificationSendingDto;
import akz.notifications.services.interfaces.IKafkaNotificationConsumerService;
import akz.notifications.services.interfaces.IKafkaNotificationProducerService;
import akz.notifications.services.interfaces.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * KafkaNotificationConsumerService is a service class that implements the IKafkaNotificationConsumerService interface.
 * It is responsible for receiving notification events from Kafka and sending response events back to Kafka.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaNotificationConsumerService implements IKafkaNotificationConsumerService {

  private final IKafkaNotificationProducerService kafkaService;
  private final Map<String, INotificationService> strategies;

  /**
   * Receives notification events from Kafka and sends response events back to Kafka.
   * @param notificationData The notification data received from Kafka.
   */
  @Override
  public void receiveNotificationEvent(KafkaNotificationSendingDto notificationData) {
    KafkaNotificationResponseDto response =
      strategies.get(notificationData.getCanal().getServiceName()).send(notificationData);
    kafkaService.sendResponseEvent(response);
  }
}
