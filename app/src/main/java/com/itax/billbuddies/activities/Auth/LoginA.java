package com.itax.billbuddies.activities.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.ActivityLoginBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.MainActivity;
import com.itax.billbuddies.models.LoginModel;
import com.itax.billbuddies.R;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;
import com.itax.billbuddies.utils.SessionManager;

import org.json.JSONObject;
import java.util.HashMap;

public class LoginA extends AppCompatActivity implements ResponseListener{
    ActivityLoginBinding binding;
    String TAG="login",email, pwd;
    Functions functions;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sessionManager = new SessionManager(this);
        functions = new Functions(this);

        initView();
    }

    private void initView(){
        binding.layoutLogin.setOnClickListener(v->{
            if (validateInput()){
                callLoginApi();
            }
        });
        binding.txtForgotPwd.setOnClickListener(v->{
            moveToForgotPwd();
        });
        binding.txtRegister.setOnClickListener(v->{
            moveToRegister();
        });
    }

    private boolean validateInput(){
        email = binding.etEmail.getText().toString().trim();
        pwd = binding.etPwd.getText().toString().trim();

        if (email.isEmpty()){
            binding.etEmail.setError("Field should not be empty");
            binding.etEmail.requestFocus();
            return false;
        }

        if (pwd.isEmpty()){
            binding.etPwd.setError("Field should not be empty");
            binding.etPwd.requestFocus();
            return false;
        }
        return true;
    }
    private void callLoginApi(){
        functions.showLoading();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json; charset=UTF-8");
        params.put("token", Constants.UAT_ACCESS_TOKEN);
            //input your API parameters
        JSONObject object = new JSONObject();
        /*
        * company@itaxinfo.com
        * Rajpal@123
        * use this detail to login
        * ok on login success response code will be 000  checked? wait*/
        try {
            object.put("LoginID",binding.etEmail.getText().toString());
            object.put("Password",binding.etPwd.getText().toString());
        }
        catch (Exception e){
            Log.d(TAG, "callLoginApi: "+ e.getMessage());
        }
        RequestApi api = new RequestApi(this,this);
        api.requestJson(ApiList.LoginUrl,object,101);
    }


    private void moveNext(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void moveToRegister(){
        startActivity(new Intent(this, RegisterA.class));
        finish();
    }

    private void moveToForgotPwd(){
        startActivity(new Intent(this, ForgotPwdA.class));
        finish();
    }

    @Override
    public void onResponse(int requestCode, String response) {
        functions.hideLoading();
        verifyAuth(response);
    }

    @Override
    public void onError(int requestCode, String error) {
        functions.hideLoading();
        Log.d(TAG, "onError: "+ error);
    }

    private void verifyAuth(String response){
        LoginModel model = new Gson().fromJson(response,LoginModel.class);
        if (model.responseCode.equals("000")){
            functions.showSuccess("Login success");
            PaperDbManager.setLoginData(model);
            sessionManager.setLoggedIn(true);
            moveNext();
        }
        else {
            functions.showError("Invalid credential");
        }
    }
}