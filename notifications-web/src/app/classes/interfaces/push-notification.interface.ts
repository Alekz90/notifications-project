export interface PushMessageDto {
    id: string;
    userId: string;
    title: string;
    message: string;
    to: string;
    read: boolean;
    sentAt: string;
}
