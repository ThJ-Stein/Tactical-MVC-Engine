package implementation.model;

import java.util.ArrayList;

/**
 * Created by thomas on 4-2-17.
 */
public class Unit {
    private final String name;
    private ArrayList<Job> jobs;

    private int primaryJob;

    private Stats stats;

    public Unit(String name, ArrayList<Job> jobs, Stats stats) {
        assert name != null && name.length() > 0;
        assert jobs != null && jobs.size() > 0;
        assert stats != null && stats.hasCorrectTotal();
        assert StatConstraints.combineConstraints(jobs).verifyStats(stats);

        this.name = name;
        this.jobs = jobs;
        this.primaryJob = 0;
        this.stats = stats;
    }

    public Job getPrimaryJob() {
        assert primaryJob >= 0 && primaryJob < jobs.size();

        return jobs.get(primaryJob);
    }

    }
}
