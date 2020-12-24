package com.sanjay.androidamcservice.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sanjay.androidamcservice.app.Constants;

public class AppSharedPreference {

    private final SharedPreferences mPrefs;

    public AppSharedPreference(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private final String PREF_Key= "token";
    private final String IS_LOGGED_IN= "login";
    private final String USERNAME= "username";
    private final String EMAIL= "email";
    private final String PHONE= "phone";
    private final String FIRST_NAME= "first_name";
    private final String LAST_NAME= "last_name";
    private final String LAST_LOGIN= "last_login";
    private final String NEWSLETTER= "receive_newsletter";
    private final String BIRTHDATE= "birth_date";
    private final String ADDRESS= "address";
    private final String CITY= "city";
    private final String ABOUTME= "about_me";
    private final String PROFILE_IMAGE= "profile_image";
    private final String COMPANY_NAME= "company_name";

    public String getTokenKey() {
        return mPrefs.getString(PREF_Key, "");
    }

    public void setTokenKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PREF_Key, pREF_Key);
        mEditor.apply();
    }

    public Boolean getIS_LOGGED_INKEY() {
        return mPrefs.getBoolean(IS_LOGGED_IN, false);
    }

    public void setIS_LOGGED_INKEY(boolean pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putBoolean(IS_LOGGED_IN, pREF_Key);
        mEditor.apply();
    }

    public String getUsernameKey() {
        return mPrefs.getString(USERNAME, "");
    }

    public void setUsernameKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(USERNAME, pREF_Key);
        mEditor.apply();
    }

    public String getEmailKey() {
        return mPrefs.getString(EMAIL, "");
    }

    public void setEmailKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(EMAIL, pREF_Key);
        mEditor.apply();
    }

    public String getPhoneKey() {
        return mPrefs.getString(PHONE, "");
    }

    public void setPhoneKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PHONE, pREF_Key);
        mEditor.apply();
    }

    public String getFirstNameKey() {
        return mPrefs.getString(FIRST_NAME, "");
    }

    public void setFirstNameKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(FIRST_NAME, pREF_Key);
        mEditor.apply();
    }

    public String getLastNameKey() {
        return mPrefs.getString(LAST_NAME, "");
    }

    public void setLastNameKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(LAST_NAME, pREF_Key);
        mEditor.apply();
    }

    public String getBirthDateKey() {
        return mPrefs.getString(BIRTHDATE, "");
    }

    public void setBirthKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(BIRTHDATE, pREF_Key);
        mEditor.apply();
    }

    public String getAddressKey() {
        return mPrefs.getString(ADDRESS, "");
    }

    public void setAddressKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(ADDRESS, pREF_Key);
        mEditor.apply();
    }

    public String getCityKey() {
        return mPrefs.getString(CITY, "");
    }

    public void setCityKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(CITY, pREF_Key);
        mEditor.apply();
    }

    public String getAboutmeKey() {
        return mPrefs.getString(ABOUTME, "");
    }

    public void setAboutmeKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(ABOUTME, pREF_Key);
        mEditor.apply();
    }

    public String getProfileImageKey() {
        return mPrefs.getString(Constants.HOST+PROFILE_IMAGE, "");
    }

    public void setProfileImageKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(PROFILE_IMAGE, pREF_Key);
        mEditor.apply();
    }

    public String getCompanyNameKey() {
        return mPrefs.getString(COMPANY_NAME, "");
    }

    public void setCompanyNameKey(String pREF_Key) {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString(COMPANY_NAME, pREF_Key);
        mEditor.apply();
    }

}
