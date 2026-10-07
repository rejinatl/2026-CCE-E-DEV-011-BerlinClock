import { describe, expect, it, vi, beforeEach, afterEach } from "vitest";
import { getBerlinClock } from "./berlinClockApi";


describe("Berlin Clock API", () => {

  beforeEach(() => {
    vi.stubGlobal("fetch", vi.fn());
  });

  afterEach(() => {
    vi.restoreAllMocks();
  });

  it("should request the Berlin Clock using API version 1.0", async () => {

    const berlinClockState = {
      
      seconds: "YELLOW",
      fiveHoursLamps: ["RED", "RED", "OFF", "OFF"],
      singleHoursLamps: ["RED", "RED", "RED", "OFF"],
      fiveMinutesLamps: [
        "YELLOW",
        "YELLOW",
        "RED",
        "YELLOW",
        "YELLOW",
        "RED",
        "OFF",
        "OFF",
        "OFF",
        "OFF",
        "OFF",
      ],
      singleMinutesLamps: [
        "YELLOW",
        "YELLOW",
        "OFF",
        "OFF",
      ],
    };

    vi.mocked(fetch).mockResolvedValue(
      new Response(JSON.stringify(berlinClockState), {
        status: 200,
        headers: {
          "Content-Type": "application/json",
        },
      })
    );

    const result = await getBerlinClock();

    expect(fetch).toHaveBeenCalledWith(
      "/api/berlin-clock",
      {
        method: "GET",
        headers: {
          "X-API-Version": "1.0",
        },
      }
    );

    expect(result).toEqual(berlinClockState);
  });

  it("should throw an error when the API request fails", async () => {

    vi.mocked(fetch).mockResolvedValue(
      new Response(null, {
        status: 500,
      })
    );

    await expect(getBerlinClock())
      .rejects
      .toThrow("Failed to retrieve Berlin Clock: 500");
  });
});