import type { ClockColor } from "../model/berlinClock";
import { Lamp } from "./Lamp";

interface ClockRowProps {
  lamps: ClockColor[];
}

export function ClockRow({ lamps }: ClockRowProps) {
  return (
    <div className="clock-row">

      {
      lamps.map((color, index) => (
        
        <Lamp key={`${index}-${color}`} color={color} />
      ))
      }
    </div>
  );
}