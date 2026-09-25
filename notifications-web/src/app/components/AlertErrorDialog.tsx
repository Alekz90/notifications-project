import { AlertCircleIcon } from "lucide-react"
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert"


export function AlertErrorDialog() {
  return (
    <Alert variant="destructive" className="max-w-md mt-3 shadow-lg mr-0 top-5 right-10 absolute">
      <AlertCircleIcon />
      <AlertTitle>Payment failed</AlertTitle>
      <AlertDescription>
        Your payment could not be processed. Please check your payment method
        and try again.
      </AlertDescription>
    </Alert>    
  )
}
