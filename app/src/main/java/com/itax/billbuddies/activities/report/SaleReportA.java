package com.itax.billbuddies.activities.report;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivitySaleReportBinding;

import java.util.Objects;

public class SaleReportA extends AppCompatActivity {
    private Bundle bundle = new Bundle();
    String value;
    ActivitySaleReportBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_report);
        binding = ActivitySaleReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        value = getIntent().getExtras().getString("fragment");


    }

    @Override
    protected void onResume() {
        super.onResume();
        initView(value);
    }

    private void initView(String value1) {
        if(Objects.equals(value1, "Sale")){
       binding.txtTitle.setText("Sale Report");
        loadFragment(new SaleReportF());}
        else if (Objects.equals(value1, "Purchase")){
            binding.txtTitle.setText("Purchase Report");
            loadFragment(new PuchaseReportF());}
        else if (Objects.equals(value1, "Creditor")){
            binding.txtTitle.setText("Creditor Report");
            loadFragment(new CreditorReportF());}
        else if (Objects.equals(value1, "Debitor")){
            binding.txtTitle.setText("Debitor Report");
            loadFragment(new DebitorReportF());}
        else if (Objects.equals(value1, "Ledger")){
            binding.txtTitle.setText("Ledge Report");
            loadFragment(new LedgerReportF());}
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
    }


    private void loadFragment(Fragment fragment) {
        fragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}