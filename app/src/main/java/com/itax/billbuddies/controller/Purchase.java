package com.itax.billbuddies.controller;

import android.content.Context;
import android.content.Intent;
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
import com.itax.billbuddies.activities.Purchase.PuchaseDetailA;
import com.itax.billbuddies.adapter.PurchaseAdapter;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.models.PurchaseItem;
import com.itax.billbuddies.R;
import com.itax.billbuddies.models.PurchaseModel;
import com.itax.billbuddies.models.SalesModel;

import java.util.ArrayList;

public class Purchase {
    Context context;
    View view;
    RecyclerView recyclerView;
    ProgressBar pb;
    ArrayList<PurchaseItem> itemList = new ArrayList<>();
    PurchaseAdapter adapter;
    String TAG = "Purchase";


    public Purchase(Context context, View view) {
        this.context = context;
        this.view = view;
        initView();
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.recycler_view);
        pb = view.findViewById(R.id.pb);
        adapter = new PurchaseAdapter(context, itemList, position -> {
            moveNext(position);
            // clicked item
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        pb.setVisibility(View.GONE);
        callApi();
    }

    private void addItem(){
        for (int i=0; i<10; i++){
            itemList.add(new PurchaseItem());
        }
        adapter.notifyDataSetChanged();
    }
    private void moveNext(int position){
        String Purcahse_data = new Gson().toJson(itemList.get(position));
        context.startActivity(new Intent(context, PuchaseDetailA.class).putExtra("data",Purcahse_data));
    }
    private void callApi(){
        pb.setVisibility(View.VISIBLE);
        String url = ApiList.PURCHASE_URL+"?loginID="+ PaperDbManager.getLoginData().loginID+"&company_id="+PaperDbManager.getCompany().Company_Id;
        //url = ApiList.PURCHASE_URL+"?loginID="+"ITIC-00005161"+"&company_id="+"COM00000001";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(url, response -> {
            Log.d(TAG, "callApi: "+ response);
            pb.setVisibility(View.GONE);
            PurchaseModel model = new Gson().fromJson(response,PurchaseModel.class);
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