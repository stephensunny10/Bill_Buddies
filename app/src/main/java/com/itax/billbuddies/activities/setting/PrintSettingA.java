package com.itax.billbuddies.activities.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.ActivityPrintSettingBinding;
import com.itax.billbuddies.databinding.ActivityPuchaseDetailBinding;
import com.itax.billbuddies.models.PrintSettingModel;
import com.itax.billbuddies.utils.Functions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PrintSettingA extends AppCompatActivity {
Functions functions;
ActivityPrintSettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrintSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        functions = new Functions(this);
        callApi();

    }

    private void initView(PrintSettingModel.PrintSetting data) {
        binding.imgBack.setOnClickListener(v -> {
            finish();
        });
        binding.tdsAppPurchase.setText(data.tds_applicable_purchase);
        binding.txtCessApp.setText(data.cess_applicable_sale);
        binding.tdsAppPurchase.setText(data.tds_applicable_purchase);
        binding.txtDispThroughSale.setText(data.dispatch_though_applicable_sale);
        binding.txtDispThroughPurch.setText(data.dispatch_though_applicable_purchase);
        binding.txtEwayBillAppSale.setText(data.eway_bill_applicable_sale);
        binding.txtEwayBillAppPuchase.setText(data.eway_bill_applicable_purchase);
        binding.txtTdsAppSale.setText(data.tds_applicable_sale);
        binding.tdsAppPurchase.setText(data.tds_applicable_purchase);
        if (data.use_atuthorized_signatory_image!=null) {
            Picasso.get()
                    .load(data.use_atuthorized_signatory_image)
                    .into(binding.imgUseAuthSigImg);
        }
        if (data.atuthorized_signatory_image!=null) {
            Picasso.get()
                    .load(data.atuthorized_signatory_image)
                    .into(binding.authSignImg);
        }
    }


    private void callApi(){
        functions.showLoading();
        String url =ApiList.PRINT_SETTING_URL+"?loginID="+ PaperDbManager.getLoginData().loginID+"&company_id="+"COM00000001"+"&session_fin_year="+"2022-2023";
       // String url =ApiList.PRINT_SETTING_URL+"?loginID="+"ITIC-00005161"+"&company_id="+"COM00000001"+"&session_fin_year="+"2022-2023";
        Log.d("1233", "callApi: "+url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(url, response -> {
            Log.d("api", "callApi: "+ response);
            functions.hideLoading();
            PrintSettingModel model = new Gson().fromJson(response,PrintSettingModel.class);
            PrintSettingModel.PrintSetting modelnew = new Gson().fromJson(response,PrintSettingModel.PrintSetting.class);
            if (model.success){
                Log.d("success", "callApi: "+ response);
                initView(modelnew);
            }

        }, error -> {
            Log.d("error", "callApi: "+ error);
            functions.hideLoading();
        });
        requestQueue.add(request);
    }

}
