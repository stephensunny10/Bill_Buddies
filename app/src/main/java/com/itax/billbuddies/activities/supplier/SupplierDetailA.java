package com.itax.billbuddies.activities.supplier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.itax.billbuddies.databinding.ActivitySupplierDetailBinding;
import com.itax.billbuddies.models.SupplierModel;

public class SupplierDetailA extends AppCompatActivity {
    ActivitySupplierDetailBinding binding;
    SupplierModel.Supplier model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupplierDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().getStringExtra("data") != null){
            String data = getIntent().getStringExtra("data");
            Log.e("Supplierdata", "onCreate: "+data );
            model = new Gson().fromJson(data, SupplierModel.Supplier.class);
            setData(model);
            binding.imgBack.setOnClickListener(v -> {
                finish();
            });
        }
    }
    private void setData(SupplierModel.Supplier data) {
        binding.sessionFinYear.setText(data.session_fin_year);
        binding.txtAmount.setText(data.due_balance);
        binding.txtCreditDate.setText(data.date_created);
        binding.creditTime.setText(data.credit_time);
        binding.txtName.setText(data.fname);
        binding.txtMobile.setText("Mobile :"+data.mobile);
        binding.txtEmail.setText("Email :"+data.email);
        binding.txtRegDate.setText(data.registration_date);
        binding.txtRegNo.setText(data.registration_no);
        binding.txtGstIn.setText(data.gstin);
        binding.txtTdsApp.setText(data.tdsapplicable);
        binding.natureOfBusi.setText(data.nature_of_business);
        binding.txtAddres.setText("Address :"+data.address);
    }
}