import { Outlet } from "react-router"
import { NotificationContextProvider } from "../context/NotificationContext"

export const ManagementLayout = () => {
  return (
    <NotificationContextProvider>
      <div className="mt-5 max-w-250 mx-auto px-2">
        <h1 className="text-2xl font-bold text-center my-6">Management notifications</h1>
        <Outlet />
      </div>
    </NotificationContextProvider>
  )
}

export default ManagementLayout
