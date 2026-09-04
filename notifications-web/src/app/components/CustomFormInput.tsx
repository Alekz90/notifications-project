import { Label } from "@/components/ui/label"
import { Input } from "@/components/ui/input"
import { type FieldError, Controller, type Control } from "react-hook-form"

interface Props {
  control: Control<any> | any;
  name: string;
  type: string;
  label: string;
  placeholder: string;
  error?: FieldError;
}

export const CustomFormInput = ({ control, name, type, label, placeholder, error }: Props) => {
  return (
    <div className="grid gap-2">
      <Label className="ml-1" htmlFor={ name }>{ label }</Label>
      <Controller 
        name={ name }
        control={ control }
        render={
          ({ field }) =>  
            <Input
              id={ name } type={ type } placeholder={ placeholder } { ...field }
              className={ error ? "border-red-500" : "" }
            />
        }
      />      
      {error && <span className="text-red-500 text-left">{ error.message }</span>}
    </div>
  )
}
