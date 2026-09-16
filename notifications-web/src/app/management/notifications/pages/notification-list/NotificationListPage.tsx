import { Constants } from "@utils/constants";
import { CustomPagination } from "@components/CustomPagination";
import { NotificationTab } from "@notifications/pages/notification-list/components/NotificationTab";
import { Tabs, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { CanalEnum, getCanalEnum } from "@enums/canal-enum";
import { UseCustomGetNotifications } from "@management/hooks/CustomNotificationHook";
import { useState } from "react";
import { useSearchParams } from "react-router";

export const NotificationListPage = () => {

  const [ searchParams, setSearchParams ] = useSearchParams();
  const page = searchParams.get(Constants.PAGE_PARAM_NAME) || Constants.PAGE_DEFAULT;
  const pageSize = searchParams.get(Constants.SIZE_PARAM_NAME) || Constants.SIZE_DEFAULT;
  const canalParam = searchParams.get(Constants.CANAL_PARAM_NAME) || CanalEnum.EMAIL;
  const [canal, setCanal] = useState<CanalEnum>(getCanalEnum(canalParam));

  //console.log("Canal: ", { canalParam })

  const { notifications, totalPages, totalElements } = UseCustomGetNotifications(canal, page, pageSize);

  const handleTabValue = (value: CanalEnum) => {
    searchParams.set(Constants.CANAL_PARAM_NAME, value);
    searchParams.set(Constants.PAGE_PARAM_NAME, Constants.PAGE_DEFAULT);
    setSearchParams(searchParams);
    setCanal(value);
  }

  return (
    <Tabs defaultValue= { CanalEnum.EMAIL } value={canal} onValueChange={ handleTabValue } >
      <TabsList variant="line" className="m-auto p-2 "  >
        <TabsTrigger value={ CanalEnum.EMAIL }>Emails</TabsTrigger>
        <TabsTrigger value={ CanalEnum.SMS }>SMS</TabsTrigger>
        <TabsTrigger value={ CanalEnum.PUSH }>Notifications</TabsTrigger>
      </TabsList>
      {/* Email Tab */}
      <NotificationTab
        tabValue={ CanalEnum.EMAIL }
        title="Emails"
        description="A list of your recent emails."
        tableCaption="Emails not found"
        notifications={ canal === CanalEnum.EMAIL ? notifications : [] }
        pagination={ <CustomPagination totalPages={ totalPages } totalElements={ totalElements } /> }
      />
      {/* SMS Tab */}
      <NotificationTab
        tabValue={ CanalEnum.SMS }
        title="SMS"
        description="A list of your recent SMS messages."
        tableCaption="SMS messages not found"
        notifications={ canal === CanalEnum.SMS ? notifications : [] }
        pagination={ <CustomPagination totalPages={ totalPages } totalElements={ totalElements } /> }
      />
      {/* Push Notifications Tab */}
      <NotificationTab
        tabValue={ CanalEnum.PUSH }
        title="Notifications"
        description="A list of your recent push notifications."
        tableCaption="Push notifications not found"
        notifications={ canal === CanalEnum.PUSH ? notifications : [] }
        pagination={ <CustomPagination totalPages={ totalPages } totalElements={ totalElements } /> }
      />        
    </Tabs>
  )
}

