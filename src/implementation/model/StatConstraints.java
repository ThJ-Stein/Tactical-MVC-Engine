package implementation.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by thomas on 4-2-17.
 */
public class StatConstraints {

    public static StatConstraints UNIVERSAL_CONSTRAINTS = new StatConstraints(
            new int[]{0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{100, 100, 100, 100, 100, 100, 100, 100}
    );

    public static StatConstraints GENERATION_CONSTRAINTS = new StatConstraints(
            new int[]{10, 10, 10, 10, 10, 10, 10, 10},
            new int[]{80, 80, 80, 80, 80, 80, 80, 80}
    );

    private final EnumMap<Stats.Stat, int[]> constraintMap;

    public StatConstraints(int[] minStats, int[] maxStats) {
        assert minStats.length == Stats.Stat.values().length;
        assert maxStats.length == Stats.Stat.values().length;


        constraintMap = new EnumMap<Stats.Stat, int[]>(Stats.Stat.class);

        Stats.Stat[] values = Stats.Stat.values();
        for (int i = 0; i < values.length; i++) {
            Stats.Stat stat = values[i];
            constraintMap.put(stat, new int[]{minStats[i], maxStats[i]});
        }

    }

    public boolean verifyStats(Stats stats) {
        for (Stats.Stat stat : Stats.Stat.values()) {
            int statInt = stats.getStat(stat);
            if (statInt > getMax(stat) || statInt < getMin(stat)) {
                return false;
            }
        }
        return true;
    }

    public int getMin(Stats.Stat stat) {
        return constraintMap.get(stat)[0];
    }

    public int getMax(Stats.Stat stat) {
        return constraintMap.get(stat)[1];
    }

    public int[] getMinArray() {
        int[] minArray = new int[constraintMap.size()];

        for (int i = 0; i < minArray.length; i++) {
            minArray[i] = constraintMap.get(Stats.Stat.values()[i])[0];
        }

        return minArray;
    }

    public int[] getMaxArray() {
        int[] maxArray = new int[constraintMap.size()];

        for (int i = 0; i < maxArray.length; i++) {
            maxArray[i] = constraintMap.get(Stats.Stat.values()[i])[1];
        }

        return maxArray;
    }

    public static StatConstraints combineConstraints(StatConstraints[] constraintsList) {
        int[] min = new int[Stats.howManyStats()];
        int[] max = new int[Stats.howManyStats()];

        Stats.Stat[] values = Stats.Stat.values();
        for (int i = 0; i < values.length; i++) {
            Stats.Stat stat = values[i];

            int[] minForThisStat = new int[constraintsList.length];
            int[] maxForThisStat = new int[constraintsList.length];

            for (int j = 0; j < constraintsList.length; j++) {
                StatConstraints constraints = constraintsList[j];
                minForThisStat[j] = constraints.getMin(stat);
                maxForThisStat[j] = constraints.getMax(stat);
            }


            max[i] = IntStream.of(maxForThisStat).min().getAsInt();
            min[i] = IntStream.of(minForThisStat).max().getAsInt();
        }

        return new StatConstraints(min, max);
    }

    @Override
    public String toString() {
        String value = "";
        for (Map.Entry<Stats.Stat, int[]> statEntry : constraintMap.entrySet()) {
            value += statEntry.getKey() + "=" + Arrays.toString(statEntry.getValue()) + "\n";
        }
        return value;

    }
}