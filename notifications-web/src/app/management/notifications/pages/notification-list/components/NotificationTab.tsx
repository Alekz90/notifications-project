import { Button } from "@/components/ui/button"
import {
  Card, CardAction, CardContent, CardDescription, CardHeader, CardTitle,
} from "@/components/ui/card"
import {
  Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow,
} from "@/components/ui/table"
import { TabsContent } from "@/components/ui/tabs"
import { ConfirmAlertDialog } from "@components/ConfirmAlertDialog"
import { ConfirmDeleteDialog } from "@components/ConfirmDeleteDialog"
import { CustomToolTip } from "@components/CustomToolTip"
import { UseNotificationContext } from "@context/NotificationContext"
import type { NotificationDto } from "@interfaces/notification.interface"
import { UseCustomSendAndDropNotification } from "@management/hooks/CustomNotificationHook"
import { NotificationFormPopup } from "@notifications/pages/notification-list/components/NotificationFormPopup"
import { Constants } from "@utils/constants"
import type { CanalEnum } from "@utils/enums/canal-enum"
import { NOTIFICATIONS_STATUS_LABELS, StatusEnum } from "@utils/enums/status-enum"
import { EyeIcon, PencilLineIcon, PlusSquareIcon, SendIcon, Trash2Icon } from "lucide-react"
import { useContext } from "react"
import { NotificationDetailPopup } from "./NotificationDetailPopup"

interface Props {
  tabValue: CanalEnum;
  title: string;
  description: string;
  tableCaption: string;
  notifications: NotificationDto[];
  pagination: React.ReactNode;
}

export const NotificationTab = ({ tabValue, title, description, tableCaption, notifications, pagination }: Props) => {
  const { setNotification: setNotificationContext } = useContext(UseNotificationContext);
  const { sendFn, dropFn } = UseCustomSendAndDropNotification(tabValue);

  return (
    <TabsContent value={ tabValue }>
      <Card className="shadow-lg rounded-lg">
        <CardHeader>
          <CardTitle className="text-lg font-bold">{ title }</CardTitle>
          <CardDescription> { description } </CardDescription>
          <CardAction>
            {/* Add Button */}
            {/*
            <CustomToolTip content="Add new">
              <span>
                <NotificationFormPopup canal={ tabValue }>
                  <Button variant="ghost" className="text-green-500 w-6 h-6 hover:text-green-600 hover:bg-green-100">
                    <PlusSquareIcon className="size-6" /> 
                  </Button>
                </NotificationFormPopup>
              </span>
            </CustomToolTip>
            */}
          </CardAction>
        </CardHeader>
        <CardContent className="text-sm text-muted-foreground">
          <Table>
            {/* Table header */}
            <TableHeader>
              <TableRow>
                <TableHead className="w-25">ID</TableHead>
                <TableHead>Title</TableHead>
                <TableHead>Recipient</TableHead>
                <TableHead>Status</TableHead>
                <TableHead className="w-25 text-center">Actions</TableHead>
              </TableRow>
            </TableHeader>
            {/* Table body */}
            <TableBody>
              {notifications.map((notification) => (
                <TableRow key={ notification.id }>
                  <TableCell className="font-medium">{ notification.id }</TableCell>
                  <TableCell>{ getTitle(notification.title) }</TableCell>
                  <TableCell>{ notification.to }</TableCell>
                  <TableCell>{ NOTIFICATIONS_STATUS_LABELS[notification.status] }</TableCell>
                  <TableCell className="w-25 text-center">
                    <div className="flex justify-center space-x-2">
                      {/* View Details */}
                      <CustomToolTip content="View Details">
                        <span>
                          <NotificationDetailPopup canal={ tabValue }>
                            <Button variant="ghost" className="w-6 h-6 text-blue-500 hover:text-blue-600 hover:bg-blue-100"
                              onClick={() => setNotificationContext(notification)} >
                              <EyeIcon />
                            </Button>
                          </NotificationDetailPopup>
                        </span>
                      </CustomToolTip>
                      {/* Edit Button */}
                      
                      <CustomToolTip content={ getToolTipEdit(notification.status) }>
                        <span>
                          <NotificationFormPopup canal={ tabValue }>
                            <Button variant="ghost" className="w-6 h-6 text-cyan-500 hover:text-cyan-600 hover:bg-cyan-100"
                              disabled={ disableButton(notification.status)}
                              onClick={() => setNotificationContext(notification)} >
                              <PencilLineIcon />
                            </Button>
                          </NotificationFormPopup>
                        </span>
                      </CustomToolTip>
                      
                      {/* Send Button */}
                      <CustomToolTip content={ getToolTipSend(notification.status) }>
                        <span>
                          <ConfirmAlertDialog
                            title="Send Notification"
                            description="Do you want to send this notification?"
                            continueFn={ sendFn } >
                              <Button variant="ghost" className="w-6 h-6 text-amber-500 hover:text-amber-600 hover:bg-amber-100"
                                disabled={ disableButton(notification.status) }
                                onClick={() => setNotificationContext(notification)} >
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
                            continueFn={ dropFn } >
                              <Button variant="ghost" className="w-6 h-6 text-red-500 hover:text-red-600 hover:bg-red-100"
                                disabled={ disableButton(notification.status) }
                                onClick={() => setNotificationContext(notification)} >
                                <Trash2Icon />
                              </Button>
                          </ConfirmDeleteDialog>
                        </span>
                      </CustomToolTip>
                    </div>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
            {/* Show table caption when there are no notifications */}
            {notifications.length === 0 && (
              <TableCaption className="text-xl pt-5">{ tableCaption }</TableCaption>
            )}
          </Table>
          {/* Pagination */}
          {notifications.length > 0 && (
            pagination
          )}
        </CardContent>
      </Card>
    </TabsContent>
  )
}

const disableButton = (status: StatusEnum) => {
  const statuses: StatusEnum[] = [StatusEnum.SENDING, StatusEnum.SENT] as StatusEnum[];
  return statuses.includes(status);
}

const getToolTipEdit = (status: StatusEnum) => disableButton(status) ? "Editing is not permitted" : "Edit";

const getToolTipSend = (status: StatusEnum) => disableButton(status) ? "It has already been sent" : "Send";

const getToolTipDrop = (status: StatusEnum) => disableButton(status) ? "Deletion is not permitted" : "Delete";

const getTitle = (title: string) => 
  title.length < Constants.TITLE_MAX_LENGTH ? title :  `${title.substring(0, Constants.TITLE_MAX_LENGTH)}...`;
