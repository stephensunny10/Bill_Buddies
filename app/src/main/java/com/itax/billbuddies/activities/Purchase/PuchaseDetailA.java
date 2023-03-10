package com.itax.billbuddies.activities.Purchase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.itax.billbuddies.databinding.ActivityPuchaseDetailBinding;
import com.itax.billbuddies.models.PurchaseItem;

public class PuchaseDetailA extends AppCompatActivity {
    PurchaseItem model;
    ActivityPuchaseDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuchaseDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().getStringExtra("data") != null){
            String data = getIntent().getStringExtra("data");
            Log.e("purchasedata", "onCreate: "+data );
            model = new Gson().fromJson(data, PurchaseItem.class);
            setData(model);
            binding.imgBack.setOnClickListener(v -> {
                finish();
            });
        }
    }

    private void setData(PurchaseItem data) {
        binding.txtReferenceNo.setText(data.reference_no);
        binding.paymentStatus.setText(data.status);
        binding.txtPurchaseDate.setText(data.purchase_date);
        //binding.txtCustomerName.setText(data.);
        binding.txtDiscount.setText(data.discount_amount);
        binding.txtInvoiceDiscunt.setText(data.invoice_discount);
        binding.txtDue.setText(data.total_due_amount);
        binding.txtTaxAmount.setText(data.tax_amount);
        binding.txtSubTotal.setText(data.subtotal);
        binding.txtPaid.setText(data.total_paid_amount);
        binding.txtTotal.setText(data.total);
    }
}