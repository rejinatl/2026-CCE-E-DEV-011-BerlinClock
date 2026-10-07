export type ClockColor = "OFF" | "RED" | "YELLOW";

export interface BerlinClockTimeState {
  seconds: ClockColor;
  fiveHoursLamps: ClockColor[];
  singleHoursLamps: ClockColor[];
  fiveMinutesLamps: ClockColor[];
  singleMinutesLamps: ClockColor[];
}