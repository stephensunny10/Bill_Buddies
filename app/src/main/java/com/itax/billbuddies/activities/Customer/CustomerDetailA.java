package com.itax.billbuddies.activities.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.itax.billbuddies.databinding.ActivityCustomerDetailBinding;
import com.itax.billbuddies.models.CustomerModel;
import com.itax.billbuddies.models.SupplierModel;

public class CustomerDetailA extends AppCompatActivity {
    ActivityCustomerDetailBinding binding;
    CustomerModel.Customer model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getStringExtra("data") != null) {
            String data = getIntent().getStringExtra("data");
            Log.e("customer", "onCreate: " + data);
            model = new Gson().fromJson(data, CustomerModel.Customer.class);
            setData(model);
        }

        initView();
    }

    private void initView(){
        binding.imgBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void setData(CustomerModel.Customer data) {
        binding.sessionFinYear.setText(data.session_fin_year);
        binding.txtAmount.setText(data.due_balance);
        binding.txtCreditDate.setText(data.date_created);
        binding.creditTime.setText(data.credit_time);
        binding.txtName.setText(data.fname);
        binding.txtMobile.setText("Mobile :" + data.mobile);
        binding.txtEmail.setText("Email :" + data.email);
        binding.txtRegDate.setText(data.registration_date);
        binding.txtRegNo.setText(data.registration_no);
        binding.txtGstIn.setText(data.gstin);
        binding.txtTdsApp.setText(data.tdsapplicable);
        binding.natureOfBusi.setText(data.nature_of_business);
        binding.txtAddres.setText("Address :" + data.address);
    }
}
