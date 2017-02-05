package implementation.model;

/**
 * Created by thomas on 4-2-17.
 */
public class Job {
    public static final Job SOLDIER;
    public static final Job APPRENTICE;
    public static final Job THIEF;

    private final StatConstraints constraints;


    static {
        SOLDIER = new Job("Soldier",
                new int[]{40, 40, 40, 30, 0, 0, 30, 20},
                new int[]{100, 100, 100, 100, 40, 40, 100, 100}
        );

        APPRENTICE = new Job("Apprentice",
                new int[]{20, 0, 0, 20, 40, 40, 20, 30},
                new int[]{100, 40, 40, 100, 100, 100, 100, 100}
        );

        THIEF = new Job("Thief",
                new int[]{30, 30, 0, 50, 0, 0, 50, 30},
                new int[]{100, 100, 50, 100, 40, 100, 100, 100}
        );
    }

    private final String name;

    private Job(String name, int[] constraintsMin, int[] constraintsMax) {
        if (constraintsMin.length != Stats.howManyStats()) throw new AssertionError();
        if (constraintsMax.length != Stats.howManyStats()) throw new AssertionError();

        this.name = name;
        this.constraints = new StatConstraints(constraintsMin, constraintsMax);

        if (!constraints.canCreateValidStats()) throw new AssertionError();
    }

    public StatConstraints getConstraints() {
        return constraints;
    }
}
