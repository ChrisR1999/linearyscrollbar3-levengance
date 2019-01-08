package com.arturo.almaitu.Utillities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtilities {
    private static int cacheStatus;
    private static SharedPreferences preferences;
    private static String CACHE_LABEL = "cache";

    public CacheUtilities(Activity activity) {
        preferences = activity.getPreferences(Context.MODE_PRIVATE);
        cacheStatus = preferences.getInt(CACHE_LABEL, 1);
    }

    public static void setCacheStatus(int cacheStatus) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CACHE_LABEL, cacheStatus);
        editor.apply();
    }

    public static int getCacheStatus() {
        cacheStatus = preferences.getInt(CACHE_LABEL, 1);
        return cacheStatus;
    }
}
