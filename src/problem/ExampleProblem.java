package problem;

public class ExampleProblem extends Problem {

    @Override
    public double[] evaluate(double[] vars) {
        switch ((int) vars[0]) {
            case 0, 6:
                return new double[]{1, 5};
            case 1, 8:
                return new double[]{2, 3};
            case 2, 9:
                return new double[]{4, 1};
            case 3:
                return new double[]{3, 4};
            case 4:
                return new double[]{4, 3};
            case 5:
                return new double[]{5, 5};
            case 7:
                return new double[]{1.5, 4};
            default:
                return null;
        }
    }

    @Override
    public int getNGoals() {
        return 1;
    }
}
