import {
  Card, CardAction, CardContent, CardDescription, CardHeader, CardTitle,
} from "@/components/ui/card"
import {
  Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow,
} from "@/components/ui/table"
import { ConfirmAlertDialog } from "@/app/components/ConfirmAlertDialog"
import { ConfirmDeleteDialog } from "@/app/components/ConfirmDeleteDialog"
import { TabsContent } from "@/components/ui/tabs"
import { Button } from "@/components/ui/button"
import { PencilLineIcon, PlusSquareIcon, SendIcon, Trash2Icon } from "lucide-react"
import type { NotificationDto } from "@interfaces/notification.interface"
import { NOTIFICATIONS_STATUS_LABELS, StatusEnum } from "@/app/classes/utils/enums/status-enum"
import { useContext } from "react"
import { CustomToolTip } from "@/app/components/CustomToolTip"
import { NotificationFormPopup } from "@/app/management/notifications/pages/notification-form/NotificationFormPopup"
import { NotificationContext } from "@/app/context/NotificationContext"

interface Props {
  tabValue: string;
  title: string;
  description: string;
  tableCaption: string;
  notifications: NotificationDto[];
  dropFn: (notification: NotificationDto) => void;
  sendFn: (notification: NotificationDto) => void;
}
const TITLE_MAX_LENGTH = 30;

const disableButton = (status: StatusEnum) => {
  const statuses: StatusEnum[] = [StatusEnum.SENDING, StatusEnum.SENT];
  return statuses.includes(status);
}

const getToolTipEdit = (status: StatusEnum) => disableButton(status) ? "Editing is not permitted" : "Edit";

const getToolTipSend = (status: StatusEnum) => disableButton(status) ? "It has already been sent" : "Send";

const getToolTipDrop = (status: StatusEnum) => disableButton(status) ? "Deletion is not permitted" : "Delete";

const getTitle = (title: string) => 
  title.length < TITLE_MAX_LENGTH ? title :  `${title.substring(0, TITLE_MAX_LENGTH)}...`;


export const NotificationTab = ({ tabValue, title, description, tableCaption, notifications, dropFn, sendFn }: Props) => {
  const { notification: notificationRefer, setNotification: setNotificationRefer } = useContext(NotificationContext);

  const continueSendFn = () => {
    if (notificationRefer){
      sendFn(notificationRefer);
      setNotificationRefer(null);
    }
  }  
  const continueDropFn = () => {
    if (notificationRefer){
      dropFn(notificationRefer);
      setNotificationRefer(null);
    }
  }

  return (
    <TabsContent value={ tabValue }>
      <Card className="shadow-lg rounded-lg">
        <CardHeader>
          <CardTitle className="text-lg font-bold">{ title }</CardTitle>
          <CardDescription> { description } </CardDescription>
          <CardAction>
            {/* Add Button */}
            <CustomToolTip content="Add new">
              <span>
                <NotificationFormPopup >
                  <Button variant="ghost" className="text-green-500 w-6 h-6 hover:text-green-600 hover:bg-green-100">
                    <PlusSquareIcon className="size-6" /> 
                  </Button>
                </NotificationFormPopup>
              </span>
            </CustomToolTip>
          </CardAction>
        </CardHeader>
        <CardContent className="text-sm text-muted-foreground">
          <Table>
            <TableCaption>{ tableCaption }</TableCaption>
            <TableHeader>
              <TableRow>
                <TableHead className="w-25">ID</TableHead>
                <TableHead>Title</TableHead>
                <TableHead>Recipient</TableHead>
                <TableHead>Status</TableHead>
                <TableHead className="w-25 text-center">Actions</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {notifications.map((notification) => (
                <TableRow key={ notification.id }>
                  <TableCell className="font-medium">{ notification.id }</TableCell>
                  <TableCell>{ getTitle(notification.title) }</TableCell>
                  <TableCell>{ notification.recipient }</TableCell>
                  <TableCell>{ NOTIFICATIONS_STATUS_LABELS[notification.status] }</TableCell>
                  <TableCell className="w-25 text-center">
                    <div className="flex justify-center space-x-2">
                      {/* Edit Button */}
                      <CustomToolTip content={ getToolTipEdit(notification.status) }>
                        <span>
                          <Button variant="ghost" className="w-6 h-6 text-blue-500 hover:text-blue-600 hover:bg-blue-100"
                            disabled={ disableButton(notification.status) }>
                            <PencilLineIcon />
                          </Button>
                        </span>
                      </CustomToolTip>
                      {/* Send Button */}
                      <CustomToolTip content={ getToolTipSend(notification.status) }>
                        <span>
                          <ConfirmAlertDialog
                            title="Send Notification"
                            description="Do you want to send this notification?"
                            continueFn={ continueSendFn } >
                              <Button variant="ghost" className="w-6 h-6 text-amber-500 hover:text-amber-600 hover:bg-amber-100"
                                disabled={ disableButton(notification.status) }
                                onClick={() => setNotificationRefer(notification)} >
                                <SendIcon />
                              </Button>
                          </ConfirmAlertDialog>
                        </span>
                      </CustomToolTip>
                      {/* Delete Button */}
                      <CustomToolTip content={ getToolTipDrop(notification.status) }>
                        <span>
                          <ConfirmDeleteDialog
                            title="Delete Notification"
                            description="Do you want to delete this notification?"
                            continueFn={ continueDropFn } >
                              <Button variant="ghost" className="w-6 h-6 text-red-500 hover:text-red-600 hover:bg-red-100"
                                disabled={ disableButton(notification.status) }
                                onClick={() => setNotificationRefer(notification)} >
                                <Trash2Icon />
                              </Button>
                          </ConfirmDeleteDialog>
                        </span>
                      </CustomToolTip>
                    </div>
                  </TableCell>
                </TableRow>
              ))}
              {notifications.length === 0 && (
                <TableRow>
                  <TableCell colSpan={5} className="text-center">
                    No notifications found.
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </CardContent>
      </Card>
    </TabsContent>
  )
}
