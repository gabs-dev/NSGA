package operators;

import util.RepairSolution;

import java.util.Random;

public class CrossOverBLXAlpha {

    private double standardDeviation;
    private boolean randomlyFlipChildrenGenes;

    public CrossOverBLXAlpha(double standardDeviation) {
        this(standardDeviation, true);
    }

    public CrossOverBLXAlpha(double standardDeviation, boolean randomlyFlipChildrenGenes) {
        this.standardDeviation = standardDeviation;
        this.randomlyFlipChildrenGenes = randomlyFlipChildrenGenes;
    }

    public double[][] getOffSpring(double[] vars1, double[] vars2, double[] lowerBounds, double[] upperBounds) {
        double[][] ret = new double[2][];

        double[] f1 = new double[vars1.length];
        double[] f2 = new double[vars1.length];

        Random random = new Random();

        for (int i = 0; i < vars1.length; i++) {
            double c1 = 0;
            double c2 = 0;

            if (this.randomlyFlipChildrenGenes) {
                if (random.nextDouble() > 0.5) {
                    c1 = vars1[i] + (random.nextGaussian() * standardDeviation) * Math.abs(vars1[i] - vars2[i]);
                    c2 = vars2[i] + (random.nextGaussian() * standardDeviation) * Math.abs(vars1[i] - vars2[i]);
                } else {
                    c2 = vars1[i] + (random.nextGaussian() * standardDeviation) * Math.abs(vars1[i] - vars2[i]);
                    c1 = vars2[i] + (random.nextGaussian() * standardDeviation) * Math.abs(vars1[i] - vars2[i]);
                }
            } else {
                c1 = vars1[i] + (random.nextGaussian() * standardDeviation) * Math.abs(vars1[i] - vars2[i]);
                c2 = vars2[i] + (random.nextGaussian() * standardDeviation) * Math.abs(vars1[i] - vars2[i]);
            }

            c1 = RepairSolution.repairSolutionVariableValue(c1, lowerBounds[i], upperBounds[i]);
            c2 = RepairSolution.repairSolutionVariableValue(c2, lowerBounds[i], upperBounds[i]);

            f1[i] = c1;
            f2[i] = c2;
        }

        ret[0] = f1;
        ret[1] = f2;

        return ret;
    }
}
