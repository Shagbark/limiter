package org.shagbark.limiter;

import org.junit.Test;

/**
 * @author Protsko D.
 *         30.08.2016
 */
public class LimiterTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFrequency() {
        new Limiter(-8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroFrequency() {
        new Limiter(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTryAcquire() {
        Limiter limiter = new Limiter(10);
        limiter.tryAcquire(-20);
    }

}
