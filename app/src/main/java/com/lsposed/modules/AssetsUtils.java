package com.lsposed.modules;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public class AssetsUtils {
    public static boolean isAssetsFileExists(Context context, String name) {
        try {
            AssetManager assetManager = context.getAssets();
            // 方法1：直接尝试打开文件
            try (InputStream ignored = assetManager.open(name)) {
                return true;
            }
        } catch (IOException e) {
            return false;
        }
    }
}
