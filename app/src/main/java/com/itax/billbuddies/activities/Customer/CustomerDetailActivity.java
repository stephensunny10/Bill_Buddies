package com.itax.billbuddies.activities.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.itax.billbuddies.databinding.ActivityCustomerDetailBinding;
import com.itax.billbuddies.models.CustomerModel;


public class CustomerDetailActivity extends AppCompatActivity {
   ActivityCustomerDetailBinding binding;
   CustomerModel.Customer model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().getStringExtra("data") != null){
            String data = getIntent().getStringExtra("data");
            Log.e("purchasedata", "onCreate: "+data );
            model = new Gson().fromJson(data, CustomerModel.Customer.class);
            setData(model);
            binding.imgBack.setOnClickListener(v -> {
                finish();
            });
        }
    }
    private void setData(CustomerModel.Customer data) {
        binding.sessionFinYear.setText(data.reference_no);
        binding.paymentType.setText(data.status);
        binding.txtDate.setText(data.purchase_date);
        binding.txtDueDate.setText(data.due_date);
        binding.txtDiscount.setText(data.discount_amount);
        binding.txtInvoiceDiscunt.setText(data.invoice_discount);
        binding.txtDue.setText(data.total_due_amount);
        binding.txtTaxAmount.setText(data.tax_amount);
        binding.txtSubTotal.setText(data.subtotal);
        binding.txtPaid.setText(data.total_paid_amount);
        binding.txtTotal.setText(data.total);
    }
}
