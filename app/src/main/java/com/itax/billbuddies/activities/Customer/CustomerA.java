package com.itax.billbuddies.activities.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.activities.Auth.OtpA;
import com.itax.billbuddies.activities.Purchase.PurchaseA;
import com.itax.billbuddies.controller.Customer;
import com.itax.billbuddies.controller.Purchase;
import com.itax.billbuddies.databinding.ActivityCustomerBinding;
import com.itax.billbuddies.databinding.ActivityPurchaseBinding;
import com.itax.billbuddies.dialog.PurchaseTypeDialog;
import com.itax.billbuddies.listener.CallBackListener;

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
        new Customer(this, binding.getRoot(), (position, data) -> {
            moveToCustomerDetail(data);
        });
        binding.txtTitle.setText(getString(R.string.customer));
        binding.fab.setOnClickListener(v->{
            moveToAddCustomer();
        });

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }

    private void moveToAddCustomer(){
        startActivity(new Intent(this, AddCustomerA.class));
    }

    private void moveToCustomerDetail(String customerData) {
        startActivity(new Intent(this, CustomerDetailA.class).putExtra("data",customerData ));
    }
}