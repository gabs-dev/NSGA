package individual;

import individual.Individual;
import problem.Problem;

public class IndividualFactory {

    private Problem problem;

    public IndividualFactory(Problem problem) {
        this.problem = problem;
    }

    public Individual getRandomIndividual() {
        double[] genes = new double[2];

        for (int i = 0; i < genes.length; i++)
            genes[i] = Math.random() * 20 - 10;

        return new Individual(this.problem, genes);
    }

}
