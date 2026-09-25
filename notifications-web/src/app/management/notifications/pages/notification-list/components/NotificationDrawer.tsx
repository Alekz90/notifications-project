import { Button } from "@/components/ui/button"
import {
  Drawer, DrawerClose, DrawerContent, DrawerDescription, DrawerFooter, DrawerHeader, DrawerTitle, DrawerTrigger
} from "@/components/ui/drawer"
import { Field, FieldContent, FieldDescription, FieldLabel, FieldTitle } from "@/components/ui/field"
import { cn } from "@/lib/utils"
import { CustomToolTip } from "@components/CustomToolTip"
import { UseGetPushMessagesHook } from "@management/hooks/UsePushMessageHook"
import { markPushMessageAsRead } from "@services/push-notification.service"
import { Constants } from "@utils/constants"
import { BellIcon, BellRingIcon } from "lucide-react"
import { useState } from "react"

const CLASS_BELL_ACTIVE = "text-cyan-500 hover:text-cyan-600 hover:bg-cyan-100";
const CLASS_BELL_INACTIVE = "text-gray-500 hover:text-gray-600 hover:bg-gray-100";

export function NotificationDrawer() {
  const [open, setOpen] = useState(false);
  const { pushMessages, setPushMessages, bellActive } = UseGetPushMessagesHook();

  const readNote = (note: any) => () => {
    if (note && !note.read) {
      note.read = true;
      setPushMessages(prev => [...prev]);
      markPushMessageAsRead(note.id)
        .then((response) => {
          if (response.id === Constants.SUCCESS_CODE) {
            console.log(`Successfully marked push message ${note.id} as read.`);
          }
        });
    }
  };

  return (
    <Drawer open={ open } onOpenChange={ setOpen } swipeDirection="right">
      <div className="flex flex-row justify-end">
        <CustomToolTip content="Notifications">
          <span>
            <DrawerTrigger render={(
              <Button variant="ghost" className={ cn("w-6 h-6 rounded-full", bellActive ? CLASS_BELL_ACTIVE : CLASS_BELL_INACTIVE) }>
                { bellActive ? <BellRingIcon/> : <BellIcon/> }
              </Button>
            )}/>
          </span>
        </CustomToolTip>
      </div>
      <DrawerContent>
        <DrawerHeader>
          <DrawerTitle>Notifications</DrawerTitle>
          <DrawerDescription>
            Here are your recent notifications.
          </DrawerDescription>
        </DrawerHeader>
        <div className="flex-1 scroll-fade overflow-y-auto p-4">
          {pushMessages.map((note) => (
            <FieldLabel key={ note.id } htmlFor={ note.id.toString() } className="mb-1">
              <Field orientation="horizontal" className="rounded-sm bg-blue-100 hover:cursor-pointer hover:bg-blue-200" onClick={readNote(note)}>
                <FieldContent>
                  <FieldTitle className={ cn("", !note.read ? "font-bold text-blue-600" : "text-gray-500") }>
                    { note.title }
                  </FieldTitle>
                  <FieldDescription className={ cn("", !note.read ? "text-blue-600" : "text-gray-400") }>
                    { note.message }
                  </FieldDescription>
                </FieldContent>
              </Field>
            </FieldLabel>
          ))}
        </div>
        <DrawerFooter>
          <DrawerClose render={<Button>Close</Button>} />
        </DrawerFooter>
      </DrawerContent>
    </Drawer>
  )
}
