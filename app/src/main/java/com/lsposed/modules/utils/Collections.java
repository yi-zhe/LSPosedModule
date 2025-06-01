package com.lsposed.modules.utils;

import java.util.Collection;

public class Collections {
    public static boolean isEmpty(Collection<?> e) {
        return e == null || e.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> e) {
        return e != null && e.size() > 0;
    }
}
