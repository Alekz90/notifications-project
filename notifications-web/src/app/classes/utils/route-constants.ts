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
  emailTab: {
    path: 'emails',
    toPath: '/management/emails',
    title: 'Emails'
  } as RouteDef,
  smsTab: {
    path: 'sms',
    toPath: '/management/sms',
    title: 'SMS'
  } as RouteDef,
  pushTab: {
    path: 'notifications',
    toPath: '/management/notifications',
    title: 'Notifications'
  } as RouteDef,
  
  // Forms
  emailForm: {
    path: 'emails/form/:id',
    toPath: '/management/emails/form',
    title: 'Email Form'
  } as RouteDef,
  smsForm: {
    path: 'sms/form/:id',
    toPath: '/management/sms/form',
    title: 'SMS Form'
  } as RouteDef,
  pushForm: {
    path: 'notifications/form/:id',
    toPath: '/management/notifications/form',
    title: 'Notification Form'
  } as RouteDef,
}