package me.erik.anotherskyblockmod.util;

public class MathUtils {
    
    public static int clamp(int num, int min, int max) {
        if (num < min) {
            return min;
        } else {
            return Math.min(num, max);
        }
    }
    
    public static long clamp(long num, long min, long max) {
        if (num < min) {
            return min;
        } else {
            return Math.min(num, max);
        }
    }
    
    public static float clamp(float num, float min, float max) {
        if (num < min) {
            return min;
        } else {
            return Math.min(num, max);
        }
    }
    
    public static double clamp(double num, double min, double max) {
        if (num < min) {
            return min;
        } else {
            return Math.min(num, max);
        }
    }
    
    public static int fastFloor(double value) {
        return (int) (value + 1024.0D) - 1024;
    }
    
}
