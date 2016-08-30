package org.shagbark.limiter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Protsko D.
 *         30.08.2016
 */
public class Limiter {

    /**
     * The frequency is the interval in seconds between execution
     * tasks
     */
    private long frequency;

    /**
     * Default value of frequency is 60 seconds
     */
    private static final long DEFAULT_FREQUENCY = 60;

    /**
     * When was last execution. long is the datetime in millis
     */
    private long lastExecution;

    private final Object mutex = new Object();

    public Limiter() {
        this(DEFAULT_FREQUENCY);
    }

    public Limiter(long frequency) {
        LimiterUtils.checkFrequency(frequency);
        this.frequency = frequency;
        this.lastExecution = 0L;
    }

    public boolean tryAcquire() {
        return this.tryAcquire(this.frequency);
    }

    public boolean tryAcquire(long frequency) {
        LimiterUtils.checkFrequency(frequency);
        synchronized (mutex) {
            long now = new Date().getTime();
            if (now - this.lastExecution >= TimeUnit.SECONDS.toMillis(frequency)) {
                this.lastExecution = now;
                return true;
            }
            return false;
        }
    }

}
