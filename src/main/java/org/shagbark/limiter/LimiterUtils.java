package org.shagbark.limiter;

/**
 * @author Protsko D.
 *         30.08.2016
 */
public class LimiterUtils {

    private LimiterUtils() {}

    public static long checkFrequency(long frequency) {
        checkArgument(frequency > 0, "Frequency must be positive.");
        return frequency;
    }

    private static void checkArgument(boolean expression,
                                      String exceptionMessage) {
        if (!expression) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
}
