package com.itax.billbuddies.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.activities.supplier.SupplierDetailA;
import com.itax.billbuddies.adapter.SupplierAdapter;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.models.SupplierModel;

import java.util.ArrayList;

public class Supplier {
    Context context;
    View view;
    RecyclerView recyclerView;
    ArrayList<SupplierModel.Supplier> itemList = new ArrayList<>();
    SupplierAdapter adapter;
    String TAG = "supplier";


    public Supplier(Context context, View view) {
        this.context = context;
        this.view = view;
        initView();
    }

    private void initView () {
        recyclerView = view.findViewById(R.id.recycler_view);
        Log.d(TAG, "initView: "+itemList);
        adapter = new SupplierAdapter(context, itemList, position -> {
            moveNext(position);
            // clicked item
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        callApi();
    }
    private void moveNext ( int position){
        String supplier_data = new Gson().toJson(itemList.get(position));
        context.startActivity(new Intent(context, SupplierDetailA.class).putExtra("data", supplier_data));
    }
    private void callApi () {
        String url = ApiList.SUPPLIER_URL + "?loginID=" + PaperDbManager.getLoginData().loginID + "&company_id=" + PaperDbManager.getCompany().Company_Id;
       // String url = ApiList.SUPPLIER_URL+"?loginID="+"ITIC-00005161"+"&company_id="+"COM00000001";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        @SuppressLint("NotifyDataSetChanged") StringRequest request = new StringRequest(url, response -> {
            Log.d(TAG, "callApi: " + response);
            SupplierModel model = new Gson().fromJson(response, SupplierModel.class);
            Log.d("cus_data", "callApi: "+model.data);
            if (model.success) {
                itemList.addAll(model.data);
                adapter.notifyDataSetChanged();
            }
            if (itemList.isEmpty()) {
                view.findViewById(R.id.empty_layout).setVisibility(View.VISIBLE);
            }
        }, error -> {
            Log.d(TAG, "callApi: " + error);

        });
        requestQueue.add(request);
    }
}
