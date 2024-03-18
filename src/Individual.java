import java.util.LinkedList;
import java.util.List;

public class Individual {
    List<Individual> dominatedSet; // Conjunto de pontos dominados.
    double[] genes;
    double[] goals;
    int n; // Número de vezes que o ponto foi dominado.
    int rank; // Número de fronteiras que pertence.

    public Individual(int genesAmount, int goalsAmount) {
        this.genes = new double[genesAmount];
        this.goals = new double[goalsAmount];
        this.dominatedSet = new LinkedList<>();
    }

    public Individual(double[] genes, double[] goals) {
        this.genes = genes;
        this.goals = goals;
        this.dominatedSet = new LinkedList<>();
    }

}
