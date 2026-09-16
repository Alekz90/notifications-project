package akz.notification.management.dto;

import akz.notification.management.entities.EmailNotification;
import akz.notification.management.entities.PushNotification;
import akz.notification.management.entities.SMSNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationNotificationModel {
  private Long totalElements;
  private Integer totalPages;
  private List<NotificationModel> notifications;

  /**
   * Create a PaginationNotificationModel from a list of EmailNotification entities.
   *
   * @param notifications the list of EmailNotification entities
   * @param totalElements the total number of elements
   * @param totalPages the total number of pages
   * @return a PaginationNotificationModel containing the provided data
   */
  public static PaginationNotificationModel fromEmailEntities(
    List<EmailNotification> notifications, Long totalElements, int totalPages) {
    return PaginationNotificationModel.builder()
      .totalElements(totalElements)
      .totalPages(totalPages)
      .notifications(
        notifications.stream()
        .map(NotificationModel::fromEntity)
        .toList())
      .build();
  }

  public static PaginationNotificationModel fromSMSEntities(
    List<SMSNotification> notifications, Long totalElements, int totalPages) {
    return PaginationNotificationModel.builder()
      .totalElements(totalElements)
      .totalPages(totalPages)
      .notifications(
        notifications.stream()
          .map(NotificationModel::fromEntity)
          .toList())
      .build();
  }

  public static PaginationNotificationModel fromPushEntities(
    List<PushNotification> notifications, Long totalElements, int totalPages) {
    return PaginationNotificationModel.builder()
      .totalElements(totalElements)
      .totalPages(totalPages)
      .notifications(
        notifications.stream()
          .map(NotificationModel::fromEntity)
          .toList())
      .build();
  }
}
