package akz.push.notifications.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

import static akz.push.notifications.utils.Constants.WS_QUERY;

@Slf4j
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

  @Override
  protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

    String url = request.getURI().toString();
    log.info("WebSocket connection established. URL: {}", url);

    String userId = url.contains(WS_QUERY)
      ? url.substring(url.indexOf(WS_QUERY) + WS_QUERY.length()) : "0";

    log.info("WebSocket connection established. User ID: {}", userId);
    return () -> userId;
  }
}
