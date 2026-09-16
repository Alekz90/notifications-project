export const CanalEnum = {
  EMAIL: "EMAIL",
  SMS: "SMS",
  PUSH: "PUSH",
  NONE: "NONE",
} as const;

export type CanalEnum = (typeof CanalEnum)[keyof typeof CanalEnum];

/*
const canalRecord = Record<CanalEnum, string> = {
  EMAIL: "EMAIL",
  SMS: "SMS",
  PUSH: "PUSH",
  NONE: "NONE",
}
*/

export function getCanalEnum(canal: string) {
  switch (canal) {
    case CanalEnum.EMAIL:
      return CanalEnum.EMAIL;
    case CanalEnum.SMS:
      return CanalEnum.SMS;
    case CanalEnum.PUSH:
      return CanalEnum.PUSH;
    default:
      return CanalEnum.NONE;
  }
} 
