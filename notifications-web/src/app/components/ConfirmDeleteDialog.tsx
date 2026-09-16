import { Trash2Icon } from "lucide-react"

import {
  AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription,
  AlertDialogFooter, AlertDialogHeader, AlertDialogMedia, AlertDialogTitle, AlertDialogTrigger
} from "@/components/ui/alert-dialog"

interface Props {
  title: string;
  description: string;
  children: React.ReactElement,
  continueFn: () => void;
}

export function ConfirmDeleteDialog({ title, description, children, continueFn }: Props) {
  return (
    <AlertDialog>
      <AlertDialogTrigger render={ children }/>
      <AlertDialogContent size="sm">
        <AlertDialogHeader>
          <AlertDialogMedia className="w-10 h-10 bg-destructive/10 text-destructive dark:bg-destructive/20 dark:text-destructive">
            <Trash2Icon />
          </AlertDialogMedia>
          <AlertDialogTitle>{ title }</AlertDialogTitle>
          <AlertDialogDescription> { description } </AlertDialogDescription>
        </AlertDialogHeader>
        <AlertDialogFooter>
          <AlertDialogCancel variant="outline">Cancel</AlertDialogCancel>
          <AlertDialogAction variant="destructive" onClick={ () => continueFn() }>Delete</AlertDialogAction>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  )
}
