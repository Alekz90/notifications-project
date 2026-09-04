import type { NotificationDto } from "@interfaces/notification.interface"
import { createContext, useState, type PropsWithChildren } from "react";

interface NotificationContextProps {
  notification: NotificationContextType,
  notifications: NotificationDto[],
  setNotification(notification: NotificationContextType): void;
  setNotifications(notifications: NotificationDto[]): void;
}

type NotificationContextType = NotificationDto | null;

export const NotificationContext = createContext({} as NotificationContextProps);

export const NotificationContextProvider = ({ children }: PropsWithChildren) => {
  const [notification, setNotification] = useState<NotificationContextType>(null);
  const [notifications, setNotifications] = useState<NotificationDto[]>([]);

  return (
    <NotificationContext value={{ notification, setNotification, notifications, setNotifications }}>
      {children}
    </NotificationContext>
  )
}
