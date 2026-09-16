import { Tooltip, TooltipContent, TooltipTrigger } from "@/components/ui/tooltip"

interface Props {
  content: string;
  side?: "top" | "bottom" | "left" | "right";
  children: React.ReactElement;
}

export function CustomToolTip({ content, side = "bottom", children }: Props) {
  return (
    <Tooltip>
      <TooltipTrigger render={ children } />
      <TooltipContent side={ side } >
        <p>{ content }</p>
      </TooltipContent>
    </Tooltip>
  )
}
