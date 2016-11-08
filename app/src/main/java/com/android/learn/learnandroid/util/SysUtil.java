package com.android.learn.learnandroid.util;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;

import com.android.learn.learnandroid.model.StorageInfo;

import java.io.File;

/**
 * Created by sauyee on 7/11/16.
 */

public class SysUtil {

    @SuppressWarnings("deprecation")
    public static StorageInfo getStorageBytes(String path) {
        StorageInfo storageInfo = null;
        if (!TextUtils.isEmpty(path)) {
            try {
                StatFs statFs = new StatFs(path);
                int sdkInt = Build.VERSION.SDK_INT;
                long availableBytes = 0;
                long totalBytes = 0;
                if (sdkInt < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    int blockSize = statFs.getBlockSize();
                    availableBytes = ((long) statFs.getAvailableBlocks()) * blockSize;
                    totalBytes = ((long) statFs.getBlockCount()) * blockSize;
                } else {
                    availableBytes = statFs.getAvailableBytes();
                    totalBytes = statFs.getTotalBytes();
                }
                if (totalBytes > 0) {
                    storageInfo = new StorageInfo(availableBytes, totalBytes);
                }
            } catch (IllegalArgumentException e) {

            }
        }
        return storageInfo;
    }


    public static StorageInfo getSDExternalStorageSize() {
        StorageInfo storageInfo = null;
        String path = System.getenv("SECONDARY_STORAGE");
        if (TextUtils.isEmpty(path)) {
            path = System.getenv("EXTERNAL_SDCARD_STORAGE");
        }

        if (!TextUtils.isEmpty(path)) {
            storageInfo = getStorageBytes(path);
        }
        return storageInfo;
    }

    public static StorageInfo getInternalStorageSize() {
        StorageInfo storageInfo = null;
        String path = Environment.getDataDirectory().getPath();

        if (!TextUtils.isEmpty(path)) {
            storageInfo = getStorageBytes(path);
        }
        return storageInfo;
    }

    public static int convertByteToNearestMB(long byteCount) {
        int ret = 0;
        if (byteCount > 0) {
            long gbThreshold = 1024 * 1024 * 1024;
            long mbThreshold = 1024 * 1024;
//            if (byteCount > gbThreshold) {
//                ret = ((double) byteCount / (double) gbThreshold);
//            } else
            if (byteCount > mbThreshold) {
                double mbValue = ((double) byteCount / (double) mbThreshold);
                ret = (int) (mbValue / 100);
                ret *= 100;
            }
        }
        return ret;
    }
}
