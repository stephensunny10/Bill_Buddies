package com.itax.billbuddies.activities.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.itax.billbuddies.R;
import com.itax.billbuddies.activities.Auth.OtpA;
import com.itax.billbuddies.activities.Purchase.PurchaseA;
import com.itax.billbuddies.controller.Customer;
import com.itax.billbuddies.controller.Purchase;
import com.itax.billbuddies.databinding.ActivityCustomerBinding;
import com.itax.billbuddies.databinding.ActivityPurchaseBinding;
import com.itax.billbuddies.dialog.PurchaseTypeDialog;

public class CustomerA extends AppCompatActivity {

    ActivityCustomerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        initView();
    }
    private void initView(){
        new Customer(this,binding.getRoot());
        binding.txtTitle.setText("Customer");

        binding.fab.setOnClickListener(v->{
            moveToAddCustomer();
        });

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    /*    binding.imgFilter.setOnClickListener(v -> {
            if(!binding.searchLay.isShown())
                binding.searchLay.setVisibility(View.VISIBLE);
        });*/

    }

    private void moveToAddCustomer(){
        startActivity(new Intent(this, AddCustomerA.class));
    }
}