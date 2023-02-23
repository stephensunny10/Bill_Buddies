package com.itax.billbuddies.activities.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.databinding.ActivityOtpBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.R;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class OtpA extends AppCompatActivity implements ResponseListener {
    ActivityOtpBinding binding;
    String otp = "";
    Functions functions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        functions = new Functions(this);

        initView();
    }

    private void initView(){
        binding.btnVerifyOtp.setOnClickListener(v->{
            if (!binding.pinView.getText().toString().isEmpty()){
                if (binding.pinView.getText().toString().equals(Constants.SENT_OTP)){
                    moveToResetPwd();
                }
            }
        });

        binding.txtResendOtp.setOnClickListener(v->{
            sendOtp();
        });
    }

    private void moveToResetPwd(){
        startActivity(new Intent(this, ResetPwdA.class));
    }




    private void sendOtp(){
        HashMap<String,String> param = new HashMap<>();
        JSONObject object = new JSONObject();
        RequestApi api = new RequestApi(this,this);
        api.setMethod(Request.Method.GET);
        api.requestJsonGet(ApiList.ForgotPasswordUrl + "?LoginId="+ Constants.LOGIN_ID +"&OTP=" + Constants.SENT_OTP,object,101);
        functions.showLoading();
    }

    private void moveNext(){
        startActivity(new Intent(this, OtpA.class));
    }


    @Override
    public void onResponse(int requestCode, String response) {
        functions.hideLoading();
        extractResponse(response);
    }

    @Override
    public void onError(int requestCode, String error) {
        functions.hideLoading();
    }

    private void extractResponse(String response){
        try {
            JSONObject object = new JSONObject(response);
            if (object.getString("ResponseCode").equalsIgnoreCase("000")){
                Constants.LOGIN_ID = object.getString("LoginId");
                functions.showSuccess(object.getString("ResponseMessage"));
                moveNext();
            }
            else {
                Toasty.error(this,"something went wrong",Toasty.LENGTH_SHORT,true).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toasty.error(this,e.getMessage(),Toasty.LENGTH_SHORT,true).show();
        }
    }
}