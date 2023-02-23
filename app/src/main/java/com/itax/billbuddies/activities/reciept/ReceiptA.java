package com.itax.billbuddies.activities.reciept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itax.billbuddies.controller.Payment;
import com.itax.billbuddies.databinding.ActivityReceiptBinding;
import com.itax.billbuddies.dialog.AddPaymentDialog;
import com.itax.billbuddies.R;

public class ReceiptA extends AppCompatActivity {
   ActivityReceiptBinding binding;
   AddPaymentDialog addPaymentDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        binding = ActivityReceiptBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        initView();
    }
    private void initView(){
        new Payment(this,binding.getRoot());
        binding.txtTitle.setText("Receipt");

        binding.fab.setOnClickListener(v->{
            moveToAddPurchase();
        });

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }

    private void moveToAddPurchase(){
        addPaymentDialog = new AddPaymentDialog(ReceiptA.this);
        addPaymentDialog.showDialog();
    }
}