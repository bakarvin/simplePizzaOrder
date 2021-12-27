package com.bakarvin.pizzatime;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    static final String USERNAME_USER = "unameUser";
    static final String CHECK_USER_LOGIN = "checkLogin";
    static final String ALAMAT_USER_LENGKAP = "alamatUserLengkap";
    static final String ALAMAT_USER_SINGKAT = "alamatUserSingkat";

    private  static SharedPreferences getSharedPref(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLoginUname(Context context, String pass){
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString(USERNAME_USER, pass);
        editor.apply();
    }

    public static String getLoginUname(Context context){
        return  getSharedPref(context).getString(USERNAME_USER, "");
    }

    public static void setAlamatUserLengkap(Context context, String alamat){
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString(ALAMAT_USER_LENGKAP, alamat);
        editor.apply();
    }

    public static String getAlamatUserLengkap(Context context){
        return getSharedPref(context).getString(ALAMAT_USER_LENGKAP, "");
    }

    public static void setAlamatUserSingkat(Context context, String alamat){
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString(ALAMAT_USER_SINGKAT, alamat);
        editor.apply();
    }

    public static String getAlamatUserSingkat(Context context){
        return getSharedPref(context).getString(ALAMAT_USER_SINGKAT,"");
    }

    public static void setLoginStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putBoolean(CHECK_USER_LOGIN, status);
        editor.apply();
    }

    public static boolean getLoginStatus(Context context){
        return  getSharedPref(context).getBoolean(CHECK_USER_LOGIN, false);
    }

    public static void clearLoginUser (Context context){
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.remove(USERNAME_USER);
        editor.remove(CHECK_USER_LOGIN);
        editor.remove(ALAMAT_USER_LENGKAP);
        editor.remove(ALAMAT_USER_SINGKAT);
        editor.apply();
    }
}
