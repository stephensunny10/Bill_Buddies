package com.itax.billbuddies.activities.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.databinding.ActivityResetPwdBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.R;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class ResetPwdA extends AppCompatActivity implements ResponseListener {
    ActivityResetPwdBinding binding;
    String email = "",otp = "123456",loginID = "",pwd,cnfPwd;
    Functions functions;
    String TAG = "ResetPwdA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        binding = ActivityResetPwdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        functions = new Functions(this);
        initView();
    }
    private void initView(){
        binding.layoutReset.setOnClickListener(v->{
            if (validateInput()){
                callResetPwdApi(); // do further i am having tea
            }
        });
    }

    private boolean validateInput(){
        pwd = binding.etPwd.getText().toString().trim();
        cnfPwd = binding.etPwd.getText().toString().trim();

        if (pwd.isEmpty()){
            binding.etPwd.setError("Field should not be empty");
            binding.etPwd.requestFocus();
            return false;
        }
        else if (cnfPwd.isEmpty()){
            binding.etCnfPwd.setError("Field should not be empty");
            binding.etCnfPwd.requestFocus();
            return false;
        }

        else if (!pwd.equals(cnfPwd)){
            binding.etCnfPwd.setError("Password does not match");
            binding.etCnfPwd.requestFocus();
            return false;
        }

        return true;
    }

    private void callResetPwdApi(){
        JSONObject object = new JSONObject();
        try {
            object.put("EmailID",Constants.LOGIN_ID);
            object.put("Password",pwd);
        }
        catch (Exception e){
            // error
            Log.d(TAG, "callResetPwdApi: "+ e.getMessage());
        }
        RequestApi api = new RequestApi(this,this);
        api.setMethod(Request.Method.POST);
        api.setRequestBody(object.toString());
        api.requestJson(ApiList.ResetPassUrl,object,101);
        functions.showLoading();
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

    private void moveToLogin(){
        startActivity(new Intent(this, LoginA.class));
    }


    private void extractResponse(String response){
        try {
            JSONObject object = new JSONObject(response);
            if (object.getString("responseCode").equalsIgnoreCase("000")){
                Toasty.success(this,object.getString("responseMessage"),Toasty.LENGTH_SHORT).show();
                moveToLogin();
                finish(); // test now everything is done
            }
            else {
                Toasty.error(this,response,Toasty.LENGTH_SHORT,true).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toasty.error(this,e.getMessage(),Toasty.LENGTH_SHORT,true).show();
        }
    }

}