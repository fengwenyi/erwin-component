package com.fengwenyi.erwin.component.common.util;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

/**
 * 随机数工具类，基于 commons-rng
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-01-03
 */
public class RandomUtils {

    /**
     * 0 <= v < max
     * @param max 最大值（不包含）
     * @return 生成随机数
     */
    public static int nextInt(int max) {
        UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();
        return rng.nextInt(max);
    }

    /**
     * min <= v < max
     * @param min 最小值（包含）
     * @param max 最大值（不包含）
     * @return 生成随机数
     */
    public static int nextInt(int min, int max) {
        UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();
        return rng.nextInt(min, max);
    }

    /**
     * 0 <= v < max
     * @param max 最大值（不包含）
     * @return 生成随机数
     */
    public static long nextLong(int max) {
        UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();
        return rng.nextLong(max);
    }

    /**
     * min <= v < max
     * @param min 最小值（包含）
     * @param max 最大值（不包含）
     * @return 生成随机数
     */
    public static long nextLong(int min, int max) {
        UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();
        return rng.nextLong(min, max);
    }

}
