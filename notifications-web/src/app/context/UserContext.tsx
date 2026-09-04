import { createContext, useState, type PropsWithChildren } from "react";
import type { AuthenticatedStatus, User } from "@interfaces/user-interface";
import type { AuthenticationResponse } from "@interfaces/authentication.interface";
import { MOCK_USER } from "../classes/mocks/mock-users";

interface UserContextProps {
  //state
  user: User | null;
  token: string;
  authenticatedStatus: AuthenticatedStatus;

  // Methods
  login: (authenticationResponse: AuthenticationResponse) => void;
  logout: () => void;
}

export const UserContext = createContext({} as UserContextProps);

export const UserContextProvider = ({ children }: PropsWithChildren) => {
  const [user, setUser] = useState<User | null>(MOCK_USER); //TODO: Replace with actual user state management by null
  const [authenticatedStatus, setAuthenticatedStatus] = useState<AuthenticatedStatus>('checking');
  const [token, setToken] = useState<string>("");

  const handleLogin = (authenticationResponse: AuthenticationResponse) => {
    setUser(authenticationResponse.user);
    setToken(authenticationResponse.token);
    setAuthenticatedStatus('authenticated');
  };

  const handleLogout = () => {
    setUser(null);
    setToken("");
    setAuthenticatedStatus('unauthenticated');
  }; 

  return (
    <UserContext value={{ user, token, authenticatedStatus, login: handleLogin, logout: handleLogout }}>
      { children }
    </UserContext>
  )
}
