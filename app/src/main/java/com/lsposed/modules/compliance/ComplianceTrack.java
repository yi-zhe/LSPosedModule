package com.lsposed.modules.compliance;

import android.content.Context;

import com.lsposed.modules.ApplicationUtils;

import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ComplianceTrack {

    private static final Set<String> whiteList = new HashSet<>();

    static {
    }

    public static void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        ApplicationUtils.onApplicationCreated(loadPackageParam.classLoader, context -> {
            if (context != null) {
                registerHooks(context, loadPackageParam);
            }
        });
    }

    private static void registerHooks(Context context, XC_LoadPackage.LoadPackageParam loadPackageParam) {
        ComplianceTrackMethodSpec[] specs = ComplianceTrackMethodSpec.fromConfig(context, loadPackageParam);
        if (specs == null) {
            specs = ComplianceTrackMethodSpec.get(loadPackageParam);
        }
        for (ComplianceTrackMethodSpec spec : specs) {
            if (spec.enabled) {
                android.util.Log.i("==##", " Registering hook: " + spec);
                XposedHelpers.findAndHookMethod(spec.className, loadPackageParam.classLoader, spec.methodName, spec.parameterTypesAndCallback);
            }
        }
    }
}
