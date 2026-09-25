import type { PushMessageDto } from "@/app/classes/interfaces/push-notification.interface";
import { getPushMessages } from "@/app/classes/services/push-notification.service";
import { Constants } from "@/app/classes/utils/constants";
import { UserContext } from "@/app/context/UserContext";
import { Client } from "@stomp/stompjs";
import { useContext, useEffect, useState } from "react";
import { toast } from "@/components/ui/toast"

const PUSH_WS_URL = import.meta.env.VITE_PUSH_WS_URL;

export const UseGetPushMessagesHook = () => {
  const { user } = useContext(UserContext);
  const [ pushMessages, setPushMessages ] = useState<PushMessageDto[]>([]);
  const [ bellActive, setBellActive ] = useState(false);

  useEffect(() => {
    if (!user) return;
    getPushMessages(user.id)
      .then(response => {
        if (response.id === Constants.SUCCESS_CODE) {
          setPushMessages(response.result);
        }
      });
  }, []);  

  useEffect(() => {
    if (!user) return;

    const client = new Client({
      webSocketFactory: () => new WebSocket(`${PUSH_WS_URL}?userId=${user.id.toString()}`),      
      reconnectDelay: 5000,
      debug: (str) => console.log(str),
      onWebSocketError: (error) => console.error('WebSocket error: ', error)
    });

    client.onConnect = () => {
      client.subscribe(`/user/topic/push-notifications`,  (response) => {
        try {
          console.log('Received notification: ', response.body);
          const notification: PushMessageDto = JSON.parse(response.body);
          setPushMessages((prev) => [...prev, notification]);
          toast.add({
            title: notification.title,
            description: notification.message,
          });
        } catch (error) {
          console.error('Error parsing notification: ', error);
        }
      });
    };

    client.activate();
  
    return () => {
      client.deactivate();
    }

  
  }, [user]);

  useEffect(() => {
    setBellActive(pushMessages.some(note => !note.read));
  }, [ pushMessages ]);

  return { pushMessages, setPushMessages, user, bellActive };
}
