package com.itax.billbuddies.activities.Unit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityUnitBinding;

public class UnitA extends AppCompatActivity {
    ActivityUnitBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        binding = ActivityUnitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }


}