package com.itax.billbuddies.controller;

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
import com.itax.billbuddies.activities.Customer.CustomerDetailA;
import com.itax.billbuddies.adapter.CustomerAdapter;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.listener.CallBackListener;
import com.itax.billbuddies.models.CustomerModel;

import java.util.ArrayList;

public class Customer {
    Context context;
    View view;
    RecyclerView recyclerView;
    ArrayList<CustomerModel.CustomerItem> itemList = new ArrayList<>();
    CustomerAdapter adapter;
    String TAG = "customer";
    CallBackListener listener;

    public Customer(Context context, View view, CallBackListener listener) {
        this.context = context;
        this.view = view;
        this.listener = listener;
        initView();
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new CustomerAdapter(context, itemList, position -> {
            String customer_data = new Gson().toJson(itemList.get(position));
            listener.onReturn(position,customer_data);
            // clicked item
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        callApi();
    }

    private void moveNext(int position) {
        String customer_data = new Gson().toJson(itemList.get(position));
        context.startActivity(new Intent(context, CustomerDetailA.class).putExtra("data", customer_data));
    }

    private void callApi() {
        String url = ApiList.CUSTOMER_URL + "?loginID=" + PaperDbManager.getLoginData().loginID + "&company_id=" + PaperDbManager.getCompany().Company_Id;
        // String url = ApiList.CUSTOMER_URL+"?loginID="+"ITIC-00005161"+"&company_id="+"COM00000001";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(url, response -> {
            Log.d(TAG, "callApi: " + response);
            CustomerModel model = new Gson().fromJson(response, CustomerModel.class);
            Log.d("cus_data", "callApi: " + model.data);
            if (model.success) {
                itemList.addAll(model.data);
                Log.d("cus_data", "callApi: " + itemList);
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
