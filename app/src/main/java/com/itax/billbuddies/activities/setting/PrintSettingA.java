package com.itax.billbuddies.activities.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.databinding.ActivityPrintSettingBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.models.PrintSettingModel;
import com.itax.billbuddies.utils.Functions;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PrintSettingA extends AppCompatActivity implements ResponseListener {
    Functions functions;
    ActivityPrintSettingBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrintSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        functions = new Functions(this);
        initView();
        cal_print_setting_api();
    }

    private void initView() {
        binding.imgBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void setData(PrintSettingModel data) {
        Log.d("myene", "initView: " + data.data.get(0).tds_applicable_sale);
        if (data.data.get(0).tds_applicable_sale.equals("Y")) {
            binding.noTdsAppSale.setSelected(true);
        } else binding.noTdsAppSale.setSelected(true);
      /*  binding..setText(data.tds_applicable_purchase);
        binding.txtCessApp.setText(data.cess_applicable_sale);
        binding.tdsAppPurchase.setText(data.tds_applicable_purchase);
        binding.txtDispThroughSale.setText(data.dispatch_though_applicable_sale);
        binding.txtDispThroughPurch.setText(data.dispatch_though_applicable_purchase);
        binding.txtEwayBillAppSale.setText(data.eway_bill_applicable_sale);
        binding.txtEwayBillAppPuchase.setText(data.eway_bill_applicable_purchase);
        binding.txtTdsAppSale.setText(data.tds_applicable_sale);
        binding.tdsAppPurchase.setText(data.tds_applicable_purchase);*/
     /*   if (data.use_atuthorized_signatory_image!=null) {
            Picasso.get()
                    .load(data.use_atuthorized_signatory_image)
                    .into(binding.image);
        }*/
    }

    private void cal_print_setting_api() {
        // String url = ApiList.PRINT_SETTING_URL + "?loginID=" + PaperDbManager.getLoginData().loginID + "&company_id=" + "COM00000001" + "&session_fin_year=" + "2022-2023";
        String url = ApiList.PRINT_SETTING_URL + "?loginID=" + "ITIC-00005161" + "&company_id=" + "COM00000001" + "&session_fin_year=" + "2022-2023";

        HashMap<String, String> param = new HashMap<>();
        JSONObject object = new JSONObject();
        RequestApi api = new RequestApi(this, this);
        api.setMethod(Request.Method.GET);
        api.requestJsonGet(url, object, 101);
        functions.showLoading();
    }

    @Override
    public void onResponse(int requestCode, String response) {
        functions.hideLoading();
        PrintSettingModel model = new Gson().fromJson(response, PrintSettingModel.class);
        Log.d("data", "verifyAuth: " + model);
        setData(model);
        verifyAuth(response);
    }

    @Override
    public void onError(int requestCode, String error) {
        functions.hideLoading();
        Log.d("error", "onError: " + error);

    }

    private void verifyAuth(String response) {
    }

}
