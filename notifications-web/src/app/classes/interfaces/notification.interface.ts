import type { CanalEnum } from "@enums/canal-enum";
import type { StatusEnum } from "@/app/classes/utils/enums/status-enum";

export interface NotificationDto {
  id: number;
  title: string;
  body: string;
  recipient: string;
  status: StatusEnum;
  sentAt: string;
  updatedAt: string;
  canal: CanalEnum;
}
