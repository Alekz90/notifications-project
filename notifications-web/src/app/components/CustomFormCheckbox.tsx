import { Label } from "@/components/ui/label"
import { type FieldError, Controller, type Control } from "react-hook-form"
import { Input } from "@/components/ui/input";
import { cn } from "@/lib/utils";

interface Props {
  control: Control<any> | any;
  name: string;
  label: string;
  error?: FieldError
}

export const CustomFormCheckbox = ({ control, name, label, error }: Props) => {
  return (
    <div className="grid gap-2">          
      <Label htmlFor={ name } className={ cn(error ? "text-red-500" : "") }>
        <Controller 
          name={ name }
          control={ control }
          render={
            ({ field }) => (
              <Input id={ name } type="checkbox" { ...field } className="w-5 h-5 text-gray-900 bg-red-500" aria-invalid={ error ? "true" : "false" } />
            )
          }
        />    
        { label }
      </Label>
      {error && <span className="text-red-500 text-left">{ error.message }</span>}
    </div>
  )
}
