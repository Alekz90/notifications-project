import { UserContext } from "@/app/context/UserContext";
import type { NotificationFormData } from "@/app/management/notifications/pages/notification-list/components/notification-schema";
import { UseNotificationContext } from "@context/NotificationContext";
import type { CanalEnum } from "@enums/canal-enum";
import { createNotification, deleteNotification, getNotification, getNotifications, sendNotification, updateNotification } from "@services/notification-management.service";
import { Constants } from "@utils/constants";
import { StatusEnum } from "@utils/enums/status-enum";
import { useContext, useEffect, useState } from "react";

export const UseCustomGetNotifications = (canal: CanalEnum, page: string, pageSize: string) => {
  const { user } = useContext(UserContext);
  const { notifications, setNotifications } = useContext(UseNotificationContext);
  const [ totalPages, setTotalPages ] = useState(0);
  const [ totalElements, setTotalElements ] = useState(0);

  useEffect(() => {
    if (!user) return;
    getNotifications(user.id, canal, page, pageSize)
      .then(response => {
        if (response.id === Constants.SUCCESS_CODE) {
          setNotifications(response.result.notifications);
          setTotalPages(response.result.totalPages);
          setTotalElements(response.result.totalElements);
        }
      });
  }, [ user, canal, page, pageSize ]);

  return { notifications, setNotifications, totalPages, totalElements };
}

export const UseCustomGetNotificationById = (canal: CanalEnum) => {
  const { notification, setNotification } = useContext(UseNotificationContext);

  useEffect(() => {
    if (!notification) return;
    getNotification(notification.id, canal)
      .then((response) => {
        if (response.id === Constants.SUCCESS_CODE) {
          setNotification({ ...response.result });
        }}
      )
  }, [notification, canal]);

  return { notification };  
};

export const UseCustomSendAndDropNotification = (tabValue: CanalEnum) => {
  const { notifications, setNotifications, notification, setNotification } = useContext(UseNotificationContext);

  const sendFn = () => {
    if (!notification) return;
    sendNotification(notification.id, notification.updatedAt, tabValue)
      .then((response) => {
        if (response.id === Constants.SUCCESS_CODE) {
          notification.status = StatusEnum.SENDING;
          setNotifications([ ...notifications ]);
        }
      }).finally(() => {
        setNotification(null);
      });
    }

    const dropFn = () => {
    if (!notification) return;
    deleteNotification(notification.id, notification.updatedAt, tabValue)
      .then((response) => {
        if (response.id === Constants.SUCCESS_CODE) {
          setNotifications(notifications.filter(n => n.id !== notification.id));
        }
      }).finally(() => {
        setNotification(null);
      });
    }
  
  return { sendFn, dropFn };
}

export const UseCustomCreateUpdateNotification = (tabValue: CanalEnum) => {
  const { user } = useContext(UserContext);
  const { notification, notifications, setNotifications } = useContext(UseNotificationContext);
  const [ isOpen, setIsOpen ] = useState(false);

  const onSubmit = (notificationData: NotificationFormData) => {
    const updatedNotificationData = { ...notificationData, canal: tabValue };
    if (!notificationData.isNew) {
      if (!notification) return;
      updateNotification(notification.id, updatedNotificationData)
        .then(response => {
          if (response.id !== Constants.SUCCESS_CODE) {
            setIsOpen(true);
            return;
          }
          const updatedNotifications = notifications.map(n => n.id === response.result.id ? response.result : n);
          setNotifications(updatedNotifications);
          setIsOpen(false);
        }).catch(() => {
          setIsOpen(true);
        });
    } else {
      if (!user) return;
      createNotification(user.id, updatedNotificationData)
        .then(response => {
          if (response.id !== Constants.SUCCESS_CODE) {
            setIsOpen(true);
            return;
          }
          setNotifications([ ...notifications, response.result ]);
          setIsOpen(false);
        }).catch(() => {
          setIsOpen(true);
        });
      }
    }

  return { notification, notifications, setNotifications, onSubmit, isOpen, setIsOpen };
}
