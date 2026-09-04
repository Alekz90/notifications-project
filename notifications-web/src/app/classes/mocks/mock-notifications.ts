import type { NotificationDto } from "@interfaces/notification.interface";

export const MOCK_EMAIL_NOTIFICATIONS = [
  {
    id: 1,
    title: "Welcome to our service",
    body: "Thank you for signing up for our service.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-01T12:00:00Z",
    updatedAt: "2024-06-01T12:00:00Z"
  } as NotificationDto,
  {
    id: 2,
    title: "Password Reset",
    body: "Click the link below to reset your password.",
    recipient: "user@example.com",
    status: "SENT",
    sentAt: "2024-06-02T12:00:00Z",
    updatedAt: "2024-06-02T12:00:00Z"
  } as NotificationDto,
  {
    id: 3,
    title: "Account Deactivation",
    body: "Your account has been deactivated.",
    recipient: "user@example.com",
    status: "FAILED",
    sentAt: "2024-06-03T12:00:00Z",
    updatedAt: "2024-06-03T12:00:00Z"
  } as NotificationDto,
  {
    id: 4,
    title: "Subscription Renewal",
    body: "Your subscription has been successfully renewed.",
    recipient: "user@example.com",
    status: "SENDING",
    sentAt: "2024-06-04T12:00:00Z",
    updatedAt: "2024-06-04T12:00:00Z"
  } as NotificationDto,
  {
    id: 5,
    title: "New Feature Announcement",
    body: "We have added a new feature to our service.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-05T12:00:00Z",
    updatedAt: "2024-06-05T12:00:00Z",
  } as NotificationDto,
  {
    id: 6,
    title: "Service Downtime",
    body: "Our service will be down for maintenance tonight.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-06T12:00:00Z",
    updatedAt: "2024-06-06T12:00:00Z",
  } as NotificationDto,
  {
    id: 7,
    title: "Maintenance Completed",
    body: "The scheduled maintenance has been completed.",
    recipient: "user@example.com",
    status: "SENT",
    sentAt: "2024-06-07T12:00:00Z",
    updatedAt: "2024-06-07T12:00:00Z",
  } as NotificationDto,
  {
    id: 8,
    title: "Security Alert",
    body: "A new login to your account was detected.",
    recipient: "user@example.com",
    status: "FAILED",
    sentAt: "2024-06-08T12:00:00Z",
    updatedAt: "2024-06-08T12:00:00Z",
  } as NotificationDto,
  {
    id: 9,
    title: "Password Change",
    body: "Your password has been successfully changed.",
    recipient: "user@example.com",
    status: "SENT",
    sentAt: "2024-06-09T12:00:00Z",
    updatedAt: "2024-06-09T12:00:00Z",
  } as NotificationDto,
  {
    id: 10,
    title: "Profile Update",
    body: "Your profile information has been updated successfully.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-10T12:00:00Z",
    updatedAt: "2024-06-10T12:00:00Z",
  } as NotificationDto,
];
export const MOCK_SMS_NOTIFICATIONS = [
  {
    id: 1,
    title: "Verification Code",
    body: "Your verification code is 123456",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-01T12:00:00Z",
    updatedAt: "2024-06-01T12:00:00Z"
  } as NotificationDto,
  {
    id: 2,
    title: "Account Alert",
    body: "There was a login attempt from a new device.",
    recipient: "user@example.com",
    status: "SENDING",
    sentAt: "2024-06-02T12:00:00Z",
    updatedAt: "2024-06-02T12:00:00Z"
  } as NotificationDto,
  {
    id: 3,
    title: "Payment Confirmation",
    body: "Your payment has been successfully processed.",
    recipient: "user@example.com",
    status: "SENT",
    sentAt: "2024-06-03T12:00:00Z",
    updatedAt: "2024-06-03T12:00:00Z"
  } as NotificationDto,
  {
    id: 4,
    title: "Subscription Renewal",
    body: "Your subscription has been successfully renewed.",
    recipient: "user@example.com",
    status: "SENT",
    sentAt: "2024-06-04T12:00:00Z",
    updatedAt: "2024-06-04T12:00:00Z"
  } as NotificationDto,
  {
    id: 5,
    title: "Delivery Notification",
    body: "Your package has been delivered.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-05T12:00:00Z",
    updatedAt: "2024-06-05T12:00:00Z"
  } as NotificationDto,
  {
    id: 6,
    title: "Service Downtime",
    body: "Our service will be down for maintenance tonight.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-06T12:00:00Z",
    updatedAt: "2024-06-06T12:00:00Z"
  } as NotificationDto,
  {
    id: 7,
    title: "Maintenance Completed",
    body: "The scheduled maintenance has been completed.",
    recipient: "user@example.com",
    status: "SENT",
    sentAt: "2024-06-07T12:00:00Z",
    updatedAt: "2024-06-07T12:00:00Z"
  } as NotificationDto,
  {
    id: 8,
    title: "Security Alert",
    body: "A new login to your account was detected.",
    recipient: "user@example.com",
    status: "FAILED",
    sentAt: "2024-06-08T12:00:00Z",
    updatedAt: "2024-06-08T12:00:00Z"
  } as NotificationDto,
  {
    id: 9,
    title: "Password Change",
    body: "Your password has been successfully changed.",
    recipient: "user@example.com",
    status: "SENT",
    sentAt: "2024-06-09T12:00:00Z",
    updatedAt: "2024-06-09T12:00:00Z"
  } as NotificationDto,
  {
    id: 10,
    title: "Profile Update",
    body: "Your profile information has been updated successfully.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-10T12:00:00Z",
    updatedAt: "2024-06-10T12:00:00Z"
  } as NotificationDto,  
  
];
export const MOCK_PUSH_NOTIFICATIONS = [
  {
    id: 1,
    title: "New Message",
    body: "You have received a new message.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-01T12:00:00Z",
    updatedAt: "2024-06-01T12:00:00Z"
  } as NotificationDto,
  {
    id: 2,
    title: "Friend Request",
    body: "You have received a new friend request.",
    recipient: "user@example.com",
    status: "SENT",
    sentAt: "2024-06-02T12:00:00Z",
    updatedAt: "2024-06-02T12:00:00Z"
  } as NotificationDto,
  {
    id: 3,
    title: "Event Reminder",
    body: "Don't forget about the upcoming event.",
    recipient: "user@example.com",
    status: "FAILED",
    sentAt: "2024-06-03T12:00:00Z",
    updatedAt: "2024-06-03T12:00:00Z"
  } as NotificationDto,
  {
    id: 4,
    title: "System Update",
    body: "The system will undergo maintenance tonight.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-04T12:00:00Z",
    updatedAt: "2024-06-04T12:00:00Z"
  } as NotificationDto,
  {
    id: 5,
    title: "Subscription Renewal",
    body: "Your subscription has been successfully renewed.",
    recipient: "user@example.com",
    status: "SENDING",
    sentAt: "2024-06-05T12:00:00Z",
    updatedAt: "2024-06-05T12:00:00Z"
  } as NotificationDto,
  {
    id: 6,
    title: "Delivery Notification",
    body: "Your package has been delivered.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-06T12:00:00Z",
    updatedAt: "2024-06-06T12:00:00Z"
  } as NotificationDto,
  {
    id: 7,
    title: "Service Downtime",
    body: "Our service will be down for maintenance tonight.",
    recipient: "user@example.com",
    status: "PENDING",
    sentAt: "2024-06-07T12:00:00Z",
    updatedAt: "2024-06-07T12:00:00Z"
  } as NotificationDto,
  {
    id: 8,
    title: "Maintenance Completed",
    body: "The scheduled maintenance has been completed.",
    recipient: "user@example.com",
    status: "SENT",
    sentAt: "2024-06-08T12:00:00Z",
    updatedAt: "2024-06-08T12:00:00Z"
  } as NotificationDto,
  {
    id: 9,
    title: "Security Alert",
    body: "A new login to your account was detected.",
    recipient: "user@example.com",
    status: "FAILED",
    sentAt: "2024-06-09T12:00:00Z",
    updatedAt: "2024-06-09T12:00:00Z"
  } as NotificationDto,
  {
    id: 10,
    title: "Password Change",
    body: "Your password has been successfully changed.",
    recipient: "user@example.com",
    status: "SENT",
    sentAt: "2024-06-10T12:00:00Z",
    updatedAt: "2024-06-10T12:00:00Z"
  } as NotificationDto,
];
