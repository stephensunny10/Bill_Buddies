package com.itax.billbuddies.activities.TaxClass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.ActivityAddTaxClassBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.models.ResponseModel;
import com.itax.billbuddies.utils.Functions;
import com.itax.billbuddies.utils.InputValidator;
import com.itax.billbuddies.utils.SessionManager;

import org.json.JSONObject;

public class AddTaxClassA extends AppCompatActivity {
    ActivityAddTaxClassBinding binding;
    String type,tax_name,tax_percentage;
    SessionManager sessionManager;
    Functions functions;
    String TAG = "AddBrandA";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tax_class);
        binding = ActivityAddTaxClassBinding.inflate(getLayoutInflater());
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
        tax_name = binding.etTaxName.getText().toString();
        tax_percentage = binding.etTaxPercentage.getText().toString();

        if (tax_name.isEmpty()){
            InputValidator.validateInput(binding.etTaxName);
            return false;
        }
        else if (tax_percentage.isEmpty()){
            InputValidator.validateInput(binding.etTaxPercentage);
            return false;
        }
        return true;
    }

    private void callApi(){
        try {
            JSONObject object = new JSONObject();
            object.put("loginID", PaperDbManager.getLoginData().loginID);
            object.put("company_id",PaperDbManager.getCompany().getCompany_Id());
            object.put("session_fin_year",sessionManager.getAssessmentYear());
            object.put("tax_name",tax_name);
            object.put("tax_percentage",tax_percentage);

            RequestApi api = new RequestApi(this, new ResponseListener() {
                @Override
                public void onResponse(int requestCode, String response) {
                    ResponseModel model = new Gson().fromJson(response,ResponseModel.class);
                    functions.hideLoading();
                    if (model.success){
                        functions.showSuccess("Tax class saved");
                        finish();
                    }
                }

                @Override
                public void onError(int requestCode, String error) {
                    Log.d(TAG, "onError: "+ error);
                    functions.hideLoading();
                }
            });
            api.requestJson(ApiList.TAX_CLASS_URL,object,101);
            functions.showLoading();
        }
        catch (Exception e){
            Log.d(TAG, "callApi: "+ e.getMessage());
        }
    }

}