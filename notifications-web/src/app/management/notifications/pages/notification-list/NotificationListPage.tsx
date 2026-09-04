import { NotificationTab } from "@/app/management/notifications/pages/notification-list/NotificationTab"
import { Tabs, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { useContext, useState } from "react"
import { UserContext } from "@context/UserContext"
import { CanalEnum } from "@enums/canal-enum"
import { UseCustomGetNotifications } from "@notifications/hooks/CustomNotificationHook"
import { deleteNotification, sendNotification } from "@services/notification-management.service"
import type { NotificationDto } from "@interfaces/notification.interface"
import { Constants } from "@utils/constants"
import { StatusEnum } from "@enums/status-enum"

export const NotificationListPage = () => {
  const { user } = useContext(UserContext)
  const [tabValue, setTabValue] = useState<CanalEnum>(CanalEnum.EMAIL)

  const { notifications, setNotifications } = UseCustomGetNotifications(user?.id!, tabValue)

  const dropFn = (notification: NotificationDto) => {
    deleteNotification(notification.id, notification.updatedAt, tabValue)
      .then((response) => {
        if (response.id === Constants.SUCCESS_CODE) {
          setNotifications(notifications.filter(n => n.id !== notification.id));
        }
      });
  }

  const sendFn = (notification: NotificationDto) => {
    sendNotification(notification.id, notification.updatedAt, tabValue)
      .then((response) => {
        if (response.id === Constants.SUCCESS_CODE) {
          notification.status = StatusEnum.SENDING;
          setNotifications([ ...notifications ]);
        }
      });
  }

  return (
      <Tabs defaultValue= { CanalEnum.EMAIL }  onValueChange={ (value) => setTabValue(value) } >
        <TabsList variant="line" className="m-auto p-2 ">
          <TabsTrigger value={ CanalEnum.EMAIL }>Emails</TabsTrigger>
          <TabsTrigger value={ CanalEnum.SMS }>SMS</TabsTrigger>
          <TabsTrigger value={ CanalEnum.PUSH }>Notifications</TabsTrigger>
        </TabsList>
        {/* Email Tab */}
        <NotificationTab
          tabValue={ CanalEnum.EMAIL }
          title="Emails"
          description="A list of your recent emails."
          tableCaption="Recent Emails"
          notifications={ notifications }
          dropFn={ dropFn }
          sendFn={ sendFn }
        />
        {/* SMS Tab */}
        <NotificationTab
          tabValue={ CanalEnum.SMS }
          title="SMS"
          description="A list of your recent SMS messages."
          tableCaption="Recent SMS Messages"
          notifications={ notifications }
          dropFn={ dropFn }
          sendFn={ sendFn }
        />
        {/* Push Notifications Tab */}
        <NotificationTab
          tabValue={ CanalEnum.PUSH }
          title="Notifications"
          description="A list of your recent push notifications."
          tableCaption="Recent Push Notifications"
          notifications={ notifications }
          dropFn={ dropFn }
          sendFn={ sendFn }
        />        
    </Tabs>
  )
}

