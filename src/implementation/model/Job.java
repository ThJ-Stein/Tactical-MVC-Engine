package implementation.model;

/**
 * Created by thomas on 4-2-17.
 */
public class Job {
    private final StatConstraints constraints;

    public Job(StatConstraints constraints) {
        this.constraints = constraints;
    }

    //TODO this method is temporary. remove later
    public static Job getSoldierJob() {
        return new Job(
                new StatConstraints(
                        new int[]{40, 40, 40, 30, 0, 0, 30, 0},
                        new int[]{100, 100, 100, 100, 40, 40, 100, 100}
                )
        );
    }

    public StatConstraints getConstraints() {
        return constraints;
    }
}
