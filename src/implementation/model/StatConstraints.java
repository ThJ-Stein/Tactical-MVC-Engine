package implementation.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by thomas on 4-2-17.
 */
public class StatConstraints {

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

    @Override
    public String toString() {
        String value = "";
        for (Map.Entry<Stats.Stat, int[]> statEntry : constraintMap.entrySet()) {
            value += statEntry.getKey() + "=" + Arrays.toString(statEntry.getValue()) + "\n";
        }
        return value;

    }
}
