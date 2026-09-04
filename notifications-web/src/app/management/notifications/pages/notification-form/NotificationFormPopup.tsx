import { useContext, useState } from "react"
import { useForm } from "react-hook-form"
import { zodResolver } from "@hookform/resolvers/zod"
import { CustomFormInput } from "@components/CustomFormInput"
import { UserContext } from "@context/UserContext"
import { createNotification } from "@services/notification-management.service"
import { Constants } from "@utils/constants"
import { EMPTY_NOTIFICATION_FORM_VALUES, NotificationSchema, type NotificationFormData } from "./notification-schema"
import { CustomFormTextArea } from "@/app/components/CustomFormTextArea"
import {
  AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription,
  AlertDialogFooter, AlertDialogHeader, AlertDialogTitle, AlertDialogTrigger,
} from "@/components/ui/alert-dialog"

interface Props {
  children: React.ReactElement,
}

export function NotificationFormPopup({ children }: Props) {
  const { user } = useContext(UserContext);
  const [isOpen, setIsOpen] = useState(false);
  
  const { control, handleSubmit, reset, formState: { errors } } = useForm<NotificationFormData>({
    resolver: zodResolver(NotificationSchema),
    defaultValues: { ...EMPTY_NOTIFICATION_FORM_VALUES },
    mode: "onBlur",
  });

  const onSubmit = (data: NotificationFormData) => {
    createNotification(user?.id!, { ...data })
      .then(response => {
        if (response.id !== Constants.SUCCESS_CODE) {
          setIsOpen(true);
          return;
        }
        reset();
        setIsOpen(false);
      });
  };

  return (
    <AlertDialog open={ isOpen } onOpenChange={setIsOpen} >
      <AlertDialogTrigger render={ children }/>
        <form>
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle>New notification</AlertDialogTitle>
              <AlertDialogDescription>Fill in the fields below to create a new notification</AlertDialogDescription>
            </AlertDialogHeader>
            <div className="flex flex-col gap-6">
              {/* TOOD: Delete default values for form inputs */}
              {/* Recipient */}
              <CustomFormInput 
                control={ control }
                name='recipient'
                type='email'
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
            <AlertDialogFooter>
              <AlertDialogCancel onClick={ () => reset() }>Cancel</AlertDialogCancel>
              <AlertDialogAction onClick={ handleSubmit(onSubmit) } >Create</AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
        </form>
    </AlertDialog>
  )
}