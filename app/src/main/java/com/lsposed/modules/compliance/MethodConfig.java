package com.lsposed.modules.compliance;

import com.lsposed.modules.utils.Collections;

import java.util.List;

public class MethodConfig {
    public String name;
    public List<String> params;
    public boolean enabled;

    public Object[] generateParamAndCallback(DumpMethodHook dumpMethodHook) {
        if (Collections.isEmpty(params)) {
            return new Object[]{dumpMethodHook};
        }
        int size = params.size() + 1;
        Object[] result = new Object[size];
        int i = 0;
        for (; i < size - 1; i++) {
            try {
                result[i] = generateClass(params.get(i));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        result[i] = dumpMethodHook;
        return result;
    }

    public static Class<?> generateClass(String cls) throws Exception {
        switch (cls) {
            case "byte":
                return byte.class;
            case "short":
                return short.class;
            case "char":
                return char.class;
            case "int":
                return int.class;
            case "long":
                return long.class;
            case "float":
                return float.class;
            case "double":
                return double.class;
            case "boolean":
                return boolean.class;
            default:
                return Class.forName(cls);
        }
    }
}
