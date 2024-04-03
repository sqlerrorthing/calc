package me.oneqxz.calc;

/**
 * @author .1qxz
 * @since 2024.04.02
 */

public class CalcUtils {

    public static long factorial(int n) {
        if (n == 0 || n == 1) return 1;
        else return n * factorial(n - 1);
    }

}
