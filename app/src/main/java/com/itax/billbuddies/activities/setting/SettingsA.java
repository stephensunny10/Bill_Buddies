package com.itax.billbuddies.activities.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivitySettingsBinding;
import com.itax.billbuddies.utils.SettingsManager;

import java.util.ArrayList;

public class SettingsA extends AppCompatActivity {
    ActivitySettingsBinding binding;
    ArrayList<String> financialYearList = new ArrayList<>();
    ArrayAdapter financialYearAdapter;
    SettingsManager settingsManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        findView();
    }

    private void findView(){
        settingsManager = new SettingsManager(this);
        addFinancialYear();

        binding.layoutUserSettings.setOnClickListener(v->{
            startActivity(new Intent(this,UserSettingA.class));
        });
        binding.layoutPrintSettings.setOnClickListener(v->{
            startActivity(new Intent(this,PrintSettingA.class));
        });

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }

    private void addFinancialYear(){
        financialYearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, financialYearList);
        binding.spinnerFinancialYear.setAdapter(financialYearAdapter);
        binding.spinnerFinancialYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saveSelectedYear(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        int financial_year = 2015,year = 16;
        for (int i=0; i<20; i++){
            financial_year++;
            year++;
            String f_year = Integer.toString(financial_year) + "-" + Integer.toString(year);
            financialYearList.add(f_year);
        }
        financialYearAdapter.notifyDataSetChanged();
        getSelectedYear();  // get saved year
    }

    private void saveSelectedYear(int position){
        settingsManager.setFinancialYear(financialYearList.get(position));
    }

    private void getSelectedYear(){
        String year = settingsManager.getFinancialYear();
        if (!year.isEmpty()){
            binding.spinnerFinancialYear.setSelection(financialYearList.indexOf(year));
        }
    }
}