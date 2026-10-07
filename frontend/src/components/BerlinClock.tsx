import { useBerlinClock } from "../hooks/useBerlinClock";
import { ClockRow } from "./ClockRow";
import { Lamp } from "./Lamp";

export function BerlinClock() {

  const {clock, loading, error} = useBerlinClock();

  if (loading) {
    return (
      <div className="clock-message">
        Loading Clock...
      </div>
    );
  }

  if (error) {
    return (
      <div className="clock-error" role="alert" >
        {error}
      </div>
    );
  }

  if (!clock) {
    return null;
  }

  return (

    <section className="berlin-clock">

        <div className="seconds-row">
            <Lamp color={clock.seconds} />
        </div>

        <ClockRow lamps={clock.fiveHoursLamps} />
        <ClockRow lamps={clock.singleHoursLamps} />
        <ClockRow lamps={clock.fiveMinutesLamps} />
        <ClockRow lamps={clock.singleMinutesLamps} />

    </section>
  );
}