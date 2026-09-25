package akz.push.notifications.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

  /**
   * Configure message broker options.
   *
   * @param registry the message broker registry
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // Topic examples: topic/push, topic/notification
    registry.enableSimpleBroker("/topic");
    // Application destination prefix example: /app/push, /app/notifications
    registry.setApplicationDestinationPrefixes("/notifications-web");
    // User destination prefix example: /user/push, /user/notifications
    registry.setUserDestinationPrefix("/user");
  }

  /**
   * Register STOMP endpoints mapping each to a specific URL and (optionally)
   * enabling and configuring SockJS fallback options.
   *
   * @param registry the STOMP endpoint registry
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // Spring translates this call to /user/{userId}/queue/notifications internally, where {userId} is the user identifier.
    registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("*")
            .setHandshakeHandler(new CustomHandshakeHandler())
            .withSockJS();
  }
}
