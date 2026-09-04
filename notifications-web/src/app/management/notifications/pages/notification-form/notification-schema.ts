import { z } from "zod"

export const NotificationSchema = z.object({
  title: z
    .string()
    .min(1, { message: "Title is required" })
    .max(100, { message: "Title must be at most 100 characters" }),

  body: z
    .string()
    .min(1, { message: "Body is required" })
    .max(500, { message: "Body must be at most 500 characters" }),

  recipient: z
    .string()
    .min(1, { message: "Recipient is required" })
    .max(100, { message: "Recipient must be at most 100 characters" }),
});

export type NotificationFormData = z.infer<typeof NotificationSchema>;

export const EMPTY_NOTIFICATION_FORM_VALUES: NotificationFormData = {
  title: "user@example.com",
  body: "Meeting Reminder",
  recipient: "This email is for the upcoming meeting.",
};
