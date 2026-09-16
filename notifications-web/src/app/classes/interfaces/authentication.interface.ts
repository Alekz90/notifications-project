import type { UserResponse } from "./user-interface";

export interface AuthenticationResponse {
  token: string;
  user: UserResponse;
}