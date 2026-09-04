export interface UserCreate {
  username:    string;
  email:       string;
  phone:       string;
  password:    string;
  acceptTerms: boolean;
}

export interface UserLogin {
  username:    string;
  password:    string;
}

export interface User {
  id:          number;
  username:    string;
  email:       string;
  active:      boolean;
  role:        string;
  blocked:     boolean;
  verified:    boolean;
  createdAt:   string;   // ISO 8601 date string
}

export type AuthenticatedStatus = 'checking' | 'authenticated' | 'unauthenticated';

export interface UserResponse extends User {}