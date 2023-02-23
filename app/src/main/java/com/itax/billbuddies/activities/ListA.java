package com.itax.billbuddies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.itax.billbuddies.activities.Purchase.AddPurchaseA;
import com.itax.billbuddies.activities.Sale.AddSalesA;
import com.itax.billbuddies.controller.OurServices;
import com.itax.billbuddies.controller.Purchase;
import com.itax.billbuddies.controller.Sales;
import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityListBinding;
import com.itax.billbuddies.utils.Constants;

public class ListA extends AppCompatActivity {
    ActivityListBinding binding;
    String pageName = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        initView();
    }

    // in a single activity we can open many pages
    private void initView(){
        if (getIntent().getStringExtra(Constants.page_name) != null){
            pageName = getIntent().getStringExtra(Constants.page_name);

            if (pageName.equals(Constants.sales)){
                new Sales(this,binding.getRoot());
                binding.txtTitle.setText("Sales");
            }

            else if (pageName.equals(Constants.purchase)){
                new Purchase(this,binding.getRoot());
                binding.txtTitle.setText("Purchase");
            }
            else if (pageName.equals(Constants.item)){
                new Purchase(this,binding.getRoot());
                binding.txtTitle.setText("Manage Product");
            }
            else if (pageName.equals(Constants.bank)){
                new Purchase(this,binding.getRoot());
                binding.txtTitle.setText("Bank");
            }
            else if (pageName.equals(Constants.our_services)){
                new OurServices(this,binding.getRoot());
                binding.txtTitle.setText(pageName);
                binding.fab.setVisibility(View.GONE);
            }
        }

        binding.fab.setOnClickListener(v->{
            findCLickFAB();
        });

        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.list_menu,menu);
        return true;
    }

    private void findCLickFAB(){
        switch (pageName){
            case Constants.sales:
                moveToAddSales();
                break;
            case Constants.purchase:
                moveToAddPurchase();
                break;
        }
    }

    private void moveToAddSales(){
        startActivity(new Intent(this, AddSalesA.class));
    }

    private void moveToAddPurchase(){
        startActivity(new Intent(this, AddPurchaseA.class));
    }

}