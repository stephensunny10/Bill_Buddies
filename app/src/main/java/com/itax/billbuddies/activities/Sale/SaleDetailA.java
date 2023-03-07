package com.itax.billbuddies.activities.Sale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityCategoryBinding;
import com.itax.billbuddies.databinding.ActivitySaleBinding;
import com.itax.billbuddies.databinding.ActivitySaleDetailBinding;
import com.itax.billbuddies.models.SalesItem;
import com.itax.billbuddies.models.SalesModel;

public class SaleDetailA extends AppCompatActivity {
    SalesItem model;
    ActivitySaleDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaleDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().getStringExtra("data") != null) {

            String data = getIntent().getStringExtra("data");
            Log.e("tandata2", "onCreate: " + data);
            model = new Gson().fromJson(data, SalesItem.class);
            setData(model);
            binding.imgBack.setOnClickListener(v -> {
                finish();
            });
        }
    }

    private void setData(SalesItem data) {
        binding.txtReferenceNo.setText(data.reference_no);
        binding.txtSalesInvoiceId.setText(data.invoice_id);
        binding.txtSaleDate.setText(data.sales_date);
        //binding.txtCustomerName.setText(data.);
        binding.txtDiscount.setText(data.discount_amount);
        binding.txtInvoiceDiscunt.setText(data.invoice_discount);
        binding.txtDue.setText(data.total_due_amount);
        binding.txtTaxAmount.setText(data.tax_amount);
        binding.txtSubTotal.setText(data.subtotal);
        binding.txtPaid.setText(data.total_paid_amount);
        binding.txtSaleStatus.setText(data.status);
        binding.txtTotal.setText(data.total);
        binding.dispatchThrough.setText(data.dispatch_through);
        binding.performaxToTax.setText(data.performa_to_tax);
        binding.differentShipping.setText(data.different_shipping_details);

    }
}