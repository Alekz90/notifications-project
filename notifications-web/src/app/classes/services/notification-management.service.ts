import type { Response } from "@interfaces/response.interface";
import axios from "axios";
import { CanalEnum } from "@enums/canal-enum";
import type { NotificationDto } from "@interfaces/notification.interface";
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
  userId: number, canal: CanalEnum, page: number = Constants.PAGE_DEFAULT, size: number = Constants.SIZE_DEFAULT
) : Promise<Response<NotificationDto[]>> => {
  /*
  const params = { canal, page, size };
  
  return NotificationsApi.get(`${NOTIFICATIONS_V1_URL}/users/${userId}`, { params })
    .then(response => response.data)
    .catch(error => catchError(error, "Error fetching notifications: "));
  */
 
  return Promise.resolve(
    canal === CanalEnum.EMAIL ? MOCK_EMAIL_NOTIFICATIONS_RESPONSE : canal === CanalEnum.SMS ? MOCK_SMS_NOTIFICATIONS_RESPONSE : MOCK_PUSH_NOTIFICATIONS_RESPONSE
  );
};

export const createNotification = async (userId: number, notification: Partial<NotificationDto>)
  : Promise<Response<NotificationDto>> => {
  /*
  return NotificationsApi.post(`${NOTIFICATIONS_V1_URL}/users/${userId}`, notification)
    .then(response => response.data)
    .catch(error => catchError(error, "Error creating notification: "));
  */  
  let response: Response<NotificationDto> = MOCK_NEW_NOTIFICATION_RESPONSE;
  response.result = { ...response.result, ...notification };
  
  return Promise.resolve(response);
};

export const updateNotification = async (id: number, notification: Partial<NotificationDto>)
  : Promise<Response<NotificationDto>> => {
  return NotificationsApi.put(`${NOTIFICATIONS_V1_URL}/${id}`, notification)
    .then(response => response.data)
    .catch(error => catchError(error, "Error updating notification: "));
};

export const sendNotification = async (id: number, updatedAt: string, canal: CanalEnum)
  : Promise<Response<null>> => {  
  /*
  const params = { canal };
  const headers = { updatedAt };

  return NotificationsApi.patch(`${NOTIFICATIONS_V1_URL}/${id}/send`, { params, headers })
    .then(response => response.data)
    .catch(error => catchError(error, "Error sending notification: "));
  */
  console.log("Sending notification with id:", id, "canal:", canal, "updatedAt:", updatedAt);
  return Promise.resolve(MOCK_SEND_NOTIFICATION_RESPONSE);
};

export const deleteNotification = async (id: number, updatedAt: string, canal: CanalEnum)
  : Promise<Response<null>> => {
  /*
  const params = { canal };
  const headers = { updatedAt };
  
  return NotificationsApi.delete(`${NOTIFICATIONS_V1_URL}/${id}`, { params, headers })
    .then(response => response.data)
    .catch(error => catchError(error, "Error deleting notification: "));
  */
  console.log("Deleting notification with id:", id, "canal:", canal, "updatedAt:", updatedAt);
  return Promise.resolve(MOCK_DELETE_NOTIFICATION_RESPONSE);
};

const catchError = (error: any, message: string): void => {
  console.error(message, error?.response?.data?.message || error.message);
  throw error;
};
