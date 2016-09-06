package org.shagbark.limiter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Protsko D.
 *         30.08.2016
 */
public class LimiterUtilsTest {

    @Test
    public void testCheckFrequency() {
        assertTrue(LimiterUtils.checkFrequency(5) > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCheckFrequency() {
        assertTrue(LimiterUtils.checkFrequency(0) > 0);
    }

}
