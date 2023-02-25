package com.itax.billbuddies.activities.Purchase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.itax.billbuddies.controller.Purchase;
import com.itax.billbuddies.databinding.ActivityPurchaseBinding;
import com.itax.billbuddies.dialog.PurchaseTypeDialog;

import com.itax.billbuddies.R;


public class PurchaseA extends AppCompatActivity {
    ActivityPurchaseBinding binding;
    PurchaseTypeDialog purchaseTypeDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        binding = ActivityPurchaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        initView();
    }
    private void initView(){
        new Purchase(this,binding.getRoot());
        binding.txtTitle.setText("Purchase");

        binding.fab.setOnClickListener(v->{
           moveToAddPurchase();
        });

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
        binding.imgFilter.setOnClickListener(v -> {
            if(!binding.searchLay.isShown())
            binding.searchLay.setVisibility(View.VISIBLE);
        });
    }

    private void moveToAddPurchase(){
        purchaseTypeDialog = new PurchaseTypeDialog(PurchaseA.this);
        purchaseTypeDialog.showDialog();
    }
}