package com.itax.billbuddies.activities.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.itax.billbuddies.R;
import com.itax.billbuddies.controller.Customer;
import com.itax.billbuddies.databinding.ActivitySelectCustomerBinding;
import com.itax.billbuddies.utils.Constants;

public class SelectCustomerA extends AppCompatActivity {
    ActivitySelectCustomerBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_customer);
        binding = ActivitySelectCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initView();
    }

    private void initView(){
        new Customer(this, binding.getRoot(), (position, data) -> {
            returnBack(data);
        });
        binding.txtTitle.setText(getString(R.string.select_customer));
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }

    private void returnBack(String result){
        Constants.selected_customer = result;
        Intent intent = new Intent();
        intent.putExtra("result",result);
        setResult(RESULT_OK,intent);
        finish();
    }

}