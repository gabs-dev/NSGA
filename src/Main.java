import individual.Individual;
import individual.IndividualFactory;
import problem.Problem;
import problem.ShaffeProblem;
import util.CrowdingDistance;
import util.FNDS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static final int POPULATION_SIZE = 20;
    static final double MUTATION_PROBABILITY = 0.9;
    static final int NUMBER_OF_SEASONS = 1000;

    public static void main(String[] args) {
        Problem problem = new ShaffeProblem();
        IndividualFactory factory = new IndividualFactory(problem);

        Main main = new Main();
        main.execute(factory);
    }

    public void execute(IndividualFactory factory) {
        int s = 1;
        List<Individual> P = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++)
            P.add(factory.getRandomIndividual());

        while (s <= NUMBER_OF_SEASONS) {
            List<Individual> Q = makeOfSpring(P);

            List<Individual> R = new ArrayList<>();
            R.addAll(P);
            R.addAll(Q);

            List<List<Individual>> F = FNDS.execute(R);

            List<Individual> newPop = new ArrayList<>(POPULATION_SIZE);

            int i = 0;

            while (newPop.size() + F.get(i).size() <= POPULATION_SIZE) {
                newPop.addAll(F.get(i));
                i++;
            }

            List<Individual> finalFront = F.get(i);
            if (newPop.size() < POPULATION_SIZE) {
                CrowdingDistance.evaluate(finalFront);

                int newPopOriginalSize = newPop.size();
                for (int j = 0; j < POPULATION_SIZE - newPopOriginalSize; j++) {
                    newPop.add(finalFront.get(j));
                }
            }

            P = newPop;

            if (s % 20 == 0 || s == 1) {
                System.out.println("\nÉpoca = " + s);
                printPop(newPop);
            }

            s++;
        }
    }

    private void printPop(List<Individual> newPop) {
        for (int i = 0; i < newPop.size(); i++) {
            Individual individual = newPop.get(i);
            System.out.print("(");
            for (int j = 0; j < individual.goals.length; j++) {
                if (j == individual.goals.length - 1)
                    System.out.print(String.format("%f", individual.goals[j]) + ") ");
                else
                    System.out.print(String.format("%f", individual.goals[j]) + "; ");
            }
        }
    }

    public static List<Individual> makeOfSpring(List<Individual> population) {
        Random random = new Random();
        List<Individual> offSpring = new ArrayList<>();
        List<Individual> popAux = new ArrayList<>(population.size());
        popAux.addAll(population);

        while (popAux.size() > 1) {
            Individual parent1 = popAux.remove(random.nextInt(popAux.size()));
            Individual parent2 = popAux.remove(random.nextInt(popAux.size()));

            List<Individual> children = parent1.recombine(parent2);

            Individual c1 = children.get(0);
            if (random.nextDouble() > MUTATION_PROBABILITY)
                c1.mutate();

            Individual c2 = children.get(1);
            if (random.nextDouble() > MUTATION_PROBABILITY)
                c2.mutate();

            offSpring.addAll(children);
        }

        return offSpring;
    }
}