package com.itax.billbuddies.activities.Item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.itax.billbuddies.R;
import com.itax.billbuddies.controller.ItemProduct;
import com.itax.billbuddies.databinding.ActivityItemSelectBinding;

public class ItemSelectA extends AppCompatActivity {
    ActivityItemSelectBinding binding;
    ItemProduct product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_select);
        binding = ActivityItemSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initView();
    }

    private void initView(){
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
        product = new ItemProduct(this, binding.getRoot(), (position, data) -> {
            returnBack(data);
        });
    }

    public void onResume(){
        super.onResume();
        product.callApi();
    }

    private void returnBack(String data){
        Intent intent = new Intent();
        intent.putExtra("result",data);
        setResult(RESULT_OK,intent);
        finish();
    }
}