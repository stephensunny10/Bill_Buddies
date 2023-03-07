package com.itax.billbuddies.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String financialYear;


    public SettingsManager(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        pref = context.getSharedPreferences("Settings",0);
        editor = pref.edit();
    }


    public String getFinancialYear() {
        financialYear = pref.getString("financial_year","");
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
        editor.putString("financial_year",financialYear).commit();
    }
}
