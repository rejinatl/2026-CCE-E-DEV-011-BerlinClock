import { useCallback, useEffect, useState } from "react";
import { getBerlinClock } from "../api/berlinClockApi";
import type { BerlinClockTimeState } from "../model/berlinClock";

interface UseBerlinClockResponse {
  clock: BerlinClockTimeState | null;
  loading: boolean;
  error: string | null;
}

export function useBerlinClock(): UseBerlinClockResponse {

    const [clock, setClock] = useState<BerlinClockTimeState | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    const fetchBerlinClock = useCallback(async () => {

        try {

              const state = await getBerlinClock();
              setClock(state);

        } catch (error) {

            setError(error instanceof Error ? error.message : "Unable to load Berlin Clock");

        } finally {
            
            setLoading(false);
        }

    },[]);

    useEffect(() => {

        fetchBerlinClock();
        const interval = window.setInterval(
            fetchBerlinClock,
            1000
        );

        return () => {
        window.clearInterval(interval);
        };

    }, [fetchBerlinClock]);

    return {
        clock,
        loading,
        error,
    };
}
