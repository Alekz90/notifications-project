DO $$
    DECLARE
        v_emptyDate TIMESTAMP := TO_TIMESTAMP('1900-01-01', 'YYYY-MM-DD HH:MI:SS');
        v_deleted 	BOOLEAN := false;
        v_userId 	INT := 1;
        v_email_seq	VARCHAR(50) := 'email_notifications_id_seq';
        v_sms_seq	VARCHAR(50) := 'sms_notifications_id_seq';
        v_push_seq	VARCHAR(50) := 'push_notifications_id_seq';
        v_email		VARCHAR(25) := 'user@example.com';
        v_phone		VARCHAR(25) := '8461265896';
        v_push		VARCHAR(25) := 'ASDSDDS-21651AS';
    BEGIN

        DELETE FROM email_notifications WHERE id <> 0;

        INSERT INTO public.email_notifications
        (user_id, id, created_at, updated_at, sent_at, deleted, recipient, status, title, body)
        VALUES
        (v_userId, nextval(v_email_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_email, 'PENDING', 'Welcome to our service', 'Thank you for signing up for our service.')
             , (v_userId, nextval(v_email_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_email, 'SENT', 'Password Reset', 'Click the link below to reset your password.')
             , (v_userId, nextval(v_email_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_email, 'FAILED', 'Account Deactivation', 'Your account has been deactivated.')
             , (v_userId, nextval(v_email_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_email, 'SENT', 'Subscription Renewal', 'Your subscription has been successfully renewed.')
             , (v_userId, nextval(v_email_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_email, 'PENDING', 'New Feature Announcement', 'We have added a new feature to our service.');

        DELETE FROM sms_notifications WHERE id <> 0;

        INSERT INTO public.sms_notifications
        (user_id, id, created_at, updated_at, sent_at, deleted, recipient, status, title, body)
        VALUES
        (v_userId, nextval(v_sms_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_phone, 'PENDING', 'Verification Code', 'Your verification code is 123456')
             , (v_userId, nextval(v_sms_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_phone, 'SENT', 'Account Alert', 'There was a login attempt from a new device.')
             , (v_userId, nextval(v_sms_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_phone, 'SENT', 'Payment Confirmation', 'Your payment has been successfully processed.');

        DELETE FROM push_notifications WHERE id <> 0;

        INSERT INTO public.push_notifications
        (user_id, id, created_at, updated_at, sent_at, deleted, recipient, status, title, body)
        VALUES
        (v_userId, nextval(v_push_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_push, 'PENDING', 'New Message', 'You have received a new message.')
             , (v_userId, nextval(v_push_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_push, 'SENT', 'Friend Request', 'You have received a new friend request.')
             , (v_userId, nextval(v_push_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_push, 'FAILED', 'Event Reminder', 'Don''t forget about the upcoming event.')
             , (v_userId, nextval(v_push_seq), CURRENT_DATE, CURRENT_DATE, v_emptyDate, v_deleted, v_push, 'PENDING', 'System Update', 'The system will undergo maintenance tonight.');
    END;
$$ LANGUAGE plpgsql;