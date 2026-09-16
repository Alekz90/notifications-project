
export class Constants {

  static readonly QUERY_PARAM_NAME: string = 'query';
  static readonly SUCCESS_CODE: string = '0';
  static readonly EMPTY_DATE: string = '1900-01-01T00:00:00Z';

  static readonly TITLE_MAX_LENGTH: number = 30;
  
  // Pagination defaults
  static readonly PAGE_DEFAULT: string = "1";
  static readonly SIZE_DEFAULT: string = "10";
  static readonly PAGE_SIZE_OPTIONS = [{key: "first", value: "10"}, {key: "second", value: "25"}, {key: "third", value: "50"}, {key: "four", value: "100"}];
  static readonly PAGE_PARAM_NAME: string = 'page';
  static readonly SIZE_PARAM_NAME: string = 'size';
  static readonly CANAL_PARAM_NAME: string = 'canal';
  static readonly LIMIT_PAGINATION_PAGES: number = 9;
  
  static readonly LOGIN_PATH: string = '/auth/login';
  static readonly REGISTER_PATH: string = '/auth/register';
  static readonly ADMIN_PATH: string = '/admin';
  static readonly HOME_PATH: string = '/';

  static readonly USERNAME_REGEX: RegExp = /^[a-zA-Z0-9_]+$/;
  static readonly PASSWORD_REGEX: RegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$¡!%*¿?&\-+=.#])[A-Za-z\d@$¡!%*¿?&\-+=.# ]{8,50}$/;
  static readonly EMAIL_REGEX: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  static readonly PHONE_REGEX: RegExp = /^\d{10}$/;

}
