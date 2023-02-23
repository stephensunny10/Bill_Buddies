package com.itax.billbuddies.activities.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import com.itax.billbuddies.controller.Payment;
import com.itax.billbuddies.databinding.ActivityPaymentBinding;
import com.itax.billbuddies.dialog.AddPaymentDialog;
import com.itax.billbuddies.R;


public class PaymentA extends AppCompatActivity {
    ActivityPaymentBinding binding;
    AddPaymentDialog addPaymentDialog;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        initView();
    }
    private void initView(){
        new Payment(this,binding.getRoot());
        binding.txtTitle.setText("Payment");

        binding.fab.setOnClickListener(v->{
            moveToAddPurchase();
        });

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }

    private void moveToAddPurchase(){
     addPaymentDialog = new AddPaymentDialog(PaymentA.this);
     addPaymentDialog.showDialog();
    }
}
