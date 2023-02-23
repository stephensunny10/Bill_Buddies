package com.itax.billbuddies.activities.Item;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import com.itax.billbuddies.activities.Sale.AddSalesA;
import com.itax.billbuddies.controller.ItemProduct;
import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityItemBinding;


public class ItemActivity extends AppCompatActivity {
   ActivityItemBinding binding;
   ItemProduct product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        binding = ActivityItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        initView();
    }

    private void initView(){
        product = new ItemProduct(this,binding.getRoot());
        binding.txtTitle.setText("Manage Product");

        binding.imgBack.setOnClickListener(v->{
            finish();
        });

        binding.fab.setOnClickListener(v->{
            moveToAddItem();
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                product.filter(newText);
                return false;
            }
        });
    }

    private void moveToAddSales(){
        startActivity(new Intent(this, AddSalesA.class));
    }

    private void moveToAddItem(){
        startActivity(new Intent(this, AddItemA.class));
    }

    public void onResume(){
        super.onResume();
        product.callApi();
    }


}
