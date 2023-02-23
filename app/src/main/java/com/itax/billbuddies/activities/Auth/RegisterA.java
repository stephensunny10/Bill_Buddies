package com.itax.billbuddies.activities.Auth;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.models.RegisterModel;
import com.itax.billbuddies.R;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterA extends AppCompatActivity implements ResponseListener {

    Spinner business_type_spinner,user_type_spinner;
    EditText full_name_et,email_et,mobile_et,pan_et,address_et,city_et,state_et,pwd_et,cnfPwd_et;
    RadioButton male_rb,female_rb;
    ArrayList<String>businessTypeList = new ArrayList<>();
    ArrayList<String>userTypeList = new ArrayList<>();
    ArrayAdapter businessTypeAdapter,userTypeAdapter;
    LinearLayout registerButton;
    String  businessType="",userType="",fullName,email,mobile,panNo,address,city,state,pwd,cnfPwd,gender="";
    TextView login;
    Functions functions;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        functions = new Functions(this);
        findView();
    }
    
    private void findView(){
        business_type_spinner = findViewById(R.id.spinner_business_type);
        user_type_spinner = findViewById(R.id.spinner_user_type);
        email_et= findViewById(R.id.et_email);
        mobile_et = findViewById(R.id.et_mobile);
        full_name_et= findViewById(R.id.et_full_name);
        pan_et= findViewById(R.id.et_pan);
        address_et = findViewById(R.id.et_address);
        city_et= findViewById(R.id.et_city);
        state_et = findViewById(R.id.et_state);
        pwd_et= findViewById(R.id.et_pwd);
        cnfPwd_et = findViewById(R.id.et_cnf_pwd);
        registerButton = findViewById(R.id.btn_register);
        login= findViewById(R.id.txt_login);
        login.setOnClickListener(v -> {
            moveNext();
        });
        registerButton.setOnClickListener(v -> {
            try {
                if(validateInput()){
                callRegisterApi();}
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        addSpinnerItem();
    }
    private boolean validateInput(){
        businessType = business_type_spinner.getSelectedItem().toString();
        if (businessType.isEmpty()){
            business_type_spinner.requestFocus();
            return false;
        }
        userType = user_type_spinner.getSelectedItem().toString();

        if (userType.isEmpty()){
            user_type_spinner.requestFocus();
            return false;
        }
        fullName = full_name_et.getText().toString().trim();

        if (fullName.isEmpty()){
            full_name_et.setError("Field should not be empty");
            full_name_et.requestFocus();
            return false;
        }
        mobile = mobile_et.getText().toString().trim();

        if (mobile.isEmpty()){
            mobile_et.setError("Field should not be empty");
            mobile_et.requestFocus();
            return false;
        }
        email = email_et.getText().toString().trim();

        if (email.isEmpty()){
            email_et.setError("Field should not be empty");
            email_et.requestFocus();
            return false;
        }
        panNo = pan_et.getText().toString().trim();

        if (panNo.isEmpty()){
            pan_et.setError("Field should not be empty");
            pan_et.requestFocus();
            return false;
        }
        city = city_et.getText().toString().trim();

        if (city.isEmpty()){
            city_et.setError("Field should not be empty");
            city_et.requestFocus();
            return false;
        }

        pwd = pwd_et.getText().toString().trim();

        if (pwd.isEmpty()){
           pwd_et.setError("Field should not be empty");
            pwd_et.requestFocus();
            return false;
        }
        cnfPwd  = cnfPwd_et.getText().toString().trim();

        if (cnfPwd.isEmpty()){
            cnfPwd_et.setError("Field should not be empty");
            cnfPwd_et.requestFocus();
            return false;
        }

        return true;

    }
    private void addSpinnerItem(){
        businessTypeList.add("Select Business Type");
        businessTypeList.add("Individual");
        businessTypeList.add("Company");
        userTypeList.add("Select User Type");
        userTypeList.add("Customer");
        userTypeList.add("Merchant");

        businessTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,businessTypeList);
        userTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,userTypeList);
        business_type_spinner.setAdapter(businessTypeAdapter);
        user_type_spinner.setAdapter(userTypeAdapter);

        business_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    businessType = businessTypeList.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        user_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    userType = userTypeList.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void callRegisterApi() throws JSONException {
        functions.showLoading();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json; charset=UTF-8");
        params.put("token", Constants.UAT_ACCESS_TOKEN);
        //input your API parameters
        JSONObject object = new JSONObject();
        object.put("EmailID",email_et.getText().toString());
        object.put("Password",pwd_et.getText().toString());
        object.put("MobileNo",mobile_et.getText().toString());
        object.put("FullName",full_name_et.getText().toString());
        object.put("user_type",user_type_spinner.getSelectedItem().toString());
        object.put("Gender","male");
        object.put("PAN",pan_et.getText().toString());
        object.put("Address",address_et.getText().toString());
        object.put("City",city_et.getText().toString());
        object.put("State",state_et.getText().toString());
        object.put("bussiness_type",businessType);
        RequestApi api = new RequestApi(this,this);
        api.requestJson(ApiList.RegisterUrl,object,101);
    }
    @Override
    public void onResponse(int requestCode, String response) {
        functions.hideLoading();
        verifyAuth(response);
    }

    @Override
    public void onError(int requestCode, String error) {

    }
    private void verifyAuth(String response){
        RegisterModel model = new Gson().fromJson(response,RegisterModel.class);
        if (model.responseCode.equals("000")){
            functions.showSuccess("Registered Successfully");
           moveNext();
        }}
        private void moveNext(){
            startActivity(new Intent(this, LoginA.class));
            finish();
        }

}