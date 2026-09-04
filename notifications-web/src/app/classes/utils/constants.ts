
export class Constants {

  static readonly NEW_ELEMENT: string = 'new';
  static readonly QUERY_PARAM_NAME: string = 'query';
  static readonly SUCCESS_CODE: string = '0';
  
  static readonly PAGE_DEFAULT: number = 1;
  static readonly SIZE_DEFAULT: number = 10;

  static readonly LOGIN_PATH: string = '/auth/login';
  static readonly REGISTER_PATH: string = '/auth/register';
  static readonly ADMIN_PATH: string = '/admin';
  static readonly HOME_PATH: string = '/';

  static readonly USERNAME_REGEX: RegExp = /^[a-zA-Z0-9_]+$/;
  static readonly PASSWORD_REGEX: RegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$¡!%*¿?&\-+=.#])[A-Za-z\d@$¡!%*¿?&\-+=.# ]{8,50}$/;
  static readonly EMAIL_REGEX: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  static readonly PHONE_REGEX: RegExp = /^\d{10}$/;

}
