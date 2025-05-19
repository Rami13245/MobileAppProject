package com.example.testingg;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "DetrackerPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_TERMS_ACCEPTED = "TermsAccepted";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_NAME = "Name";
    private static final String KEY_WEIGHT = "Weight";
    private static final String KEY_HEIGHT = "Height";
    private static final String KEY_GENDER = "Gender";
    private static final String KEY_BMR = "BMR";

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(String email) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public String getUserEmail() {
        return pref.getString(KEY_EMAIL, null);
    }

    public void acceptTerms() {
        editor.putBoolean(IS_TERMS_ACCEPTED, true);
        editor.commit();
    }

    public boolean hasAcceptedTerms() {
        return pref.getBoolean(IS_TERMS_ACCEPTED, false);
    }

    public void saveProfile(String name, String gender, int height, int weight, int bmr) {
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_GENDER, gender);
        editor.putInt(KEY_HEIGHT, height);
        editor.putInt(KEY_WEIGHT, weight);
        editor.putInt(KEY_BMR, bmr);
        editor.commit();
    }

    public int getBMR() {
        return pref.getInt(KEY_BMR, 0);
    }

    public String getGender() {
        return pref.getString(KEY_GENDER, "Unknown");
    }

    public int getHeight() {
        return pref.getInt(KEY_HEIGHT, 0);
    }

    public int getWeight() {
        return pref.getInt(KEY_WEIGHT, 0);
    }
}
