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

  from: z
    .string()
    .min(1, { message: "Sender is required" })
    .max(100, { message: "Sender must be at most 100 characters" }),

  to: z
    .string()
    .min(1, { message: "Recipient is required" })
    .max(100, { message: "Recipient must be at most 100 characters" }),
  
  isNew: z.boolean().optional(),
});

export type NotificationFormData = z.infer<typeof NotificationSchema>;

export const EMPTY_NOTIFICATION_FORM_VALUES: NotificationFormData = {
  title: "",
  body: "",
  from: "",
  to: "",
  isNew: true,
};
