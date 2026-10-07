import type { BerlinClockTimeState } from "../model/berlinClock";

const API_URL = "/api/berlin-clock";
const API_VERSION = "1.0";

export async function getBerlinClock(): Promise<BerlinClockTimeState> {
  const response = await fetch(API_URL, {
    method: "GET",
    headers: {
      "X-API-Version": API_VERSION,
    },
  });

  if (!response.ok) {
    throw new Error(
      `Failed to retrieve Berlin Clock: ${response.status}`
    );
  }

  return response.json();
}