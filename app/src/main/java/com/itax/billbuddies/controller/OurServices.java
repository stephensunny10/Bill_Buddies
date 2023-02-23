package com.itax.billbuddies.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.itax.billbuddies.adapter.ServicesAdapter;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.R;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.models.ServicesModel;
import com.itax.billbuddies.utils.Constants;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class OurServices {
    Context context;
    View view;
    RecyclerView recyclerView;
    ProgressBar pb;
    ArrayList<ServicesModel.ServicesItem> itemList = new ArrayList<>();
    ServicesAdapter adapter;
    String TAG = "OurServices";


    public OurServices(Context context, View view) {
        this.context = context;
        this.view = view;
        initView();
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.recycler_view);
        pb = view.findViewById(R.id.pb);
        adapter = new ServicesAdapter(context, itemList, position -> {
            // clicked item
            openBrowser(itemList.get(position).productionURL);
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(adapter);

        pb.setVisibility(View.GONE);
        callApi();
    }

    private void openBrowser(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }

    private void callApi(){
        pb.setVisibility(View.VISIBLE);
        JSONObject object = new JSONObject();
        HashMap<String, String> headerParam = new HashMap<>();
        headerParam.put("Accept","application/json");
        headerParam.put("Content-Type","application/json");
        headerParam.put("Authorization", Constants.UAT_ACCESS_TOKEN);
        HashMap<String,String>param = new HashMap<>();
        RequestApi api = new RequestApi(context, new ResponseListener() {
            @Override
            public void onResponse(int requestCode, String response) {
                pb.setVisibility(View.GONE);
                ServicesModel model = new Gson().fromJson(response,ServicesModel.class);
                if (model.responseCode.equalsIgnoreCase("000")){
                    itemList.addAll(model.data);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int requestCode, String error) {
                pb.setVisibility(View.GONE);
            }
        });
        api.setHeaders(headerParam);
        api.setMethod(Request.Method.GET);
        api.requestJson(ApiList.ourServicesUrl,param,101);
    }

}
