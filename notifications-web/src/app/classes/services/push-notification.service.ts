import type { Response } from "@interfaces/response.interface";
import axios from "axios";
import type { PushMessageDto } from "@interfaces/push-notification.interface";
import { MOCK_MARK_PUSH_MESSAGE_AS_READ_RESPONSE, MOCK_PUSH_MESSAGES_RESPONSE } from "@mocks/mock-response";

const PushApi = axios.create({
  baseURL: import.meta.env.VITE_PUSH_API_URL,
});

PushApi.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = token;
  }
  return config;
});

const PUSH_V1_URL = "/v1";

export const getPushMessages = async (userId: number) : Promise<Response<PushMessageDto[]>> => {
  return PushApi.get(`${PUSH_V1_URL}/users/${userId}`)
    .then(response => response.data)
    .catch(error => catchError(error, "Error fetching push messages: "));
};

export const markPushMessageAsRead = async (id: number) : Promise<Response<null>> => {
  return PushApi.patch(`${PUSH_V1_URL}/${id}/read`)
    .then(response => response.data)
    .catch(error => catchError(error, "Error marking push message as read: "));
};

const catchError = (error: any, message: string): void => {
  console.error(message, error?.response?.data?.message || error.message);
  throw error;
};


// export const getPushMessages = async (userId: number) : Promise<Response<PushMessageDto[]>> => {
//   return Promise.resolve(MOCK_PUSH_MESSAGES_RESPONSE);
// };

// export const markPushMessageAsRead = async (id: number) : Promise<Response<null>> => {
//   return Promise.resolve(MOCK_MARK_PUSH_MESSAGE_AS_READ_RESPONSE);
// };
