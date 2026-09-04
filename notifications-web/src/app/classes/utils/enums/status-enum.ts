

export const StatusEnum = {
  PENDING:  "PENDING",
  SENDING:  "SENDING",
  SENT:     "SENT",
  FAILED:   "FAILED",
} as const;

export type StatusEnum = (typeof StatusEnum)[keyof typeof StatusEnum];

export const NOTIFICATIONS_STATUS_LABELS: Record<StatusEnum, string> = {
  PENDING: "Pending",
  SENDING: "Sending",
  SENT: "Sent",
  FAILED: "Failed",
};

