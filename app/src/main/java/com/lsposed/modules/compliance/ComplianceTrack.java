package com.lsposed.modules.compliance;

import com.lsposed.modules.ApplicationUtils;
import com.lsposed.modules.AssetsUtils;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ComplianceTrack {

    public static void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        ApplicationUtils.onApplicationCreated(loadPackageParam.classLoader, context -> {
            if (context != null && AssetsUtils.isAssetsFileExists(context, "hegui")) {
                registerHooks(loadPackageParam);
            } else {
                XposedBridge.log(loadPackageParam.packageName + " has no hegui file in assets");
            }
        });
    }

    private static void registerHooks(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        ComplianceTrackMethodSpec[] specs = ComplianceTrackMethodSpec.get(loadPackageParam);
        for (ComplianceTrackMethodSpec spec : specs) {
            XposedHelpers.findAndHookMethod(spec.className, loadPackageParam.classLoader, spec.methodName, spec.parameterTypesAndCallback);
        }
    }
}
