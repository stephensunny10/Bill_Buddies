package com.itax.billbuddies.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.activities.Item.ItemDetailA;
import com.itax.billbuddies.adapter.CartAdapter;
import com.itax.billbuddies.adapter.SalesAdapter;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.layout.CustomLayoutManager;
import com.itax.billbuddies.listener.CallBackListener;
import com.itax.billbuddies.models.CartItem;
import com.itax.billbuddies.utils.Constants;

import java.util.ArrayList;

public class Cart {
    Context context;
    View view;
    RecyclerView recyclerView;
    ProgressBar pb;
    ArrayList<CartItem> itemList = new ArrayList<>();
    CartAdapter adapter;
    CustomLayoutManager mLayoutManager;
    CallBackListener listener;

    public Cart(Context context, View view, CallBackListener listener) {
        this.context = context;
        this.view = view;
        this.listener = listener;
        initView();
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.recycler_view);
        pb = view.findViewById(R.id.pb);
        adapter = new CartAdapter(context, itemList, position -> {
            String data = "";
            if (position != Constants.quantity_update){
                data = new Gson().toJson(itemList.get(position));
            }
            listener.onReturn(position,data);
        });
        mLayoutManager = new CustomLayoutManager(context,CustomLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        //addItem();
        pb.setVisibility(View.GONE);
    }

    private void addItem(){
        for (int i=0; i<3; i++){
            itemList.add(new CartItem());
        }
        adapter.notifyDataSetChanged();
    }

    public void addItem(CartItem item){
        itemList.add(item);
        adapter.notifyDataSetChanged();
    }

    public void clearAllItem(){
        itemList.clear();
        adapter.notifyDataSetChanged();
    }

    public void addItemList(ArrayList<CartItem> itemList){
        itemList.clear();
        this.itemList.addAll(itemList);
        adapter.notifyDataSetChanged();
    }

    public void loadCartItem(){
        itemList.clear();
        itemList.addAll(PaperDbManager.getCartList());
        adapter.notifyDataSetChanged();
    }

}
