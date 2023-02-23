package com.itax.billbuddies.activities.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityWalletBinding;

public class WalletA extends AppCompatActivity {
    ActivityWalletBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        initView();
    }
    private void initView(){
       // new Wallet(this,binding.getRoot());
        binding.txtTitle.setText("Wallet");

      /*  binding.fab.setOnClickListener(v->{
            moveToAddPurchase();
        });*/

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }

    private void moveToAddPurchase(){

    }
}
