package com.itax.billbuddies.activities.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityAddBankDetailBinding;

public class AddBankDetailA extends AppCompatActivity {
   ActivityAddBankDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_detail);
        binding = ActivityAddBankDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }


    private void initView(){
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
   }
}
