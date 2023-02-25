package com.itax.billbuddies.activities.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.databinding.ActivityAddCustomerBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class AddCustomerA extends AppCompatActivity implements ResponseListener {
    ActivityAddCustomerBinding binding;
    String pin_code, customer_name, gstin, email,state,spinner_status, spinner_business_type,spinner_nature_business, spinner_tds_application,address, pan_card,session,gender,dob,mob_no,city, opening_balance,notes,credit_time,credit_limit;
    ArrayList<String> statusList = new ArrayList<>();
    ArrayList<String> nature_business_list = new ArrayList<>();
    ArrayList<String> BusinessTypeList = new ArrayList<>();
    ArrayList<String> TdsAppList = new ArrayList<>();
    Functions functions;
    ArrayAdapter statusAdapter,natureBusinessAdapter,BusinessTypeAdapter,TdsAppAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        functions = new Functions(this);
        initView();
    }
    private void initView() {
        addtdsappSpiner();
        addbussinesstypeSpiner();
        addstatusSpiner();
        addbusinesTypeSpineer();
        binding.txtTitle.setText("Add Customer ");
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
        binding.btnSubmit.setOnClickListener(v -> {
            if( validateInput()){
                call_add_customer_api();
            }
        });

    }
    private void addstatusSpiner() {
        statusList.add("2017-2018");
        statusList.add("2019-2020");
        statusList.add("2021-2022");
        statusList.add("2022-2023");

        statusAdapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,statusList);
        binding.spinnerStatus.setAdapter(statusAdapter);

        binding.spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                session = statusList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void addbusinesTypeSpineer() {
        BusinessTypeList.add("Male");
        BusinessTypeList.add("Female");
        BusinessTypeList.add("Transgender");

        BusinessTypeAdapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,BusinessTypeList);
        binding.spinnerBusinessType.setAdapter(BusinessTypeAdapter);

        binding.spinnerBusinessType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = BusinessTypeList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void addbussinesstypeSpiner() {
        BusinessTypeList.add("2017-2018");
        BusinessTypeList.add("2019-2020");
        BusinessTypeList.add("2021-2022");
        BusinessTypeList.add("2022-2023");

        BusinessTypeAdapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,BusinessTypeList);
        binding.spinnerBusinessType.setAdapter(BusinessTypeAdapter);

        binding.spinnerBusinessType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_business_type = BusinessTypeList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void add_nature_of_business() {
        nature_business_list.add("2017-2018");
        nature_business_list.add("2019-2020");
        nature_business_list.add("2021-2022");
        nature_business_list.add("2022-2023");

        natureBusinessAdapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1, nature_business_list);
        binding.spinnerNatureBusiines.setAdapter(natureBusinessAdapter);

        binding.spinnerNatureBusiines.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_nature_business = nature_business_list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void addtdsappSpiner() {
        TdsAppList.add("2017-2018");
        TdsAppList.add("2019-2020");
        TdsAppList.add("2021-2022");
        TdsAppList.add("2022-2023");

        TdsAppAdapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,TdsAppList);
        binding.spinnerTdsApplication.setAdapter(TdsAppAdapter);

        binding.spinnerTdsApplication.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_tds_application = TdsAppList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean validateInput() {
        pan_card = binding.etPanCard.getText().toString();
        customer_name = binding.etCustomerName.getText().toString();
        mob_no = binding.etPhoneNo.getText().toString();
        spinner_business_type = binding.spinnerBusinessType.getSelectedItem().toString();
        spinner_status = binding.spinnerStatus.getSelectedItem().toString();
        spinner_nature_business = binding.spinnerNatureBusiines.getSelectedItem().toString();
        spinner_tds_application = binding.spinnerTdsApplication.getSelectedItem().toString();
        pin_code = binding.etPincode.getText().toString();
        state = binding.etState.getText().toString();
        city = binding.etCity.getText().toString();
        address = binding.etAddress.getText().toString();
        opening_balance = binding.etOpeningBalance.getText().toString();
        credit_time = binding.etCreditTime.getText().toString();
        credit_limit = binding.etCreditLimited.getText().toString();

        if (pan_card.isEmpty()) {
            binding.etPanCard.setError("Please enter pancard");
            binding.etPanCard.requestFocus();
            return false;}
            if (customer_name.isEmpty()) {
                binding.etCustomerName.setError("Please enter customer name");
                binding.etCustomerName.requestFocus();
                return false;
            }
            if (mob_no.isEmpty()) {
                binding.etPhoneNo.setError("Please enter mobile number");
                binding.etPhoneNo.requestFocus();
                return false;

            }
            if (pin_code.isEmpty()) {
                binding.etPincode.setError("Please enter pincode");
                binding.etPincode.requestFocus();
                return false;

            }
            if (state.isEmpty()) {
                binding.etState.setError("Please enter state");
                binding.etState.requestFocus();
                return false;
            }
            if (city.isEmpty()) {
                binding.etCity.setError("Please enter city");
                binding.etCity.requestFocus();
                return false;
            }
            if (address.isEmpty()) {
                binding.etAddress.setError("Please enter address");
                binding.etAddress.requestFocus();
                return false;
            }
            if (opening_balance.isEmpty()) {
                binding.etOpeningBalance.setError("Please enteropening balance");
                binding.etOpeningBalance.requestFocus();
                return false;
            }
            if (credit_limit.isEmpty()) {
                binding.etCreditLimited.setError("Please enter credit limit");
                binding.etCreditLimited.requestFocus();
                return false;
            }
            if (credit_time.isEmpty()) {
                binding.etCreditTime.setError("Please enter credit time");
                binding.etCreditTime.requestFocus();
                return false;
            }
            if (notes.isEmpty()) {
                binding.etNotes.setError("Please enter notes");
                binding.etNotes.requestFocus();
                return false;
            }
            return true;
    }

    private void call_add_customer_api(){
        binding.pb.setVisibility(View.VISIBLE);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json; charset=UTF-8");
        params.put("token", Constants.UAT_ACCESS_TOKEN);
        //input your API parameters
        JSONObject object = new JSONObject();
       /* object.put("LoginId", "ITIC-00000007");//sessionManager.getloginId());
        object.put("Company_Id","COM00000023"); // sessionManager.getCompanyId());
        object.put("name",customer_name);
        object.put("session_fin_year",session);
        object.put("father_name",father_name);
        object.put("mother_name",mother_name);
        object.put("pan_card",pancard);
        object.put("gender",gender);
        object.put("mobile_number",mob_no);
        object.put("email","anjalik@gmail.com");
        object.put("dob",dob);
        object.put("doj",doj);
        object.put("pin",pincode);
        object.put("city",city);
        object.put("state",state);
        object.put("address",address);*/

        RequestApi api = new RequestApi(this, this);
       // api.requestJson(ApiList.addCustomerUrl, object, 101);
    }
    @Override
    public void onResponse(int requestCode, String response) {
        binding.pb.setVisibility(View.GONE);
        verifyAuth(response);
    }

    @Override
    public void onError(int requestCode, String error) {

    }
    private void verifyAuth(String response) {
        // RegisterModel model = new Gson().fromJson(response,RegisterModel.class);
        Toasty.success(this, "Add Customer").show();
        //functions.showSuccess("Profile Picture Uploaded");

    }
}
