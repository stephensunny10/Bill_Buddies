package com.itax.billbuddies.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.models.ITaxCompanyItem;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ITaxCompanyList implements ResponseListener {
    Context context;
    String TAG = "ITaxCompanyList";
    PaperDbManager dbManger;
    ArrayList<ITaxCompanyItem>itemList = new ArrayList<>();
    Functions functions;

    public ITaxCompanyList(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        Gson gson = new Gson();
        functions = new Functions(context);
    }

    public void getCompanyList(){
        HashMap<String, String> headerParam = new HashMap<>();
        headerParam.put("Accept","application/json");
        headerParam.put("Content-Type","application/json");
        headerParam.put("Authorization", Constants.UAT_ACCESS_TOKEN);
        JSONObject object = new JSONObject();
        HashMap<String,String>param = new HashMap<>();
        RequestApi api = new RequestApi(context,this); //https://uat.itaxinfo.com/Api/Service/GetUserCompanyList?LoginID=ITIC-00000007
        api.setMethod(Request.Method.GET);
        api.setHeaders(headerParam);
        api.requestJson(ApiList.ITAX_COMPANY + "?LoginID=" + PaperDbManager.getLoginData().loginID,param,101);
        functions.showLoading();
        String url = "https://uat.itaxinfo.com/Api/Service/GetCompanyList?LoginID=ITIC-00000007";
    }

    @Override
    public void onResponse(int requestCode, String response) {
        Log.d(TAG, "onResponse: " + response);
        extractCompanyList(response);
        functions.hideLoading();
    }

    @Override
    public void onError(int requestCode, String error) {
        Log.d(TAG, "onError: "+ error);
        functions.hideLoading();
    }

    private void extractCompanyList(String response){
        try {
            JSONObject object = new JSONObject(response);
            if (object.getString("ResponseCode").equalsIgnoreCase("000")){

                JSONArray array = object.getJSONArray("CompanyList");
                for (int i=0; i<array.length(); i++){
                    JSONObject childObject = array.getJSONObject(i);
                    Gson gson = new Gson();
                    ITaxCompanyItem item = gson.fromJson(childObject.toString(),ITaxCompanyItem.class);
                    itemList.add(item);
                }
                dbManger.setAllCompanyList(itemList);
                showData();
            }
            
            if (!itemList.isEmpty()){
                PaperDbManager.setCompany(itemList.get(0));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void showData(){
        Gson gson = new Gson();
        String company = gson.toJson(dbManger.getAllCompanyList().get(0));
        Log.d(TAG, "showData: "+ company);
    }

    private void callApi(){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest("", response -> {
            Log.d(TAG, "onResponse: "+ response);
            functions.hideLoading();
        }, error -> {
            Log.d(TAG, "getCompanyList: "+ error);
            functions.hideLoading();
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String>headerParam = new HashMap<>();
                //headerParam.put("Accept","application/json");
                headerParam.put("Authorization", Constants.UAT_ACCESS_TOKEN);
                return super.getHeaders();
            }
        };
        requestQueue.add(request);
    }
}


