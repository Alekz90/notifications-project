export interface RouteDef {
  path: string;
  toPath: string;
  title: string;  
}

export const RouteConstants = {

  // Auth  
  auth: {
    path: 'auth',
    toPath: '/auth',
    title: 'Authentication' 
  } as RouteDef,
  login: {
    path: 'login',
    toPath: '/auth/login',
    title: 'Login' 
  } as RouteDef,
  register: {
    path: 'sign-up',
    toPath: '/auth/sign-up',
    title: 'Sign Up'
  } as RouteDef,

  // Management
  management: {
    path: 'management',
    toPath: '/management',
    title: 'Notification Management'
  } as RouteDef,
  notifications: {
    path: 'notifications',
    toPath: '/management/notifications',
    title: 'Notification Management'
  } as RouteDef,
}