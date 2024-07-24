package individual;

import operators.CrossOverBLXAlpha;
import operators.Mutation;
import problem.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Individual {
    public List<Individual> dominatedSet; // Conjunto de pontos dominados.
    public double[] genes;
    public double[] goals;
    public int n; // Número de vezes que o ponto foi dominado.
    public int rank; // Número de fronteiras que pertence.
    public double crowdingDistance;
    public Problem problem;
    private CrossOverBLXAlpha crossOverBLXAlpha;
    private Mutation mutation;

    public Individual(Problem problem, double[] genes) {
        this(problem, genes, new CrossOverBLXAlpha(0.1), new Mutation());
    }

    public Individual(Problem problem, double[] genes, CrossOverBLXAlpha crossOver, Mutation mutation) {
        this.problem = problem;
        this.genes = genes;
        this.dominatedSet = new LinkedList<>();
        this.crossOverBLXAlpha = crossOver;
        this.mutation = mutation;
    }

    public List<Individual> recombine(Individual parent2) {
        List<Individual> offSpringList = new ArrayList<>();

        double[][] genesMatrix = crossOverBLXAlpha.getOffSpring(this.genes, parent2.genes, new double[] {-10, -10}, new double[] {10, 10});

        Individual f1 = new Individual(this.problem, genesMatrix[0]);
        Individual f2 = new Individual(this.problem, genesMatrix[1]);

        offSpringList.add(f1);
        offSpringList.add(f2);

        return offSpringList;
    }

    public void mutate() {
        this.genes = mutation.getPolynomialMutation(this.genes, new double[] {-10, -10}, new double[] {10, 10});
    }

    public double[] getGoals() {
        if (goals == null)
            goals = problem.evaluate(this.genes);

        return goals;
    }
}
