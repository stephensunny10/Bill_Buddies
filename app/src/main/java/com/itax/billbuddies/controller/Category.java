package com.itax.billbuddies.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.itax.billbuddies.adapter.CategoryAdapter;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.models.ItemCategoryModel;
import com.itax.billbuddies.models.PurchaseModel;
import com.itax.billbuddies.models.SalesItem;
import com.itax.billbuddies.R;

import java.util.ArrayList;

public class Category {
    Context context;
    View view;
    RecyclerView recyclerView;
    ProgressBar pb;
    ArrayList<ItemCategoryModel.ItemCategoryData> itemList = new ArrayList<>();
    CategoryAdapter adapter;
    String TAG = "Category";


    public Category(Context context, View view) {
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
        //callApi();
    }

    public void callApi(){
        pb.setVisibility(View.VISIBLE);
        String url = ApiList.CATEGORY_URL+"?loginID="+ PaperDbManager.getLoginData().loginID+"&company_id="+PaperDbManager.getCompany().Company_Id;
        //url = ApiList.CATEGORY_URL+"?loginID="+"ITIC-00005161"+"&company_id="+"COM00000001";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(url, response -> {
            Log.d(TAG, "callApi: "+ response);
            pb.setVisibility(View.GONE);
            itemList.clear();
            ItemCategoryModel model = new Gson().fromJson(response,ItemCategoryModel.class);
            if (model.success){
                itemList.addAll(model.data);
                adapter.notifyDataSetChanged();
            }
            if (itemList.isEmpty()){
                view.findViewById(R.id.empty_layout).setVisibility(View.VISIBLE);
            }
        }, error -> {
            Log.d(TAG, "callApi: "+ error);
            pb.setVisibility(View.GONE);
        });
        requestQueue.add(request);
    }

}


