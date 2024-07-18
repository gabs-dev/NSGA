import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CrowdingDistance {

    public static void evaluate(List<Individual> T) {
        int size = T.size();

        for (Individual individual : T)
            individual.crowdingDistance = 0;

        Individual first = T.get(0);
        int M = first.goals.length;
        for (int m = 0; m < M; m++) {
            sort(T, m);

            T.get(0).crowdingDistance = Double.POSITIVE_INFINITY;
            T.get(size - 1).crowdingDistance = Double.POSITIVE_INFINITY;

            for (int i = 1; i < size - 1; i++) {
                double aux = (T.get(i + 1).goals[m] - T.get(i - 1).goals[m])
                        / (T.get(size - 1).goals[m] - T.get(0).goals[m]);
                T.get(i).crowdingDistance += aux;
            }
        }
    }

    private static void sort(List<Individual> individuals, int m) {
        Collections.sort(individuals, Comparator.comparingDouble(x -> x.goals[m]));
    }

}
