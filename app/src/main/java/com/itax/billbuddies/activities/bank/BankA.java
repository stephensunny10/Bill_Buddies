package com.itax.billbuddies.activities.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itax.billbuddies.controller.ManageCash;
import com.itax.billbuddies.databinding.ActivityBankBinding;
import com.itax.billbuddies.dialog.AddCashTransactionDialog;

import com.itax.billbuddies.R;

public class BankA extends AppCompatActivity {
  ActivityBankBinding binding;
  AddCashTransactionDialog addCashTransactionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        binding = ActivityBankBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        initView();
    }
    private void initView(){
        new ManageCash(this,binding.getRoot());
        binding.txtTitle.setText("Manage Cash Transaction");

        binding.fab.setOnClickListener(v->{
            moveToAdd();
        });

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }

    private void moveToAdd(){
addCashTransactionDialog = new AddCashTransactionDialog(BankA.this);
addCashTransactionDialog.showDialog();
        }
}
