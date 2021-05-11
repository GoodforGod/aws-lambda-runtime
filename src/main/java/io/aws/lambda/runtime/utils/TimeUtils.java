package io.aws.lambda.runtime.utils;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 7.11.2020
 */
public class TimeUtils {

    private TimeUtils() {}

    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static long timeTook(long startedInMillis) {
        return getTime() - startedInMillis;
    }
}
