import { CustomFormTextArea } from "@/app/components/CustomFormTextArea"
import {
  AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription,
  AlertDialogFooter, AlertDialogHeader, AlertDialogTitle, AlertDialogTrigger,
} from "@/components/ui/alert-dialog"
import { CustomFormInput } from "@components/CustomFormInput"
import { zodResolver } from "@hookform/resolvers/zod"
import { CanalEnum } from "@utils/enums/canal-enum"
import { useEffect } from "react"
import { useForm } from "react-hook-form"
import { UseCustomCreateUpdateNotification } from "@management/hooks/CustomNotificationHook"
import { EMPTY_NOTIFICATION_FORM_VALUES, NotificationSchema, type NotificationFormData } from "./notification-schema"

interface Props {
  canal: CanalEnum;
  isNew?: boolean;
  children: React.ReactElement
}

export function NotificationFormPopup({ canal, isNew = false, children }: Props) {
  const { notification, isOpen, setIsOpen, onSubmit } = UseCustomCreateUpdateNotification(canal);

  const { control, handleSubmit, reset, formState: { errors } } = useForm<NotificationFormData>({
    resolver: zodResolver(NotificationSchema),
    defaultValues: { ...EMPTY_NOTIFICATION_FORM_VALUES },
    mode: "onBlur",
  });

  useEffect(() => {
    if (!isOpen) {
      reset({ ...EMPTY_NOTIFICATION_FORM_VALUES });
    } else if (!isNew) {
      reset({ ...notification, isNew: false });
    }
  }, [ isOpen, reset ]);

  return (
    <AlertDialog open={ isOpen } onOpenChange={setIsOpen} >
      <AlertDialogTrigger render={ children }/>
        <form >
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle>{ getTitle(canal, isNew) }</AlertDialogTitle>
              <AlertDialogDescription>{ getDescription(canal, isNew) }</AlertDialogDescription>
            </AlertDialogHeader>
            <div className="flex flex-col gap-6">
              {/* Recipient */}
              <CustomFormInput
                control={ control }
                name='from'
                type='email'
                label='Sender'
                placeholder='Ex: user@example.com'
                error={ errors.from }
                disabled
              />
              {/* Recipient */}
              <CustomFormInput 
                control={ control }
                name='to'
                type='email'
                label='Recipient'
                placeholder='Ex: user@example.com'
                error={ errors.to }
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
              <AlertDialogCancel>Cancel</AlertDialogCancel>
              <AlertDialogAction onClick={ handleSubmit(onSubmit) } >{ getButtonLabel(canal, isNew) }  </AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
        </form>
    </AlertDialog>
  )
}

const getTitle = (canal: CanalEnum, isNew: boolean): string => {
  switch (canal) {
    case CanalEnum.EMAIL:
      return isNew ? "Create a new E-Mail": "Update the E-Mail";
    case CanalEnum.SMS:
      return isNew ? "Create a new SMS Message": "Update the SMS Message";
    case CanalEnum.PUSH:
      return isNew ? "Create a new notification": "Update the notification";
    default:
      return "";
  }
}

function getDescription(canal: CanalEnum, isNew: boolean): string {
  switch (canal) {
    case CanalEnum.EMAIL: 
      return isNew ? "Fill in the details to create a new E-Mail." : "Update the details of the E-Mail.";
    case CanalEnum.SMS:
      return isNew ? "Fill in the details to create a new SMS Message." : "Update the details of the SMS Message.";
    case CanalEnum.PUSH:
      return isNew ? "Fill in the details to create a new notification." : "Update the details of the notification.";
    default:
      return "";
  }
}

function getButtonLabel(canal: CanalEnum, isNew: boolean): string {
  switch (canal) {
    case CanalEnum.EMAIL:
      return isNew ? "Create E-Mail" : "Update E-Mail";
    case CanalEnum.SMS:
      return isNew ? "Create SMS Message" : "Update SMS Message";
    case CanalEnum.PUSH:
      return isNew ? "Create notification" : "Update notification";
    default:
      return "";
  }
}