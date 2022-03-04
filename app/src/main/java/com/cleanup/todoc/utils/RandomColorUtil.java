package com.cleanup.todoc.utils;

import android.graphics.Color;

import androidx.annotation.ColorInt;

import java.util.Random;

public class RandomColorUtil {

    public @ColorInt int randomColorGenerator() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

}
