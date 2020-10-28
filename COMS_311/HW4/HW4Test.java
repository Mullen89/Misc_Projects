import java.io.IOException;
import java.util.HashSet;

public class HW4Test {
    public static void main(String[] args) throws Exception {
        RobotPath rPath = new RobotPath();
        rPath.readInput(args[0]);
        System.out.println("\n planShortest:\n");
        rPath.planShortest();
        rPath.output();

        System.out.println("\n quickPlan:\n");
        rPath.quickPlan();
        rPath.output();
    }
}