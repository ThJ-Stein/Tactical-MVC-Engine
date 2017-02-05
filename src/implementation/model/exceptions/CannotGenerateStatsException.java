package implementation.model.exceptions;

/**
 * Created by thomas on 5-2-17.
 */
public class CannotGenerateStatsException extends Exception {
    //TODO add more details to exception
    public CannotGenerateStatsException(int[] statArray, int min, int max, int iteration) {
        super("Failed to generate stats.");
    }
}
