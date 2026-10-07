import type { ClockColor } from "../model/berlinClock";

interface LampProps {
  color: ClockColor;
}

export function Lamp({ color }: LampProps) {
  return (
    <div  className={`lamp lamp-${color.toLowerCase()}`}/>
  );
}