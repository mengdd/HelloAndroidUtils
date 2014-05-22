package com.mengdd.utils.android;

import android.content.Context;
import android.os.Environment;

public class DirectoryUtils {

    private static final String LOG_TAG = "DirectoryUtils";

    public static void getEnvironmentDirectories() {
        LogUtils.i(LOG_TAG, "getRootDirectory(): "
                + Environment.getRootDirectory().toString());
        LogUtils.i(LOG_TAG, "getDataDirectory(): "
                + Environment.getDataDirectory().toString());
        LogUtils.i(LOG_TAG, "getDownloadCacheDirectory(): "
                + Environment.getDownloadCacheDirectory().toString());
        LogUtils.i(LOG_TAG, "getExternalStorageDirectory(): "
                + Environment.getExternalStorageDirectory().toString());

        LogUtils.i(
                LOG_TAG,
                "getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES): "
                        + Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).toString());

//        LogUtils.i(
//                LOG_TAG,
//                "isExternalStorageEmulated(): "
//                        + Environment.isExternalStorageEmulated());
//
//        LogUtils.i(
//                LOG_TAG,
//                "isExternalStorageRemovable(): "
//                        + Environment.isExternalStorageRemovable());

    }

    public static void getApplicationDirectories(Context context) {

        LogUtils.i(LOG_TAG, "context.getFilesDir(): "
                + context.getFilesDir().toString());
        LogUtils.i(LOG_TAG, "context.getCacheDir(): "
                + context.getCacheDir().toString());

        // methods below will return null if the permissions denied
        LogUtils.i(
                LOG_TAG,
                "context.getExternalFilesDir(Environment.DIRECTORY_MOVIES): "
                        + context
                                .getExternalFilesDir(Environment.DIRECTORY_MOVIES));

        LogUtils.i(
                LOG_TAG,
                "context.getExternalCacheDir(): "
                        + context.getExternalCacheDir());
    }
}
