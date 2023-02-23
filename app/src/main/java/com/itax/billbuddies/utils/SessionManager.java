package com.itax.billbuddies.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    boolean isLoggedIn = false;
    String mobileNumber;

    public SessionManager(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        pref = context.getSharedPreferences("MyPref",0);
        editor = pref.edit();
    }

    public boolean isLoggedIn() {
        isLoggedIn = pref.getBoolean("isLoggedIn",false);
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        editor.putBoolean("isLoggedIn",isLoggedIn).commit();
    }

    public String getMobileNumber() {
        mobileNumber = pref.getString("mobileNo","");
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        editor.putString("mobileNo",mobileNumber).commit();
    }

    public void setAssessmentYear(String assessmentYear){
        editor.putString("assessmentYear",assessmentYear).commit();
    }

    public String getAssessmentYear(){
        return pref.getString("assessmentYear","2022-2023");
    }

    public void setFinancialYear(String assessmentYear){
        editor.putString("financialYear",assessmentYear).commit();
    }

    public String getFinancialYear(){
        return pref.getString("financialYear","2022-2023");
    }
}
