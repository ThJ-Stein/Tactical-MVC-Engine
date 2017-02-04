package implementation.model;

import java.util.EnumMap;

/**
 * Created by thomas on 4-2-17.
 */
public class Stats extends EnumMap<Stats.Stat, Integer> {

    public static int STAT_TOTAL = 300;

    public Stats() {
        super(Stat.class);

        for (Stat stat : Stat.values()) {
            put(stat, 0);
        }
    }

    /**
     * Created by thomas on 4-2-17.
     */
    public enum Stat {
        HP,
        STRENGTH,
        DEFENSE,
        DEXTERITY,
        MAGIC,
        RESISTANCE,
        MOBILITY,
        LUCK
    }
}
