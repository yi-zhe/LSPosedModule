package com.lsposed.modules;

import com.lsposed.modules.https.HttpsTrustMe;

import de.robv.android.xposed.IXposedHookZygoteInit;

public class Entry implements IXposedHookZygoteInit {
    @Override
    public void initZygote(StartupParam startupParam) {
        HttpsTrustMe.initZygote(startupParam);
    }
}
