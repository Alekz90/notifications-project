CREATE OR REPLACE PROCEDURE insert_notification(
    pType VARCHAR, pUserId INT, pTo VARCHAR(150), pStatus VARCHAR(10), pTitle VARCHAR(150), pBody VARCHAR(500)
)
    LANGUAGE plpgsql
AS $$
DECLARE
    vEmptyDate 	TIMESTAMP := TO_TIMESTAMP('1900-01-01', 'YYYY-MM-DD HH:MI:SS');
    vEmailSeq	VARCHAR(50) := 'email_notifications_id_seq';
    vSMSSeq		VARCHAR(50) := 'sms_notifications_id_seq';
    vPushSeq	VARCHAR(50) := 'push_notifications_id_seq';
    vEmailFrom	VARCHAR(25) := 'alekz@example.com';
    vPhoneFrom	VARCHAR(15) := '+528461265896';
    vPushFrom	VARCHAR(25) := 'alekz_q8b6e5319c38';
BEGIN
    IF pType = 'EMAIL' THEN		/* Email Insert*/
        INSERT INTO public.email_notifications
            (user_id, id, created_at, updated_at, sent_at, deleted, sender, receiver, status, title, body)
        VALUES
            (pUserId, nextval(vEmailSeq), CURRENT_DATE, CURRENT_DATE, vEmptyDate, false, vEmailFrom, pTo, pStatus, pTitle, pBody);
    ELSIF pType = 'SMS' THEN	/* SMS Insert */
        INSERT INTO public.sms_notifications
            (user_id, id, created_at, updated_at, sent_at, deleted, sender, receiver, status, title, body)
        VALUES
            (pUserId, nextval(vSMSSeq), CURRENT_DATE, CURRENT_DATE, vEmptyDate, false, vPhoneFrom, pTo, pStatus, pTitle, pBody);
    ELSIF pType = 'PUSH' THEN	/* Push Insert */
        INSERT INTO public.push_notifications
            (user_id, id, created_at, updated_at, sent_at, deleted, sender, receiver, status, title, body)
        VALUES
            (pUserId, nextval(vPushSeq), CURRENT_DATE, CURRENT_DATE, vEmptyDate, false, vPushFrom, pTo, pStatus, pTitle, pBody);
    END IF;
END;
$$;


