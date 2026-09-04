import { z } from "zod"
import { Constants } from "@utils/constants";

export const LoginSchema = z.object({
  username: z
    .string()
    .min(1, { message: "Username is required" })
    .regex(Constants.USERNAME_REGEX, { message: "Username can only contain letters, numbers, and underscores" }),
    
  password: z
    .string()
    .min(1, { message: "Password is required" })
    .regex(Constants.PASSWORD_REGEX, { message: "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character" }),
});

export type LoginFormData = z.infer<typeof LoginSchema>;

export const EMPTY_LOGIN_FORM_VALUES: LoginFormData = {
  username: "",
  password: "",
};