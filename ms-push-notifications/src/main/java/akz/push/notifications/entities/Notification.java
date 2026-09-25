package akz.push.notifications.entities;

import akz.commonutils.util.CommonUtils;
import akz.push.notifications.models.NotificationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

/**
 * Notification entity representing a push notification.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("notifications")
public class Notification {

    @Id
    private String id;
    @Indexed
    private long userId;
    private String to;
    private String title;
    private String message;
    private boolean read;
    private LocalDateTime sentAt;

    /**
     * Constructs a new Notification with the specified recipient, title, and message.
     * The notification is marked as unread by default.
     *
     * @param to      the username recipient of the notification
     * @param title   the title of the notification
     * @param message the message content of the notification
     */
    public Notification(long userId, String to, String title, String message) {
        this.userId = userId;
        this.to = to;
        this.title = title;
        this.message = message;
        this.read = false;
        this.sentAt = CommonUtils.getCurrentLocalDateTime();
    }

    /**
     * Converts a NotificationDto.Create object to a Notification entity.
     *
     * @param dto the NotificationDto.Create object
     * @return a new Notification entity
     */
    public static Notification fromDto(NotificationDto.Create dto) {
        return new Notification(dto.userId(), dto.to(), dto.title(), dto.message());
    }
}
