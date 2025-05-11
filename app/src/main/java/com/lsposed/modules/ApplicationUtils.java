package com.lsposed.modules;

import android.app.Application;
import android.content.Context;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class ApplicationUtils {
    public static void onApplicationCreated(ClassLoader loader, OnApplicationCreated back) {
        XposedHelpers.findAndHookMethod(
                Application.class.getName(),
                loader,
                "onCreate",
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                        Application app = (Application) param.thisObject;
                        if (back != null) {
                            back.onApplication(app.getApplicationContext());
                        }
                    }
                }
        );
    }

    public interface OnApplicationCreated {
        void onApplication(Context context);
    }
}
