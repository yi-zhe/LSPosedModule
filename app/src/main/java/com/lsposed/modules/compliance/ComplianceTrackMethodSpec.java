package com.lsposed.modules.compliance;

import android.content.ContentResolver;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.net.NetworkInterface;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class ComplianceTrackMethodSpec {
    public String className;
    public String methodName;
    public Object[] parameterTypesAndCallback;

    public static ComplianceTrackMethodSpec get(Class<?> clazz, String methodName, Object... parameterTypesAndCallback) {
        return get(clazz.getName(), methodName, parameterTypesAndCallback);
    }

    public static ComplianceTrackMethodSpec get(String className, String methodName, Object... parameterTypesAndCallback) {
        ComplianceTrackMethodSpec spec = new ComplianceTrackMethodSpec();
        spec.className = className;
        spec.methodName = methodName;
        spec.parameterTypesAndCallback = parameterTypesAndCallback;
        return spec;
    }

    public static ComplianceTrackMethodSpec[] get(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        DumpMethodHook dumpMethodHook = new DumpMethodHook(loadPackageParam.packageName);
        return new ComplianceTrackMethodSpec[]{
                get(TelephonyManager.class, "getDeviceId", dumpMethodHook),
                get(TelephonyManager.class, "getDeviceId", int.class, dumpMethodHook),
                get(TelephonyManager.class, "getSubscriberId", int.class, dumpMethodHook),
                get(TelephonyManager.class, "getImei", dumpMethodHook),
                get(TelephonyManager.class, "getImei", int.class, dumpMethodHook),
                get(WifiInfo.class, "getMacAddress", dumpMethodHook),
                get(NetworkInterface.class, "getHardwareAddress", dumpMethodHook),
                get(Settings.Secure.class, "getString", ContentResolver.class, String.class, dumpMethodHook),
                get(LocationManager.class, "getLastKnownLocation", String.class, dumpMethodHook),
                get(LocationManager.class, "requestLocationUpdates", String.class, long.class, float.class, LocationListener.class, dumpMethodHook),
                get(LocationManager.class, "requestLocationUpdates", String.class, long.class, float.class, LocationListener.class, Looper.class, dumpMethodHook),
                get("android.app.ActivityManager", "getRunningAppProcesses", dumpMethodHook),
                get("android.app.ApplicationPackageManager", "getInstalledPackages", int.class, dumpMethodHook),
                get("android.app.ApplicationPackageManager", "getInstalledApplications", int.class, dumpMethodHook),
        };
    }
}
