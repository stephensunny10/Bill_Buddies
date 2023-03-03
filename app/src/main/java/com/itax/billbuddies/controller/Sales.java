package com.itax.billbuddies.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.itax.billbuddies.activities.ListA;
import com.itax.billbuddies.activities.Sale.SaleDetailA;
import com.itax.billbuddies.adapter.SalesAdapter;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.models.SalesItem;
import com.itax.billbuddies.R;
import com.itax.billbuddies.models.SalesModel;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sales {
    Context context;
    View view;
    RecyclerView recyclerView;
    ProgressBar pb;
    ArrayList<SalesItem>itemList = new ArrayList<>();
    SalesAdapter adapter;
    Functions functions;
    String TAG = "Sales";


    public Sales(Context context, View view) {
        this.context = context;
        this.view = view;
        initView();
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.recycler_view);
        pb = view.findViewById(R.id.pb);
        adapter = new SalesAdapter(context, itemList, position -> {
            moveNext(position);
            // clicked item
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        //addItem();
        pb.setVisibility(View.GONE);
        callApi();
    }
    private void moveNext(int position){
        String saledata = new Gson().toJson(itemList.get(position));
        context.startActivity(new Intent(context, SaleDetailA.class).putExtra("data",saledata));}
    private void addItem(){
        for (int i=0; i<10; i++){
            itemList.add(new SalesItem());
        }
        adapter.notifyDataSetChanged();
    }


    private void callApi(){

        pb.setVisibility(View.VISIBLE);
        String url = ApiList.SALES_URL + "?loginID=" + PaperDbManager.getLoginData().loginID + "&company_id=" + PaperDbManager.getCompany().Company_Id;
        //url = ApiList.SALES_URL+"?loginID="+"ITIC-00000002"+"&company_id="+"COM00001828";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(url, response -> {
            Log.d(TAG, "callApi: "+ response);
            pb.setVisibility(View.GONE);
            SalesModel model = new Gson().fromJson(response,SalesModel.class);
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
