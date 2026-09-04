import { useContext } from "react"
import { useNavigate } from "react-router"
import { useForm } from "react-hook-form"
import { zodResolver } from "@hookform/resolvers/zod"
import { Button } from "@/components/ui/button"
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { CustomFormInput } from "@components/CustomFormInput"
import { UserContext } from "@context/UserContext"
import { createNotification } from "@services/notification-management.service"
import { Constants } from "@utils/constants"
import { RouteConstants } from "@utils/route-constants"
import { EMPTY_NOTIFICATION_FORM_VALUES, NotificationSchema, type NotificationFormData } from "./notification-schema"
import { CustomFormTextArea } from "@/app/components/CustomFormTextArea"

export function NotificationFormPage() {

  const { user } = useContext(UserContext);
  const navigate = useNavigate();

  const { control, handleSubmit, reset, formState: { errors } } = useForm<NotificationFormData>({
    resolver: zodResolver(NotificationSchema),
    defaultValues: { ...EMPTY_NOTIFICATION_FORM_VALUES },
    mode: "onBlur",
  });

  const onSubmit = (data: NotificationFormData) => {    
    createNotification(user?.id!, { ...data })
      .then(response => {
        if (response.id === Constants.SUCCESS_CODE) {
          reset();
          navigate(RouteConstants.management.toPath);
        }
      });
  };

  return (
    <Card className="w-full max-w-lg m-auto">
      <form onSubmit={ handleSubmit(onSubmit) }>
        <CardHeader className="text-center mb-5">
          <CardTitle>New notification</CardTitle>
          <CardDescription>
            Fill in the fields below to create a new notification
          </CardDescription>
        </CardHeader>
        <CardContent>
          <div className="flex flex-col gap-6">            
            {/* Recipient */}
            <CustomFormInput 
              control={ control }
              name='recipient'
              type='text'
              label='Recipient'
              placeholder='Ex: user@example.com'
              error={ errors.recipient }
            />
            {/* Title */}
            <CustomFormInput
              control={ control }
              name='title'
              type='text'
              label='Title'
              placeholder='Ex: Meeting Reminder'
              error={ errors.title }
            />
            {/* Body */}
            <CustomFormTextArea
              control={ control }
              name='body'
              label='Body'
              placeholder='Ex: This email is for the upcoming meeting.'
              error={ errors.body }
            />
          </div>
        </CardContent>
        <CardFooter className="flex-col gap-2 mt-5">
          <Button type="submit" className="w-full">Create notification</Button>
        </CardFooter>
      </form>
    </Card>
  )
}