DO $$
    DECLARE
        vUserId	INT := 1;
		vEmailSeq	VARCHAR(50) := 'email_notifications_id_seq';
	    vSMSSeq		VARCHAR(50) := 'sms_notifications_id_seq';
	    vPushSeq	VARCHAR(50) := 'push_notifications_id_seq';
    BEGIN

		-- Destroy the table structure and rebuild it
        TRUNCATE TABLE email_notifications RESTART IDENTITY;
        TRUNCATE TABLE sms_notifications RESTART IDENTITY;
        TRUNCATE TABLE push_notifications RESTART IDENTITY;

		-- Insert the Emails 
        CALL insert_notification(
                'EMAIL', vUserId, 'john.doe@example.com', 'PENDING',
                'Welcome to our platform',
                'Welcome John! Your account has been successfully created.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'jane.smith@example.com', 'SENT',
                'Password Reset',
                'Your password reset request has been processed successfully.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'michael.brown@example.com', 'PENDING',
                'Order Confirmation',
                'Your order #10001 has been received and is being processed.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'sarah.johnson@example.com', 'SENT',
                'Payment Received',
                'We have successfully received your payment of $125.50.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'david.wilson@example.com', 'FAILED',
                'Email Delivery Failed',
                'We were unable to deliver your notification. Please try again.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'emily.davis@example.com', 'PENDING',
                'Account Verification',
                'Please verify your email address to complete your account setup.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'robert.martin@example.com', 'SENT',
                'Order Shipped',
                'Your order #10002 has been shipped and is on its way.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'olivia.anderson@example.com', 'PENDING',
                'New Notification',
                'You have a new notification waiting in your account.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'daniel.thomas@example.com', 'SENT',
                'Profile Updated',
                'Your profile information has been successfully updated.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'sophia.moore@example.com', 'FAILED',
                'Payment Notification',
                'Your payment could not be processed. Please check your payment method.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'alexander.taylor@example.com', 'PENDING',
                'New Login Detected',
                'A new login was detected on your account.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'emma.jackson@example.com', 'SENT',
                'Subscription Activated',
                'Your subscription has been successfully activated.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'william.white@example.com', 'PENDING',
                'Subscription Renewal',
                'Your subscription will renew automatically in 7 days.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'charlotte.harris@example.com', 'SENT',
                'Request Completed',
                'Your request has been successfully completed.'
             );
        CALL insert_notification(
                'EMAIL', vUserId, 'james.martin@example.com', 'PENDING',
                'Important Account Update',
                'There is an important update regarding your account. Please review it at your convenience.'
             );

		-- Insert the SMS messages
        CALL insert_notification(
                'SMS', vUserId, '+528461063940', 'PENDING', 'Welcome',
                'Welcome! Your account has been successfully created.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063941', 'SENT', 'Verification Code',
                'Your verification code is 482731. It expires in 10 minutes.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063942', 'PENDING', 'Password Reset',
                'Your password reset code is 739214. If you did not request this, ignore this message.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063943', 'SENT', 'Order Confirmation',
                'Your order #10001 has been confirmed and is being processed.'
             );
        CALL insert_notification(
                'SMS', vUserId,'+528461063944', 'FAILED', 'Payment Failed',
                'Your payment could not be processed. Please check your payment method and try again.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063945', 'SENT', 'Order Shipped',
                'Your order #10002 has been shipped. You will receive it soon.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063946', 'PENDING', 'Appointment Reminder',
                'Reminder: You have an appointment scheduled for tomorrow at 10:00 AM.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063947', 'SENT', 'Payment Received',
                'Your payment of $850.00 MXN was received successfully. Thank you.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063948', 'PENDING', 'Account Verification',
                'Please verify your phone number using code 315829.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063949', 'SENT', 'Delivery Update',
                'Your package is out for delivery today. Please make sure someone is available.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063950', 'FAILED', 'Notification Failed',
                'We could not deliver your SMS notification. Please try again later.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063951', 'PENDING', 'Subscription Renewal',
                'Your subscription will renew automatically in 7 days.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063952', 'SENT', 'Profile Updated',
                'Your profile information has been updated successfully.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063953', 'PENDING', 'Security Alert',
                'A new login was detected on your account. Contact support if this was not you.'
             );
        CALL insert_notification(
                'SMS', vUserId, '+528461063954', 'SENT', 'Request Completed',
                'Your request has been completed successfully. Thank you for using our service.'
             );

		--Insert Push Notifications
        CALL insert_notification(
                'PUSH', vUserId, 'device_a8f3c9217b45', 'PENDING', 'Welcome',
                'Welcome to the app! Your account has been successfully created.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_b7e4d8126c31', 'SENT', 'New Message',
                'You have received a new message. Open the app to view it.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_c5a9f7348d62', 'PENDING', 'Order Confirmed',
                'Your order #10001 has been confirmed and is now being processed.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_d2b6e8459a73', 'SENT', 'Payment Received',
                'Your payment of $850.00 MXN has been received successfully.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_e4c7f9561b84', 'FAILED', 'Payment Failed',
                'Your payment could not be processed. Please check your payment method.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_f9d2a6384e15', 'PENDING', 'Order Shipped',
                'Your order #10002 has been shipped and is on its way.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_g3e8b7495f26', 'SENT', 'Appointment Reminder',
                'Reminder: You have an appointment tomorrow at 10:00 AM.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_h6f1c8527a39', 'PENDING', 'Account Update',
                'Your account information has been updated successfully.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_i8a4d9632b51', 'SENT', 'Security Alert',
                'A new login was detected on your account. Please review your activity.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_j1b5e8743c62', 'FAILED', 'Notification Failed',
                'We were unable to deliver your notification. Please try again later.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_k7c2f9854d73', 'PENDING', 'Password Changed',
                'Your password has been changed successfully.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_l9d6a1965e84', 'SENT', 'Delivery Update',
                'Your package is out for delivery today. Please be available to receive it.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_m4e7b2876f95', 'PENDING', 'New Promotion',
                'You have a new promotion available. Open the app to see the details.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_n2f8c3987a16', 'SENT', 'Request Completed',
                'Your request has been completed successfully.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_p5a3d4298b27', 'PENDING', 'Subscription Reminder',
                'Your subscription will renew automatically in 7 days.'
             );
        CALL insert_notification(
                'PUSH', vUserId, 'device_q8b6e5319c38', 'SENT', 'System Update',
                'A new update is available. Open the app to get the latest version.'
             );
    END;
$$ LANGUAGE plpgsql;