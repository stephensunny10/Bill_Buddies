package com.itax.billbuddies.activities.Cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.itax.billbuddies.R;
import com.itax.billbuddies.activities.Item.ItemDetailA;
import com.itax.billbuddies.controller.Cart;
import com.itax.billbuddies.databinding.ActivityCartBinding;
import com.itax.billbuddies.listener.CallBackListener;
import com.itax.billbuddies.utils.Constants;

public class CartA extends AppCompatActivity {
    ActivityCartBinding binding;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initView();
    }

    private void initView(){
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
        cart = new Cart(this, binding.getRoot(), (position, data) -> {
            if (position != Constants.quantity_update){
                startActivity(new Intent(this, ItemDetailA.class).putExtra(Constants.data,data));
            }
        });
    }

    public void onResume(){
        super.onResume();
        cart.loadCartItem();
    }
}