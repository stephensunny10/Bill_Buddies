package com.itax.billbuddies.activities.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityAddPaymentBinding;
import com.itax.billbuddies.databinding.ActivityPaymentBinding;

public class AddPaymentA extends AppCompatActivity {
    ActivityAddPaymentBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        binding = ActivityAddPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initView();
    }

    private void initView(){

    }

}