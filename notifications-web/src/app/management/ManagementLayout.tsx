import { Outlet } from "react-router"
import { NotificationContextProvider } from "@context/NotificationContext"
import { NotificationDrawer } from "@management/notifications/pages/notification-list/components/NotificationDrawer"
import { Toaster } from "@/components/ui/toast"

export const ManagementLayout = () => {
  return (
    <NotificationContextProvider>
      <div className="mt-5 max-w-250 mx-auto px-2">
        <h1 className="text-2xl font-bold text-center my-6">Management notifications</h1>
        <NotificationDrawer/>
        <Toaster />
        <Outlet />
      </div>
    </NotificationContextProvider>
  )
}

export default ManagementLayout
