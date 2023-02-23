package com.itax.billbuddies.activities.Unit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.ActivityAddUnitBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.models.ResponseModel;
import com.itax.billbuddies.utils.Functions;
import com.itax.billbuddies.utils.InputValidator;
import com.itax.billbuddies.utils.SessionManager;

import org.json.JSONObject;

public class AddUnitA extends AppCompatActivity {
    ActivityAddUnitBinding binding;
    String type,unit_name;
    SessionManager sessionManager;
    Functions functions;
    String TAG = "AddUnitA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit);
        binding = ActivityAddUnitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManager = new SessionManager(this);
        functions = new Functions(this);
        initView();
    }


    private void initView(){
        binding.btnSubmit.setOnClickListener(v->{
            if (validateInput()){
                callApi();
            }
        });
    }

    private boolean validateInput(){
        unit_name = binding.etUnitName.getText().toString();

        if (unit_name.isEmpty()){
            InputValidator.validateInput(binding.etUnitName);
            return false;
        }
        return true;
    }

    private void callApi(){
        try {
            JSONObject object = new JSONObject();
            object.put("loginID", PaperDbManager.getLoginData().loginID);
            object.put("company_id",PaperDbManager.getCompany().getCompany_Id());
            object.put("session_fin_year",sessionManager.getFinancialYear());
            object.put("parent_category_id","0");
            object.put("unitname",unit_name);

            RequestApi api = new RequestApi(this, new ResponseListener() {
                @Override
                public void onResponse(int requestCode, String response) {
                    ResponseModel model = new Gson().fromJson(response,ResponseModel.class);
                    functions.hideLoading();
                    if (model.success){
                        functions.showSuccess("Unit saved");
                        finish();
                    }
                }

                @Override
                public void onError(int requestCode, String error) {
                    Log.d(TAG, "onError: "+ error);
                    functions.hideLoading();
                }
            });
            api.requestJson(ApiList.UNIT_URL,object,101);
            functions.showLoading();
        }
        catch (Exception e){
            Log.d(TAG, "callApi: "+ e.getMessage());
        }
    }
}