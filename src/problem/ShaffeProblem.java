package problem;

public class ShaffeProblem extends Problem {

    private int nVars = 2;

    @Override
    public double[] evaluate(double[] vars) {
        double[] goals = new double[nVars];
        goals[0] = Math.pow(vars[0], 2) + Math.pow(vars[1], 2);
        goals[1] = Math.pow(vars[0], 2) + Math.pow(vars[1] - 2, 2);
        return goals;
    }

    @Override
    public int getNGoals() {
        return nVars;
    }
}
