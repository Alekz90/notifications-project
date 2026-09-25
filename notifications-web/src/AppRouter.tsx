import React from "react";
import { createBrowserRouter, Navigate } from "react-router";
import { RouteConstants } from "@utils/route-constants";
import { LoginPage } from "@auth/pages/login/LoginPage";
import { SignUpPage } from "@auth/pages/sign-up/SignUpPage";
import { NotFoundPage } from "@auth/pages/not-found/NotFoundPage";
import { NotificationPage } from "@/app/management/notifications/pages/notification-list/NotificationPage";

const AuthLayout = React.lazy(() => import("@auth/AuthLayout"));
const ManagementLayout = React.lazy(() => import("./app/management/ManagementLayout"));

export const AppRouter = createBrowserRouter (
  [
    {
      path: RouteConstants.auth.path,
      element: <AuthLayout />,
      children: [
        {
          index: true,
          element: <Navigate to={ RouteConstants.login.toPath } />
        },
        {
          path: RouteConstants.login.path,
          element: <LoginPage />
        },
        {
          path: RouteConstants.register.path,
          element: <SignUpPage />
        }
      ]
    },
    {
      path: RouteConstants.management.path,
      element: <ManagementLayout />,
      children: [
        {
          index: true,
          element: <Navigate to={ RouteConstants.notifications.toPath } />
        },
        {
          path: RouteConstants.notifications.path,
          element: <NotificationPage />
        },
      ]
    },
    {
      path: "*",
      element: <NotFoundPage />
    }
  ]
)

export default AppRouter;