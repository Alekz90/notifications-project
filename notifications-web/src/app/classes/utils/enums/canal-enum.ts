export const CanalEnum = {
  EMAIL: "EMAIL",
  SMS: "SMS",
  PUSH: "PUSH",
  NONE: "NONE",
} as const;

export type CanalEnum = (typeof CanalEnum)[keyof typeof CanalEnum];