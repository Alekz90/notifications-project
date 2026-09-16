import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { Controller } from "react-hook-form";

interface Props {
  control: any;
  name: string;
  label: string;
  placeholder: string;
  error?: any;
}

export const CustomFormTextArea = ({ control, name, label, placeholder, error }: Props) => (
  <div className="grid gap-2">
    <Label htmlFor={ name }>{ label }</Label>
    <Controller
      name={ name }
      control={ control }
      render={
        ({ field }) =>
          <Textarea
            id={ name }
            placeholder={ placeholder }
            className={ error ? "border-red-500" : "" }
            { ...field }
          />
      }
    />
    { error && <span className="text-red-500 text-left">{ error.message }</span> }
  </div>
);