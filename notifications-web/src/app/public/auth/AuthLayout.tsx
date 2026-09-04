import { Outlet } from "react-router"

export const AuthLayout = () => {
  return (
    <div className="w-full text-center items-center flex flex-col pt-20 gap-6">
        <Outlet />
    </div>
  )
}

export default AuthLayout;
