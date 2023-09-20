package com.example.examcrud.algorithmus;

import com.example.examcrud.entity.Frage;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithmus {
    // Weil ich ein Bias von 10 haben möchte und nachher +1 rechnen muss!
    private static final int GEWICHT_TIMEBIAS = 9;

    public static Frage getNextFrage(List<Frage> liste) {
        Map<Double, Frage> gewichteteFragen = createGewichteteFragen(liste);

        // Höchstes gewicht das kommen kann, damit die schlechteste Frage aufjedenfall darunter liegt
        double schlechtestesGewicht = 1;
        Frage schlechtesteFrage = null;

        for (Map.Entry<Double, Frage> e : gewichteteFragen.entrySet()) {
            if (schlechtesteFrage == null || schlechtestesGewicht > e.getKey()){
                schlechtestesGewicht = e.getKey();
                schlechtesteFrage = e.getValue();
            }
        }

        return schlechtesteFrage;
    }

    private static Map<Double, Frage> createGewichteteFragen(List<Frage> liste) {
        Map<Double, Frage> map = new HashMap<>();
        long now = Instant.now().toEpochMilli();
        long maxMsVergangenheit = findOldest(liste).toEpochMilli();

        for (Frage frage : liste) {
            double gewicht = frage.getAltlast() / calculateTimeBias(now, maxMsVergangenheit, frage.getLastTry());
            map.put(gewicht, frage);
        }

        return map;
    }

    /**
     * Sucht den ältesten Zeitstempel der Liste
     */
    private static Instant findOldest(List<Frage> liste) {
        Instant oldest = Instant.now();

        for (Frage frage : liste) {
            if (oldest.isAfter(frage.getLastTry())) {
                oldest = frage.getLastTry();
            }
        }
        return oldest;
    }

    /**
     * @return Zahl zwischen 1 und gewichtTimeBias +1. Umso länger in der Vergangenheit umso höher die Zahl
     */
    public static double calculateTimeBias(long now, long maxMsVergangenheit, Instant toCheck) {
        // Millisekunden von toCheck seit dem aktuellen Zeitstempel
        // Now MUSS immer größer sein als toCheck weil die Millisekunden immer weiter hoch zählen!
        double msVergangenheit = now - toCheck.toEpochMilli();

        return msVergangenheit / maxMsVergangenheit * GEWICHT_TIMEBIAS + 1;
    }
}
