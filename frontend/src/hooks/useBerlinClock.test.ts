import {
  afterEach,
  describe,
  expect,
  it,
  vi,
} from "vitest";

import { act, renderHook, waitFor } from "@testing-library/react";
import { useBerlinClock } from "./useBerlinClock";
import * as berlinClockApi from "../api/berlinClockApi";
import type { BerlinClockTimeState } from "../model/berlinClock";

describe("useBerlinClock", () => {
    
  afterEach(() => {
    vi.restoreAllMocks();
    vi.useRealTimers();
  });

  it("should load the Berlin Clock state", async () => {

    const state: BerlinClockTimeState = {
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
    };

    vi.spyOn(berlinClockApi, "getBerlinClock").mockResolvedValue(state);

    const { result } = renderHook(() => useBerlinClock());

    await waitFor(() => {
      expect(result.current.loading).toBe(false);
    });

    expect(result.current.clock).toEqual(state);
    expect(result.current.error).toBeNull();
  });

  it("should refresh the clock every second", async () => {
    vi.useFakeTimers();

    const getBerlinClockMock = vi
      .spyOn(berlinClockApi, "getBerlinClock")
      .mockResolvedValue({
        seconds: "YELLOW",
        fiveHoursLamps: [],
        singleHoursLamps: [],
        fiveMinutesLamps: [],
        singleMinutesLamps: [],
      });

    renderHook(() => useBerlinClock());

    await act(async () => {});

    expect(getBerlinClockMock).toHaveBeenCalledTimes(1);

    await act(async () => {
      vi.advanceTimersByTime(1000);
    });

    expect(getBerlinClockMock).toHaveBeenCalledTimes(2);
  });
});