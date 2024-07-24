package util;

public class RepairSolution {

    public static double repairSolutionVariableValue(double value, double lowerBound, double upperBound) {
        if (value < lowerBound)
            value = lowerBound;
        else if (value > upperBound)
            value = upperBound;

        return value;
    }
}
