package com.lsposed.modules;

import com.lsposed.modules.compliance.ComplianceTrack;
import com.lsposed.modules.https.HttpsTrustMe;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Entry implements IXposedHookZygoteInit, IXposedHookLoadPackage {
    @Override
    public void initZygote(StartupParam startupParam) {
        HttpsTrustMe.initZygote(startupParam);
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        ComplianceTrack.handleLoadPackage(loadPackageParam);
    }
}
