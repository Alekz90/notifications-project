import { Constants } from "@utils/constants";
import { MOCK_EMAIL_NOTIFICATIONS, MOCK_PUSH_NOTIFICATIONS, MOCK_SMS_NOTIFICATIONS } from "@mocks/mock-notifications";
import type { Response } from "@interfaces/response.interface";
import type { NotificationDto } from "@interfaces/notification.interface";
import { MOCK_PUSH_MESSAGES } from "./mock-push-notifications";
import type { PushMessageDto } from "../interfaces/push-notification.interface";

export const MOCK_EMAIL_NOTIFICATIONS_RESPONSE = {
  id: Constants.SUCCESS_CODE,
  message: '',
  date: new Date().toISOString(),
  result: {
    totalElements: 0,
    totalPages: 0,
    notifications: MOCK_EMAIL_NOTIFICATIONS
  }
};

export const MOCK_SMS_NOTIFICATIONS_RESPONSE = {
  id: Constants.SUCCESS_CODE,
  message: '',
  date: new Date().toISOString(),
  result: {
    totalElements: 0,
    totalPages: 0,
    notifications: MOCK_SMS_NOTIFICATIONS
  }
};

export const MOCK_PUSH_NOTIFICATIONS_RESPONSE = {
  id: Constants.SUCCESS_CODE,
  message: '',
  date: new Date().toISOString(),
  result: {
    totalElements: 0,
    totalPages: 0,
    notifications: MOCK_PUSH_NOTIFICATIONS
  }
};

export const MOCK_DELETE_NOTIFICATION_RESPONSE = {
  id: Constants.SUCCESS_CODE,
  message: '',
  date: new Date().toISOString(),
  result: null
};

export const MOCK_SEND_NOTIFICATION_RESPONSE = {
  id: Constants.SUCCESS_CODE,
  message: '',
  date: new Date().toISOString(),
  result: null
};

const MOCK_NEW_NOTIFICATION: NotificationDto = {
  id: 100,
  title: '',
  body: '',
  from: '',
  to: '',
  status: 'PENDING',
  sentAt: new Date().toISOString(),
  createdAt: new Date().toISOString(),
  updatedAt: new Date().toISOString(),
  canal: 'EMAIL',
};

export const MOCK_NEW_NOTIFICATION_RESPONSE: Response<NotificationDto> = {
  id: Constants.SUCCESS_CODE,
  message: '',
  date: new Date().toISOString(),
  result: MOCK_NEW_NOTIFICATION,
};

export const MOCK_PUSH_MESSAGES_RESPONSE: Response<PushMessageDto[]> = {
  id: Constants.SUCCESS_CODE,
  message: '',
  date: new Date().toISOString(),
  result: MOCK_PUSH_MESSAGES,
};

export const MOCK_MARK_PUSH_MESSAGE_AS_READ_RESPONSE = {
  id: Constants.SUCCESS_CODE,
  message: '',
  date: new Date().toISOString(),
  result: null
};
