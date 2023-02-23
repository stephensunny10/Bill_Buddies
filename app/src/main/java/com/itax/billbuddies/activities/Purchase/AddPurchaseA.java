package com.itax.billbuddies.activities.Purchase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityAddPurchaseBinding;

public class AddPurchaseA extends AppCompatActivity {
    ActivityAddPurchaseBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);
        binding = ActivityAddPurchaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView(){
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }
}