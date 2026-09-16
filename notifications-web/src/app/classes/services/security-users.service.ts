import type { AuthenticationResponse } from "@interfaces/authentication.interface";
import type { Response } from "@interfaces/response.interface";
import type { UserCreate, UserLogin } from "@interfaces/user-interface";
import axios from "axios";

const SecurityUsersApi = axios.create({
  baseURL: import.meta.env.VITE_SECURITY_USERS_API_URL,
});

SecurityUsersApi.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = token;
  }
  return config;
});

const AUTHENTICATIONS_V1_URL = "/authentications/v1";

export const registerUser = async (userData: UserCreate): Promise<Response<AuthenticationResponse>> => {
  return SecurityUsersApi.post(`${AUTHENTICATIONS_V1_URL}/register`, userData)
    .then(response => response.data)
    .catch(error => {
      console.error("Error registering user:", error?.response?.data?.message || error.message);
      throw error;
    });
};

export const loginUser = async (loginData: UserLogin): Promise<Response<AuthenticationResponse>> => {
  return SecurityUsersApi.post(`${AUTHENTICATIONS_V1_URL}/login`, loginData)
    .then(response => response.data)
    .catch(error => {
      console.error("Error logging in user:", error?.response?.data?.message || error.message);
      throw error;
    });
};