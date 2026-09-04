import { useForm } from "react-hook-form"
import { zodResolver } from "@hookform/resolvers/zod"
import { Button } from "@/components/ui/button"
import { 
  Card, CardAction, CardContent, CardDescription, CardFooter, CardHeader, CardTitle 
} from "@/components/ui/card"
import { Link } from "react-router"
import { RouteConstants } from "@utils/route-constants"
import { EMPTY_SIGN_UP_FORM_VALUES, SignUpSchema, type SignUpFormData } from "./sign-up-schema"
import { CustomFormInput } from "@components/CustomFormInput"
import { registerUser } from "@services/security-users.service";
import { CustomFormCheckbox } from "@components/CustomFormCheckbox";

export const SignUpPage = () => {
  const { control, handleSubmit, formState: { errors } } = useForm<SignUpFormData>({
    resolver: zodResolver(SignUpSchema),
    defaultValues: EMPTY_SIGN_UP_FORM_VALUES,
    mode: "onBlur",
  });

  const onSubmit = (data: SignUpFormData) => {
    registerUser({ ...data });
  };

  return (
    <Card className="w-full max-w-lg">
      <form onSubmit={ handleSubmit(onSubmit) }>
        <CardHeader>
          <CardTitle>Sign Up for an account</CardTitle>
          <CardDescription>
            Enter your email below to create a new account
          </CardDescription>
          <CardAction>
            <Link to={ RouteConstants.login.toPath }>Login</Link>
          </CardAction>
        </CardHeader>
        <CardContent className="flex flex-col gap-3 mt-5 w-full">
              {/* Email */}
              <CustomFormInput control={ control } name='email' type='email' label='Email' placeholder='Your email' error={ errors.email } />
              {/* Phone */}
              <CustomFormInput control={ control } name='phone' type='tel' label='Phone' placeholder='(123) 456-7890' error={ errors.phone } />
              {/* Username */}
              <CustomFormInput control={ control } name='username' type='text' label='Username' placeholder='Your username' error={ errors.username } />
              {/* Password */}
              <CustomFormInput control={ control } name='password' type='password' label='Password' placeholder='********' error={ errors.password } />
              {/* Confirm Password */}
              <CustomFormInput control={ control } name='confirmPassword' type='password' label='Confirm Password' placeholder='********' error={ errors.confirmPassword } />
        </CardContent>
        <CardFooter>
          <div className="flex flex-col gap-3 mt-5 w-full">
            {/* Accept Terms */}
            <CustomFormCheckbox control={ control } name='acceptTerms' label='Accept terms and conditions' error={ errors.acceptTerms } />
            {/* Button */}
            <Button type="submit" className="w-full">Sign Up</Button>
          </div>
        </CardFooter>
      </form>
    </Card>
  )
}
