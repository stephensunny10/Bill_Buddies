package com.itax.billbuddies.activities.supplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.itax.billbuddies.R;
import com.itax.billbuddies.controller.Supplier;
import com.itax.billbuddies.databinding.ActivitySupplierBinding;

public class SupplierA extends AppCompatActivity {
    ActivitySupplierBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupplierBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        initView();
    }
    private void initView(){
        new Supplier(this,binding.getRoot());
        binding.txtTitle.setText("Supplier");

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
        startActivity(new Intent(this, AddSupplierA.class));
    }

}