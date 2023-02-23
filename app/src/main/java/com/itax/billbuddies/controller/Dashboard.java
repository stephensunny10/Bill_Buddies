package com.itax.billbuddies.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.itax.billbuddies.adapter.DashboardAdapter;
import com.itax.billbuddies.models.DashboardItem;
import com.itax.billbuddies.R;
import com.itax.billbuddies.utils.Constants;

import java.util.ArrayList;

public class Dashboard {
    Drawable myDrawable;
    Context context;
    View view;
    RecyclerView recyclerView, recyclerView2;
    ArrayList<DashboardItem>itemList = new ArrayList<>();
    ArrayList<DashboardItem>itemList2 = new ArrayList<>();
    DashboardAdapter adapter , adapter2;


    public Dashboard(Context context, View view) {
        this.context = context;
        this.view = view;
        initView();
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView2 = view.findViewById(R.id.recycler_view2);
        adapter = new DashboardAdapter(context, itemList, position -> {
            // clicked item
        });
        adapter2 = new DashboardAdapter(context, itemList2, position -> {
            // clicked item
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        StaggeredGridLayoutManager staggeredGridLayoutManager2 = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager manager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(staggeredGridLayoutManager2);
        recyclerView.setAdapter(adapter);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(staggeredGridLayoutManager);
        recyclerView2.setAdapter(adapter2);
        addDashboardItem1();
        addDashboardItem2();
    }

    private void addDashboardItem2() {
        DashboardItem item2 = new DashboardItem();
        item2 = new DashboardItem();
        item2.amount= Constants.rupee+  "0";
        item2.title = "Total Income";
        item2.icon =R.drawable.incomemoney;
        itemList2.add(item2);

        item2 = new DashboardItem();
        item2.amount= Constants.rupee+  "0";
        item2.title = "Total Expenses";
        item2.icon =R.drawable.expenses;
        itemList2.add(item2);

        item2 = new DashboardItem();
        item2.amount = "0";
        item2.title = "Total Debtors Balance ";
        item2.icon =R.drawable.debitor;
        itemList2.add(item2);

        item2 = new DashboardItem();
        item2.amount = "0";
        item2.title = "Total Creditor Balance";
        item2.icon =R.drawable.creditor;
        itemList2.add(item2);

        item2 = new DashboardItem();
        item2.amount = "0";
        item2.title = "Cash Transaction";
        item2.icon =R.drawable.transaction;
        itemList2.add(item2);

        item2 = new DashboardItem();
        item2.amount = "0";
        item2.title = "Bank Transaction";
        item2.icon =R.drawable.banking;
        itemList2.add(item2);

        adapter2.notifyDataSetChanged();

    }

    private void addItem(){
        for (int i=0; i<10; i++){
            itemList.add(new DashboardItem());
        }
        adapter.notifyDataSetChanged();
    }

    private void addDashboardItem1(){
        DashboardItem item = new DashboardItem();
        item.amount = "0";
        item.title = "Total Sales/Last Bill No";
        item.icon = R.drawable.trolley;
        itemList.add(item);

        item = new DashboardItem();
        item.amount = "0";
        item.title = "Total Purchases/Last Bill No";
        item.icon = R.drawable.salebag;
        itemList.add(item);

        item = new DashboardItem();
        item.amount = "0";
        item.title = "Total Input GST";
        item.icon = R.drawable.taxes;
        itemList.add(item);

        item = new DashboardItem();
        item.amount = "0";
        item.title = "Total Output GST";
        item.icon = R.drawable.coin;
        itemList.add(item);

        item = new DashboardItem();
        item.amount = "0";
        item.title = "Total Items";
        item.icon = R.drawable.shoppingcommerce;
        itemList.add(item);

        item = new DashboardItem();
        item.amount = "0";
        item.title = "Total Customers/Due Amount";
        item.icon = R.drawable.customerfeedback;
        itemList2.add(item);

        item = new DashboardItem();
        item.amount = "0";
        item.title = "Total Stock Value";
        item.icon = R.drawable.trend;
        itemList.add(item);

        item = new DashboardItem();
        item.amount= Constants.rupee+  "0";
        item.title = "Cash Balance";
        item.icon = R.drawable.cashbalance;
        itemList.add(item);

        item = new DashboardItem();
        item.amount= Constants.rupee+  "0";
        item.title = "Bank Balance";
        item.icon = R.drawable.banking;
        itemList.add(item);

        item = new DashboardItem();
        item.amount = "0";
        item.title = "Total Supplier/Due Amount";
        item.icon = R.drawable.supplier;
        itemList.add(item);

        adapter.notifyDataSetChanged();
    }

}
