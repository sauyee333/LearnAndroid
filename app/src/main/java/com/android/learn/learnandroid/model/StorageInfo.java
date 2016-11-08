package com.android.learn.learnandroid.model;

/**
 * Created by sauyee on 7/11/16.
 */

public class StorageInfo {
    private long availBytes;
    private long totalBytes;

    public StorageInfo(long availBytes, long totalBytes) {
        this.availBytes = availBytes;
        this.totalBytes = totalBytes;
    }

    public long getAvailBytes() {
        return availBytes;
    }

    public void setAvailBytes(long availBytes) {
        this.availBytes = availBytes;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public void setTotalBytes(long totalBytes) {
        this.totalBytes = totalBytes;
    }
}
