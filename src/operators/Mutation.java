package operators;

import java.util.Random;

public class Mutation {

    private double mutationProbability;
    private double mutationDistributionIndex;

    public Mutation() {

    }

    public Mutation(double mutationProbability, double mutationDistributionIndex) {
        this.mutationProbability = mutationProbability;
        this.mutationDistributionIndex = mutationDistributionIndex;
    }

    public double[] getMutationNone(double[] x, double[] lowerBound, double[] upperBound) {
        return x;
    }

    public double[] getPolynomialMutation(double[] x, double[] lowerBound, double[] upperBound) {
        Random random = new Random();
        for (int i = 0; i < x.length; i++) {
            if (random.nextDouble() <= mutationProbability) {
                double y = x[i];
                double yl = lowerBound[i];
                double yu = upperBound[i];
                double delta1 = (y - yl) / (yu - yl);
                double delta2 = (yu - y) / (yu - yl);
                double mutPow = 1.0 / (mutationDistributionIndex + 1.0);
                double rnd = random.nextDouble();
                double deltaq;
                if (rnd <= 0.5) {
                    double xy = 1.0 - delta1;
                    double val = 2.0 * rnd + (1.0 - 2.0 * rnd) * Math.pow(xy, (mutationDistributionIndex + 1.0));
                    deltaq = Math.pow(val, mutPow) - 1.0;
                } else {
                    double xy = 1.0 - delta2;
                    double val = 2.0 * (1.0 - rnd) + 2.0 * (rnd - 0.5) * Math.pow(xy, (mutationDistributionIndex + 1.0));
                    deltaq = 1.0 - Math.pow(val, mutPow);
                }
                y = y + deltaq * (yu - yl);
                if (y < yl) {
                    y = yl;
                }
                if (y > yu) {
                    y = yu;
                }
                x[i] = y;
            }
        }

        return x;
    }
}
