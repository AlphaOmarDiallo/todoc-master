package com.cleanup.todoc.utils;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class RandomColorUtilTest {

    RandomColorUtil randomColorGen = mock(RandomColorUtil.class);

    @Test
    public void check_that_randomColorGenerator_instantiate_color_with_int_value() {
        int color = randomColorGen.randomColorGenerator();
        assertNotEquals(color, null);
    }
}