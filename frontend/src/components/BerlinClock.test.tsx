import { describe, expect, it, vi } from "vitest";
import { render } from "@testing-library/react";
import { BerlinClock } from "./BerlinClock";
import { useBerlinClock } from "../hooks/useBerlinClock";

vi.mock("../hooks/useBerlinClock");

describe("BerlinClock", () => {

  it("should render the Berlin Clock", () => {
    
    vi.mocked(useBerlinClock).mockReturnValue({
      clock: {
        seconds: "YELLOW",
        fiveHoursLamps: ["RED", "RED", "OFF", "OFF"],
        singleHoursLamps: ["RED", "RED", "RED", "OFF"],
        fiveMinutesLamps: [
          "YELLOW",
          "YELLOW",
          "RED",
          "YELLOW",
          "YELLOW",
          "OFF",
          "OFF",
          "OFF",
          "OFF",
          "OFF",
          "OFF",
        ],
        singleMinutesLamps: ["YELLOW", "YELLOW", "OFF", "OFF"],
      },
      loading: false,
      error: null,
    });

    const { container } = render(<BerlinClock />);

    expect(container.querySelector(".berlin-clock")).toBeInTheDocument();
    expect(container.querySelectorAll(".lamp")).toHaveLength(24);
  });
});