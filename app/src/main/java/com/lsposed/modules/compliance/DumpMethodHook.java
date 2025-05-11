package com.lsposed.modules.compliance;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class DumpMethodHook extends XC_MethodHook {

    private final String packageName;

    public DumpMethodHook(String packageName) {
        this.packageName = packageName;
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) {
        XposedBridge.log(packageName + " " + "调用了:" + param.method.getName());
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) {
        dump();
    }

    private static void dump() {
        XposedBridge.log("[Dump Stack]: ---------------start----------------1");
        Throwable ex = new Throwable();
        StackTraceElement[] stackElements = ex.getStackTrace();
        if (stackElements != null) {
            for (int i = 4; i < stackElements.length; i++) {
                XposedBridge.log("[Dump Stack]" +
                        i +
                        ": " +
                        stackElements[i].getClassName() +
                        '.' +
                        stackElements[i].getMethodName() +
                        '(' +
                        stackElements[i].getFileName() +
                        ':' + stackElements[i].getLineNumber() +
                        ')');
            }
        }
        XposedBridge.log("[Dump Stack]: ---------------end----------------1");
    }
}
