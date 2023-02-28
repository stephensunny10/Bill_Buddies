package com.itax.billbuddies.activities.Sale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.itax.billbuddies.controller.Sales;
import com.itax.billbuddies.databinding.ActivitySaleBinding;
import com.itax.billbuddies.dialog.FilterSaleListDialog;
import com.itax.billbuddies.dialog.SalesTypeDialog;
import com.itax.billbuddies.R;

public class SaleA extends AppCompatActivity {
    ActivitySaleBinding binding;
    SalesTypeDialog salesTypeDialog;
    FilterSaleListDialog filterSaleListDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        binding = ActivitySaleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        initView();
    }

    private void initView(){
        new Sales(this,binding.getRoot());
        binding.txtTitle.setText("Sales");

        binding.fab.setOnClickListener(v->{
            moveToAddSales();
        });
 /*       binding.imgFilter.setOnClickListener(v -> {
            filterSaleListDialog = new FilterSaleListDialog(SaleA.this);
            filterSaleListDialog.showDialog();
                });*/
        binding.imgFilter.setOnClickListener(v -> {
            if(!binding.searchLay.isShown())
                binding.searchLay.setVisibility(View.VISIBLE);
            else
            binding.searchLay.setVisibility(View.GONE);
        });

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }

    private void moveToAddSales(){
        salesTypeDialog = new SalesTypeDialog(SaleA.this);
        salesTypeDialog.showDialog();

        //startActivity(new Intent(this, AddSalesA.class));
    }
}