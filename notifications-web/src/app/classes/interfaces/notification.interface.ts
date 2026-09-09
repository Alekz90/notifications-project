import type { CanalEnum } from "@enums/canal-enum";
import type { StatusEnum } from "@utils/enums/status-enum";

export interface NotificationDto {
  id: number;
  title: string;
  body: string;
  from: string;
  to: string;
  status: StatusEnum;
  sentAt: string;
  createdAt: string;
  updatedAt: string;
  canal: CanalEnum;
}

export interface NotificationListResponse {
  notifications: NotificationDto[];
  totalElements: number;
  totalPages: number;
}
