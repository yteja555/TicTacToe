package com.example.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ConcurrentModificationException;

public class PreferencesUtils
{
    private static final int MODE_FRIEND=1;
    private static final int MODE_YUVA=2;
    private static final int DEFAULT_MODE=MODE_FRIEND;
    public static SharedPreferences sharedPreferences;

    public static int getModePreference(Context context)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int mode=sharedPreferences.getInt(context.getString(R.string.key_for_saving_mode),DEFAULT_MODE);
        return mode;
    }



    public static void setModePreference(Context context,int mode)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getString(R.string.key_for_saving_mode),mode);
        editor.apply();
        Log.v("preferences changed","to yuva mode");
    }

}
