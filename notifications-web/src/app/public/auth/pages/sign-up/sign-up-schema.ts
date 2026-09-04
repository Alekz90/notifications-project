
import { z } from "zod"
import { Constants } from "@utils/constants";

export const SignUpSchema = z.object({
  username: z
    .string()
    .min(1, { message: "Username is required" })
    .regex(Constants.USERNAME_REGEX, { message: "Username can only contain letters, numbers, and underscores" }),

  email: z
    .string()
    .min(1, { message: "Email is required" })
    .regex(Constants.EMAIL_REGEX, { message: "Invalid email address" }),
    
  password: z
    .string()
    .min(1, { message: "Password is required" })
    .regex(Constants.PASSWORD_REGEX, { message: "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character" }),

  confirmPassword: z
    .string()
    .min(1, { message: "Confirm Password is required" }),

  phone: z
    .string()
    .regex(Constants.PHONE_REGEX, { message: "Phone number must be exactly 10 digits" }),

  acceptTerms: z
    .boolean()
    .refine((val) => val === true, { message: "You must accept the terms and conditions" })
}).refine((data) => data.password === data.confirmPassword, {
  message: "Passwords do not match",
  path: ["confirmPassword"],
});

export type SignUpFormData = z.infer<typeof SignUpSchema>;

export const EMPTY_SIGN_UP_FORM_VALUES: SignUpFormData = {
  username: "",
  email: "",
  password: "",
  confirmPassword: "",
  phone: "",
  acceptTerms: false,
};
