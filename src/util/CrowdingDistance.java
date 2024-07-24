package util;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import individual.Individual;

public class CrowdingDistance {

    public static void evaluate(List<Individual> T) {
        int size = T.size();

        for (Individual individual : T)
            individual.crowdingDistance = 0;

        Individual first = T.get(0);
        int M = first.getGoals().length;
        for (int m = 0; m < M; m++) {
            sort(T, m);

            T.get(0).crowdingDistance = Double.POSITIVE_INFINITY;
            T.get(size - 1).crowdingDistance = Double.POSITIVE_INFINITY;

            for (int i = 1; i < size - 1; i++) {
                double aux = (T.get(i + 1).getGoals()[m] - T.get(i - 1).getGoals()[m])
                        / (T.get(size - 1).getGoals()[m] - T.get(0).getGoals()[m]);
                T.get(i).crowdingDistance += aux;
            }
        }

        Collections.sort(T, Comparator.comparingDouble((Individual x) -> x.crowdingDistance).reversed());
    }

    private static void sort(List<Individual> individuals, int m) {
        Collections.sort(individuals, Comparator.comparingDouble(x -> x.getGoals()[m]));
    }

}
