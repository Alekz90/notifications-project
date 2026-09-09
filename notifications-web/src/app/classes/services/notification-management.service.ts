import type { Response } from "@interfaces/response.interface";
import axios from "axios";
import { CanalEnum } from "@enums/canal-enum";
import type { NotificationDto, NotificationListResponse } from "@interfaces/notification.interface";
import { Constants } from "@utils/constants";
import { MOCK_DELETE_NOTIFICATION_RESPONSE, MOCK_EMAIL_NOTIFICATIONS_RESPONSE, MOCK_NEW_NOTIFICATION_RESPONSE, MOCK_PUSH_NOTIFICATIONS_RESPONSE, MOCK_SEND_NOTIFICATION_RESPONSE, MOCK_SMS_NOTIFICATIONS_RESPONSE } from "../mocks/mock-response";

const NotificationsApi = axios.create({
  baseURL: import.meta.env.VITE_NOTIFICATIONS_API_URL,
});

NotificationsApi.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = token;
  }
  return config;
});

const NOTIFICATIONS_V1_URL = "/v1";

export const getNotification = async (id: number, canal: CanalEnum)
  : Promise<Response<NotificationDto>> => {
  
  const params = { canal: canal };

  return NotificationsApi.get(`${NOTIFICATIONS_V1_URL}/${id}`, { params })
    .then(response => response.data)
    .catch(error => catchError(error, "Error fetching notification: "))
  ;
}

export const getNotifications = async (
  userId: number, canal: CanalEnum, page = Constants.PAGE_DEFAULT, size = Constants.SIZE_DEFAULT
) : Promise<Response<NotificationListResponse>> => {
  
  const params = { canal, page, size };
  
  return NotificationsApi.get(`${NOTIFICATIONS_V1_URL}/users/${userId}`, { params })
    .then(response => response.data)
    .catch(error => catchError(error, "Error fetching notifications: "));
};

export const createNotification = async (userId: number, notification: Partial<NotificationDto>)
  : Promise<Response<NotificationDto>> => {
  
  return NotificationsApi.post(`${NOTIFICATIONS_V1_URL}/users/${userId}`, notification)
    .then(response => response.data)
    .catch(error => catchError(error, "Error creating notification: "));
};

export const updateNotification = async (id: number, notification: Partial<NotificationDto>)
  : Promise<Response<NotificationDto>> => {
  
  return NotificationsApi.put(`${NOTIFICATIONS_V1_URL}/${id}`, notification)
    .then(response => response.data)
    .catch(error => catchError(error, "Error updating notification: "));
};

export const sendNotification = async (id: number, updatedAt: string, canal: CanalEnum)
  : Promise<Response<null>> => {  
  
  const params = { canal };
  const headers = { updatedAt };

  return NotificationsApi.patch(`${NOTIFICATIONS_V1_URL}/${id}/send`, { params, headers })
    .then(response => response.data)
    .catch(error => catchError(error, "Error sending notification: "));
};

export const deleteNotification = async (id: number, updatedAt: string, canal: CanalEnum)
  : Promise<Response<null>> => {

  const params = { canal };
  const headers = { updatedAt };
  
  return NotificationsApi.delete(`${NOTIFICATIONS_V1_URL}/${id}`, { params, headers })
    .then(response => response.data)
    .catch(error => catchError(error, "Error deleting notification: "));
};

const catchError = (error: any, message: string): void => {
  console.error(message, error?.response?.data?.message || error.message);
  throw error;
};

/*
export const getNotification = async (id: number, canal: CanalEnum)
  : Promise<Response<NotificationDto>> => {
  
  const params = { canal: canal };

  return NotificationsApi.get(`${NOTIFICATIONS_V1_URL}/${id}`, { params })
    .then(response => response.data)
    .catch(error => catchError(error, "Error fetching notification: "))
  ;
}

export const getNotifications = async (
  userId: number, canal: CanalEnum, page = Constants.PAGE_DEFAULT, size = Constants.SIZE_DEFAULT
) : Promise<Response<NotificationListResponse>> => {
  
  const params = { canal, page, size };
  
  return NotificationsApi.get(`${NOTIFICATIONS_V1_URL}/users/${userId}`, { params })
    .then(response => response.data)
    .catch(error => catchError(error, "Error fetching notifications: "));
 
  let notificationsResponse: Response<NotificationListResponse>;
  switch (canal) {
    case CanalEnum.EMAIL:
      notificationsResponse = { ...MOCK_EMAIL_NOTIFICATIONS_RESPONSE };
      break;
    case CanalEnum.SMS:
      notificationsResponse = { ...MOCK_SMS_NOTIFICATIONS_RESPONSE };
      break;
    case CanalEnum.PUSH:
      notificationsResponse = { ...MOCK_PUSH_NOTIFICATIONS_RESPONSE };
      break;
    default:
      notificationsResponse = { ...MOCK_EMAIL_NOTIFICATIONS_RESPONSE };
      break;
  }
  
  const totalElements = notificationsResponse.result.notifications.length;
  const totalPages = Math.ceil(totalElements / Number(size));
  const slicedNotifications = notificationsResponse.result.notifications.slice((Number(page) - 1) * Number(size), Number(page) * Number(size));

  notificationsResponse.result = {
    notifications: slicedNotifications,
    totalElements,
    totalPages,
  };
  
  return Promise.resolve(notificationsResponse);
};

export const createNotification = async (userId: number, notification: Partial<NotificationDto>)
  : Promise<Response<NotificationDto>> => {
  
  return Promise.resolve({ ...MOCK_NEW_NOTIFICATION_RESPONSE, result: { ...MOCK_NEW_NOTIFICATION_RESPONSE.result , ...notification }});
};

export const updateNotification = async (id: number, notification: Partial<NotificationDto>, oldNotification: NotificationDto)
  : Promise<Response<NotificationDto>> => {
  return Promise.resolve({ ...MOCK_NEW_NOTIFICATION_RESPONSE, result: { ...oldNotification, ...notification } });
};

export const sendNotification = async (id: number, updatedAt: string, canal: CanalEnum)
  : Promise<Response<null>> => {
  return Promise.resolve(MOCK_SEND_NOTIFICATION_RESPONSE);
};

export const deleteNotification = async (id: number, updatedAt: string, canal: CanalEnum)
  : Promise<Response<null>> => {
  return Promise.resolve(MOCK_DELETE_NOTIFICATION_RESPONSE);
};
*/
