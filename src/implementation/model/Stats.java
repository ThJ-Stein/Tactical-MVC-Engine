package implementation.model;

import implementation.model.exceptions.CannotGenerateStatsException;
import util.ArrayShuffler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by thomas on 4-2-17.
 */
public class Stats {

    public static final int STAT_TOTAL = 300;

    private final EnumMap<Stat, Integer> statMap;

    private Stats(int[] stats) {
        assert stats.length == Stat.values().length;

        statMap = new EnumMap<Stat, Integer>(Stat.class);

        Stat[] values = Stat.values();
        for (int i = 0; i < values.length; i++) {
            Stat stat = values[i];
            statMap.put(stat, stats[i]);
        }

        assert hasCorrectTotal();
    }

    public static Stats createStats(StatConstraints constraints) throws CannotGenerateStatsException {
        Random rng = new Random();

        ArrayShuffler shuffler = new ArrayShuffler(Stat.values().length);

        int[] minArray = constraints.getMinArray();
        int[] maxArray = constraints.getMaxArray();

        shuffler.shuffle(minArray);
        shuffler.shuffle(maxArray);

        assert IntStream.of(minArray).sum() <= STAT_TOTAL;
        assert IntStream.of(maxArray).sum() >= STAT_TOTAL;

        int[] statArray = new int[Stat.values().length];

        for (int i = 0; i < statArray.length; i++) {

            int[] previousStats = Arrays.copyOfRange(statArray, 0, i);
            int[] remainingMin = Arrays.copyOfRange(minArray, i + 1, statArray.length);
            int[] remainingMax = Arrays.copyOfRange(maxArray, i + 1, statArray.length);

            int sumOfPreviousValues = IntStream.of(previousStats).sum();

            int sumOfMinRemaining = IntStream.of(remainingMin).sum();

            int sumOfMaxRemaining = IntStream.of(remainingMax).sum();

            int maxTotal = STAT_TOTAL - sumOfPreviousValues - sumOfMinRemaining;
            int max = Math.min(maxTotal, maxArray[i]);

            int minTotal = STAT_TOTAL - sumOfPreviousValues - sumOfMaxRemaining;
            int min = Math.max(minTotal, minArray[i]);

            try {
                statArray[i] = rng.nextInt((max - min) + 1) + min;
            } catch (IllegalArgumentException e) {
                throw new CannotGenerateStatsException(statArray, min, max, i);
            }
        }

        shuffler.rearrange(statArray);

        Stats stats = new Stats(statArray);

        return stats;
    }

    public int getStat(Stat stat) {
        return statMap.get(stat);
    }

    public int getStatSum() {
        int total = 0;
        for (Integer integer : statMap.values()) {
            total += integer;
        }
        return total;
    }

    public boolean hasCorrectTotal() {
        return getStatSum() == STAT_TOTAL;
    }

    //TODO rename this method
    public static int howManyStats() {
        return Stat.values().length;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "statMap=" + statMap +
                '}';
    }

    //TODO remove main later on
    public static void main(String[] args) {

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
