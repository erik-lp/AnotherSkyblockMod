package me.erik.anotherskyblockmod.util;

import java.util.List;

public class ArrayUtils {
    
    public static <T> T getRandom(List<? extends T> list) {
        return list.get((int) (Math.random() * list.size()));
    }
    
}
