package com.itax.billbuddies.controller;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.itax.billbuddies.adapter.CategoryAdapter;
import com.itax.billbuddies.models.ItemCategoryModel;
import com.itax.billbuddies.models.SalesItem;
import com.itax.billbuddies.R;

import java.util.ArrayList;

public class Profile {
    Context context;
    View view;
    RecyclerView recyclerView;
    ProgressBar pb;
    ArrayList<ItemCategoryModel.ItemCategoryData> itemList = new ArrayList<>();
    CategoryAdapter adapter;


    public Profile(Context context, View view) {
        this.context = context;
        this.view = view;
        initView();
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.recycler_view);
        pb = view.findViewById(R.id.pb);
        adapter = new CategoryAdapter(context, itemList, position -> {
            // clicked item
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        pb.setVisibility(View.GONE);
    }

    private void addItem(){
        for (int i=0; i<10; i++){
            itemList.add(new ItemCategoryModel.ItemCategoryData());
        }
        adapter.notifyDataSetChanged();
    }




}
