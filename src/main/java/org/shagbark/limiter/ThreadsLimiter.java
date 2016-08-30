package org.shagbark.limiter;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Protsko D.
 *         30.08.2016
 */
public class ThreadsLimiter {

    private ConcurrentMap<Long, Long> queue;

    private final Object mutex = new Object();

    private static final ThreadsLimiter instance = new ThreadsLimiter();

    private ThreadsLimiter() {
        this.queue = new ConcurrentHashMap<>();
    }

    public static ThreadsLimiter getInstance() {
        return instance;
    }

    public boolean tryAcquire(long frequency) {
        LimiterUtils.checkFrequency(frequency);

        Long threadID = Thread.currentThread().getId();
        Long lastExecution = this.queue.get(threadID);
        if (lastExecution == null) {
            lastExecution = new Date().getTime();
            synchronized (mutex) {
                this.queue.put(threadID, lastExecution);
                return true;
            }
        }

        long now = new Date().getTime();
        if (now - lastExecution >= TimeUnit.SECONDS.toMillis(frequency)) {
            synchronized (mutex) {
                lastExecution = now;
                this.queue.put(threadID, lastExecution);
                return true;
            }
        }
        return false;
    }

}
