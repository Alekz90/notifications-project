import { Constants } from "@utils/constants";
import { UseCustomGetNotificationById } from "@management/hooks/CustomNotificationHook";
import {
  AlertDialog, AlertDialogAction, AlertDialogContent, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle, AlertDialogTrigger,
} from "@/components/ui/alert-dialog";
import { Table, TableBody, TableCell, TableRow } from "@/components/ui/table";
import { CanalEnum } from "@utils/enums/canal-enum";
import { NOTIFICATIONS_STATUS_LABELS, StatusEnum } from "@utils/enums/status-enum";

interface Props {
  canal: CanalEnum;
  children: React.ReactElement
}

const getDetailTitle = (canal: CanalEnum) => {
  return canal === CanalEnum.EMAIL ? "Email Details" :
         canal === CanalEnum.SMS ? "SMS Details" :
         canal === CanalEnum.PUSH ? "Push Notification Details" :
         "Notification Details";
}

const getDateFormatted = (dateString: string | undefined) => {
  if (!dateString || dateString === Constants.EMPTY_DATE) return "N/A";
  const date = new Date(dateString);
  return date.toLocaleString("MX", { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' });
}

export function NotificationDetailPopup({ canal, children }: Props) {
  //const { notification } = UseCustomGetNotificationById(canal);

  return (
    <AlertDialog>
      <AlertDialogTrigger render={ children }/>
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle className="ml-2 m-auto text-3xl">{ getDetailTitle(canal) }</AlertDialogTitle>
            </AlertDialogHeader>
            <Table>
              <TableBody>
                {notification && (
                  <>
                    <TableRow>
                      <TableCell><strong>Recipient:</strong></TableCell>
                      <TableCell>{ notification.to }</TableCell>
                    </TableRow>
                    <TableRow>
                      <TableCell><strong>Title</strong></TableCell>
                      <TableCell><p className="text-wrap">{ notification.title }</p></TableCell>
                    </TableRow>
                    <TableRow>
                      <TableCell><strong>Body</strong></TableCell>
                      <TableCell><p className="text-wrap">{ notification.body }</p></TableCell>
                    </TableRow>
                    <TableRow>
                      <TableCell><strong>Created At</strong></TableCell>
                      <TableCell>{ getDateFormatted(notification.createdAt) }</TableCell>
                    </TableRow>
                    <TableRow>
                      <TableCell><strong>Status</strong></TableCell>
                      <TableCell>{ NOTIFICATIONS_STATUS_LABELS[notification.status] }</TableCell>
                    </TableRow>
                    {notification.status === StatusEnum.SENT && (
                      <TableRow>
                        <TableCell><strong>Sent At</strong></TableCell>
                        <TableCell>{ getDateFormatted(notification.sentAt) }</TableCell>
                      </TableRow>
                    )}
                  </>
                )}
                {!notification && (
                  <TableRow>
                    <TableCell colSpan={2} className="text-center text-2xl">No notification info to show.</TableCell>
                  </TableRow>
                )}
              </TableBody>
            </Table>
            <AlertDialogFooter>
              <AlertDialogAction>Close</AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
    </AlertDialog>
  )
}
