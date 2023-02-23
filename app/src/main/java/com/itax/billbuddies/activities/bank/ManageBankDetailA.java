package com.itax.billbuddies.activities.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.itax.billbuddies.controller.ManageCash;
import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityManageBankDetailBinding;

public class ManageBankDetailA extends AppCompatActivity {
    ActivityManageBankDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_bank_detail);
        binding = ActivityManageBankDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        initView();
    }
    private void initView(){
        new ManageCash(this,binding.getRoot());
        binding.txtTitle.setText("Manage Bank");

        binding.fab.setOnClickListener(v->{
            moveToAddPurchase();
        });

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }

    private void moveToAddPurchase(){
        startActivity(new Intent(this, AddBankDetailA.class));
    }
}
