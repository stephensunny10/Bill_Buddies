package com.itax.billbuddies.activities.Sale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityAddSalesBinding;

public class AddSalesA extends AppCompatActivity {
    ActivityAddSalesBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        binding = ActivityAddSalesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView(){
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }
}