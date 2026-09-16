export interface Response<T> {
  id: string;
  message: string;
  date: string;   // ISO 8601 date string
  result: T;      // Generic type
}