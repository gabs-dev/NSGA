import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Individual> population = new LinkedList<>();

        population.add(new Individual(new double[]{1}, new double[]{1, 5})); // A
        population.add(new Individual(new double[]{2}, new double[]{2, 3})); // B
        population.add(new Individual(new double[]{3}, new double[]{4, 1})); // C
        population.add(new Individual(new double[]{4}, new double[]{3, 4})); // D
        population.add(new Individual(new double[]{5}, new double[]{4, 3})); // E
        population.add(new Individual(new double[]{6}, new double[]{5, 5})); // F

        FNDS.execute(population);
    }
}