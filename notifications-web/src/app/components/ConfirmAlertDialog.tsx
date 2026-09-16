import { TriangleAlertIcon } from "lucide-react"

import {
  AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription,
  AlertDialogFooter, AlertDialogHeader, AlertDialogMedia, AlertDialogTitle, AlertDialogTrigger,
} from "@/components/ui/alert-dialog"

interface Props {
  title: string,
  description: string,
  cancelText?: string,
  continueText?: string,
  children: React.ReactElement,
  continueFn: () => void,
}

export function ConfirmAlertDialog({ title, description, cancelText: cancelText = 'Cancel', continueText: continueText = 'Continue', continueFn , children }: Props) {
  return (
    <AlertDialog>
      <AlertDialogTrigger render={ children }/>
      <AlertDialogContent size="sm">
        <AlertDialogHeader>
          <AlertDialogMedia className="w-10 h-10 bg-transparent" >
            <TriangleAlertIcon />
          </AlertDialogMedia>
          <AlertDialogTitle>{ title }</AlertDialogTitle>
          <AlertDialogDescription>{ description }</AlertDialogDescription>
        </AlertDialogHeader>
        <AlertDialogFooter>
          <AlertDialogCancel>{ cancelText }</AlertDialogCancel>
          <AlertDialogAction onClick={ () => continueFn() }>{ continueText }</AlertDialogAction>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  )
}
