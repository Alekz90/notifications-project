package akz.notificationsmanagement.remote.dto;

public record NotificationDto(Long id, String title, String message, String recipient, String status) {

}
