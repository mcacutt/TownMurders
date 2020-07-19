package me.mcacutt.townmurders.util;

import java.util.SplittableRandom;

public final class RandomUtil {
    private static final SplittableRandom RANDOM = new SplittableRandom();

    public static SplittableRandom getRandom() {
        return RANDOM;
    }
}
