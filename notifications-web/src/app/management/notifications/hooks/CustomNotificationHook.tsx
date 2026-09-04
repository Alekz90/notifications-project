import type { NotificationDto } from "@interfaces/notification.interface";
import { getNotifications } from "@services/notification-management.service";
import { Constants } from "@utils/constants";
import type { CanalEnum } from "@enums/canal-enum";
import { useEffect, useState } from "react";

export const UseCustomGetNotifications = (userId: number, tabValue: CanalEnum) => {
  
  const [notifications, setNotifications] = useState<NotificationDto[]>([]);

  useEffect(() => {
    console.log('Fetching notifications for user:', userId, 'tab:', tabValue);
    if (!userId) return;
    getNotifications(userId, tabValue)
      .then(response => {
        if (response.id === Constants.SUCCESS_CODE) {
          setNotifications(response.result);
          console.log('Fetched notifications:', response.result);
        }
      });
  }, [ userId, tabValue ])

  return { notifications, setNotifications };
}