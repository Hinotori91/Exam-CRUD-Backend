package com.example.examcrud.algorithmus;

import com.example.examcrud.entity.Frage;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithmus {

    private static final int GEWICHT_TIMEBIAS = 9;  // Weil ich ein Bias von 10 haben möchte und nachher +1 rechnen muss!
    private static final int GEWICHT_PROZENT_RICHTIG = 2;
    private static final int GEWICHT_ALTLAST = 3;


    public static double calculateNeulast(double prozentRichtig, double altlast) {
        return (prozentRichtig * GEWICHT_PROZENT_RICHTIG + altlast * GEWICHT_ALTLAST)
                / GEWICHT_PROZENT_RICHTIG + GEWICHT_ALTLAST;
    }

    public static Frage getNextFrage(List<Frage> liste) {
        Map<Double, Frage> gewichteteFragen = createGewichteteFragen(liste);

        // Höchstes gewicht das kommen kann, damit die schlechteste Frage aufjedenfall darunter liegt
        double schlechtestesGewicht = 1;
        Frage schlechtesteFrage = null;

        for (Map.Entry<Double, Frage> e : gewichteteFragen.entrySet()) {
            if (schlechtesteFrage == null || schlechtestesGewicht > e.getKey()) {
                schlechtestesGewicht = e.getKey();
                schlechtesteFrage = e.getValue();
            }
        }

        return schlechtesteFrage;
    }

    private static Map<Double, Frage> createGewichteteFragen(List<Frage> liste) {
        Map<Double, Frage> map = new HashMap<>();
        long now = Instant.now().toEpochMilli();
        long maxMsVergangenheit = now - findOldest(liste).toEpochMilli();
        Instant getLastTry;

        for (Frage frage : liste) {
            if (frage.getLastTry() == null){
                getLastTry = Instant.now();
            }else {
                getLastTry = frage.getLastTry();
            }

//            double gewicht = frage.getAltlast() / calculateTimeBias(now, maxMsVergangenheit, frage.getLastTry());
            double gewicht = frage.getAltlast() / calculateTimeBias(now, maxMsVergangenheit, getLastTry);
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
            if (frage.getLastTry() != null && oldest.isAfter(frage.getLastTry())) {
                oldest = frage.getLastTry();
            }
        }
        return oldest;
    }

    /**
     * @return Zahl zwischen 1 und gewichtTimeBias +1. Umso länger in der Vergangenheit umso höher die Zahl
     */
    public static double calculateTimeBias(long now, long maxMsVergangenheit, Instant toCheck) {
        // Wenn frage bisher noch nie beantwortet wurde ist das bias die max bias zahl
        if (maxMsVergangenheit == 0) {
            return GEWICHT_TIMEBIAS + 1;
        }

        // Millisekunden von toCheck seit dem aktuellen Zeitstempel
        // Now MUSS immer größer sein als toCheck weil die Millisekunden immer weiter hoch zählen!
        double msVergangenheit = now - toCheck.toEpochMilli();

        return msVergangenheit / maxMsVergangenheit * GEWICHT_TIMEBIAS + 1;
    }
}
