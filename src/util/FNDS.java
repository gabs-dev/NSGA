package util;

import individual.Individual;

import java.util.ArrayList;
import java.util.List;

public final class FNDS {

    public static List<List<Individual>> execute(List<Individual> population) {
        List<List<Individual>> fronts = new ArrayList<>();
        List<Individual> currentFront = new ArrayList<>();

        // 1ª PARTE util.FNDS

        for (Individual p : population) {
            p.n = 0;
            for (Individual q : population) {
                if (dominates(p, q))
                    p.dominatedSet.add(q);
                else if (dominates(q, p))
                    p.n++;
            }

            if (p.n == 0) {
                p.rank = 1;
                currentFront.add(p);
            }
        }

        fronts.add(currentFront);

        int i = 1;
        while (!currentFront.isEmpty()) {
            List<Individual> nextFront = new ArrayList<>();
            for (Individual p : currentFront) {
                for (Individual q : p.dominatedSet) {
                    q.n = q.n - 1;
                    if (q.n == 0) {
                        q.rank = i + 1;
                        nextFront.add(q);
                    }
                }
            }
            i++;
            fronts.add(nextFront);
            currentFront = nextFront;
        }

        return fronts;
    }

    private static boolean dominates(Individual p, Individual q) {
        int allMinors = 0, atLeastOneMinor = 0;

        for (int i = 0; i < p.getGoals().length; i++) {
            if (p.getGoals()[i] <= q.getGoals()[i])
                allMinors++;
            if (p.getGoals()[i] < q.getGoals()[i])
                atLeastOneMinor++;
        }

        return ((p.getGoals().length == allMinors) && (atLeastOneMinor >= 1));
    }
}
