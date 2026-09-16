import { Button } from "@/components/ui/button"
import { RouteConstants } from "@utils/route-constants"
import {
  Card,
  CardAction,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { Link, useNavigate } from "react-router"
import { CustomFormInput } from "@components/CustomFormInput"
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import { EMPTY_LOGIN_FORM_VALUES, LoginSchema, type LoginFormData } from "./login-schema"
import { loginUser } from "@services/security-users.service"
import { useContext } from "react"
import { UserContext } from "@context/UserContext"
import { Constants } from "@utils/constants"

export function LoginPage() {

  const { login } = useContext(UserContext);
  const navigate = useNavigate();

  const { control, handleSubmit, formState: { errors } } = useForm<LoginFormData>({
      resolver: zodResolver(LoginSchema),
      defaultValues: { ...EMPTY_LOGIN_FORM_VALUES },
      mode: "onBlur",
    });

  const onSubmit = (data: LoginFormData) => {
    console.log(data);
    loginUser({ ...data })
      .then(response => {
        if (response.id === Constants.SUCCESS_CODE) {
          login(response.result);
          navigate(RouteConstants.management.toPath);
        }
    });

  }; 

  return (
    <Card className="w-full max-w-sm">
      <form onSubmit={handleSubmit(onSubmit)}>
        <CardHeader>
          <CardTitle>Login to your account</CardTitle>
          <CardDescription>
            Enter your email below to login to your account
          </CardDescription>
          <CardAction>
            <Link to={ RouteConstants.register.toPath }>Sign Up</Link>
          </CardAction>
        </CardHeader>
        <CardContent>
          <div className="flex flex-col gap-6">
            {/* Username */}
            <CustomFormInput control={ control } name='username' type='text' label='Username' placeholder='Your username' error={ errors.username } />
            {/* Password */}
            <CustomFormInput control={ control } name='password' type='password' label='Password' placeholder='********' error={ errors.password } />
          </div>
        </CardContent>
        <CardFooter className="flex-col gap-2 mt-5">
          <Button type="submit" className="w-full">Login</Button>
        </CardFooter>
      </form>
    </Card>
  )
}
