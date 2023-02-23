package com.itax.billbuddies.activities.category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.itax.billbuddies.controller.Category;
import com.itax.billbuddies.databinding.ActivityCategoryBinding;
import com.itax.billbuddies.dialog.AddCategoryDialog;
import com.itax.billbuddies.R;

public class CategoryA extends AppCompatActivity {
    ActivityCategoryBinding binding;
    AddCategoryDialog addCategoryDialog;
    Category category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        initView();
    }

    private void initView(){
        category = new Category(this,binding.getRoot());
        binding.txtTitle.setText("Category");
        binding.fab.setOnClickListener(v ->
        moveToAddCategory());
        binding.imgBack.setOnClickListener(v->{
            finish();
        });

        binding.fab.setOnClickListener(v->{
            moveToAddCategory();
        });
    }

    private void moveToAddCategory(){
        startActivity(new Intent(this,AddCategoryA.class));
    }

    public void onResume(){
        super.onResume();
        category.callApi();
    }
}