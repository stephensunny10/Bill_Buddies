package com.itax.billbuddies.activities.supplier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.ActivityAddCustomerBinding;
import com.itax.billbuddies.databinding.ActivityAddSupplierBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;
import com.itax.billbuddies.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class AddSupplierA extends AppCompatActivity implements ResponseListener {
    ActivityAddSupplierBinding binding;
        String pin_code, customer_name, gstin, email,state,spinner_status, spinner_business_type,spinner_nature_business, spinner_tds_application,address, pan_card,session,gender,dob,mob_no,city, opening_balance,notes,credit_time,credit_limit;
        ArrayList<String> statusList = new ArrayList<>();
        ArrayList<String> nature_business_list = new ArrayList<>();
        ArrayList<String> BusinessTypeList = new ArrayList<>();
        ArrayList<String> TdsAppList = new ArrayList<>();
        Functions functions;
        ArrayAdapter statusAdapter,natureBusinessAdapter,BusinessTypeAdapter,TdsAppAdapter;
        SessionManager sessionManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityAddSupplierBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            functions = new Functions(this);
            sessionManager = new SessionManager(this);
            initView();
        }
        private void initView() {
            add_tds_Spiner();
            add_bussiness_type_Spinner();
            add_status_Spinner();
            add_nature_of_business();
            binding.txtTitle.setText("Add Supplier ");
            binding.imgBack.setOnClickListener(v->{
                finish();
            });
            binding.btnSubmit.setOnClickListener(v -> {

                if( validateInput()){
                    try {
                        call_add_customer_api();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        private void add_status_Spinner() {
            statusList.add("Active");
            statusList.add("InActive");

            statusAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,statusList);
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

        private void add_bussiness_type_Spinner() {
            BusinessTypeList.add( "Bussiness Type");
            BusinessTypeList.add(" Individual");
            BusinessTypeList.add( "Proprietorship Firm");
            BusinessTypeList.add(" One Person Company ");
            BusinessTypeList.add(" Limited liability partnership(LLP)");
            BusinessTypeList.add(" Partnership Firm");
            BusinessTypeList.add(" Private Limited Company");
            BusinessTypeList.add(" Public Limited Company");
            BusinessTypeList.add(" Section 8 Company");
            BusinessTypeList.add(" Nidhi Company ");
            BusinessTypeList.add(" Association of persons ");
            BusinessTypeList.add(" Hindu Undivided Family");
            BusinessTypeList.add(" NGO(Trust/Society)");

            BusinessTypeAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,BusinessTypeList);
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
            nature_business_list.add("Manufacturing Industry [Textiles, Handloom, Powerlooms]");
            nature_business_list.add("Manufacturing Industry [Tobacco]");
            nature_business_list.add("Manufacturing Industry [Tyre]");
            nature_business_list.add("Manufacturing Industry [Vanaspati & Edible Oils]");
            nature_business_list.add("Manufacturing Industry [Others]");
            nature_business_list.add("Trading [Chain stores]");
            nature_business_list.add("Trading [Retailers]");
            nature_business_list.add("Trading [Wholesalers]");
            nature_business_list.add("Trading [Others]");
            nature_business_list.add("Commission Agents [General Commission Agents]");
            nature_business_list.add("Builders [Builders]");
            nature_business_list.add("Builders [Estate agents]");
            nature_business_list.add("Builders [Property Developers]");
            nature_business_list.add("Builders [Others]");
            nature_business_list.add("Contractors [Civil Contractors]");
            nature_business_list.add("Contractors [Excise Contractors]");
            nature_business_list.add("Contractors [Mining Contractors]");
            nature_business_list.add("Contractors [Others]");
            nature_business_list.add("Profession hartered Accountants, Auditors, etc ]");
            nature_business_list.add("Professionals [Fashion designers]");
            nature_business_list.add("Professionals [Legal professionals]");
            nature_business_list.add("Professionals [Medical professionals]");
            nature_business_list.add("Professionals [Nursing Homes]");
            nature_business_list.add(" Other Sector");
            nature_business_list.add("Manufacturing Industry [Agro-based industries]");
            nature_business_list.add("Manufacturing Industry [Automobile and Auto parts]");
            nature_business_list.add("Manufacturing Industry [Cement]");
            nature_business_list.add("Manufacturing Industry [Diamond cutting]");
            nature_business_list.add("Manufacturing Industry [Drugs and Pharmaceuticals]");
            nature_business_list.add("Manufacturing Industry [Electronics including Computer Hardware]");
            nature_business_list.add("Manufacturing Industry [Engineering goods]");
            nature_business_list.add("Manufacturing Industry [Fertilizers, Chemicals, Paints]");
            nature_business_list.add("Manufacturing Industry [Flour & Rice Mills]");
            nature_business_list.add("Manufacturing Industry [Food Processing Units]");
            nature_business_list.add("Manufacturing Industry [Marble & Granite]");
            nature_business_list.add("Manufacturing Industry [Paper] ");
            nature_business_list.add("Manufacturing Industry [Petroleum and Petrochemicals]");
            nature_business_list.add("Manufacturing Industry [Power and energy]");
            nature_business_list.add("Manufacturing Industry [Printing & Publishing]");
            nature_business_list.add("Manufacturing Industry [Rubber]");
            nature_business_list.add("Manufacturing Industry [Steel]");
            nature_business_list.add("Professionals [Specialty hospitals]");
            nature_business_list.add("Professionals [Others]");
            nature_business_list.add("Service Sector [Advertisement agencies]");
            nature_business_list.add("Service Sector [Beauty Parlours]");
            nature_business_list.add("Service Sector [Consultancy services]");
            nature_business_list.add("Service Sector [Courier Agencies]");
            nature_business_list.add("Service Sector [Computer training/educational and coaching instit");
            nature_business_list.add("Service Sector [Forex Dealers]");
            nature_business_list.add("Service Sector [Hospitality services]");
            nature_business_list.add("Service Sector [Hotels]");
            nature_business_list.add("Service Sector [IT enabled services, BPO service providers]");
            nature_business_list.add("Service Sector [Security agencies]");
            nature_business_list.add("Service Sector [Software development agencies]");
            nature_business_list.add("Service Sector [Transporters]");
            nature_business_list.add("Service Sector [Travel agents, tour operators]");
            nature_business_list.add("Service Sector [Others]");
            nature_business_list.add(" Service Sector [Software development agencies]");
            nature_business_list.add(" Service Sector [Transporters]");
            nature_business_list.add(" Service Sector [Travel agents, tour operators]");
            nature_business_list.add(" Service Sector [Others]");
            nature_business_list.add(" Financial Service Sector [Banking Companies]");
            nature_business_list.add(" Financial Service Sector [Chit Funds]");
            nature_business_list.add(" Financial Service Sector [Financial Institutions]");
            nature_business_list.add(" Financial Service Sector [Financial service providers]");
            nature_business_list.add(" Financial Service Sector [Leasing Companies]");
            nature_business_list.add(" Financial Service Sector [Money Lenders]");
            nature_business_list.add(" Service Sector [Non-Banking Finangal C Co]");
            nature_business_list.add(" Financial Service Sector [Share Brokers, Sub- nen oi]");
            nature_business_list.add(" Financial Service Sector [Others]");
            nature_business_list.add(" Entertainment Industry [Cable TV. productions]");
            nature_business_list.add(" Entertainment Industry [Film distribution]");
            nature_business_list.add(" Entertainment Industry [Film laboratories]");
            nature_business_list.add(" Entertainment Industry [Motion Picture Producers]");
            nature_business_list.add(" Entertainment Industry [Television Channels]");
            nature_business_list.add(" Entertainment Industry [Others]");
            nature_business_list.add(" Other Sector");

            natureBusinessAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, nature_business_list);
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
        private void add_tds_Spiner() {
            TdsAppList.add("yes");
            TdsAppList.add("No");


            TdsAppAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,TdsAppList);
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
            notes = binding.etNotes.getText().toString();

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

        private void call_add_customer_api() throws JSONException {
            binding.pb.setVisibility(View.VISIBLE);
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("Content-Type", "application/json; charset=UTF-8");
            params.put("token", Constants.UAT_ACCESS_TOKEN);
            //input your API parameters
            JSONObject object = new JSONObject();
            object.put("loginID", PaperDbManager.getLoginData().loginID);//sessionManager.getloginId());
            object.put("company_id",PaperDbManager.getCompany().Company_Id); // sessionManager.getCompanyId());
            object.put("pan",customer_name);
            object.put("session_fin_year",session);
            object.put("ccode","0");
            object.put("business_type",spinner_business_type);
            object.put("nature_of_business","59");
            object.put("registration_no","U25203HR2006PTC069888");
            object.put("registration_date","2021-04-01");
            object.put("fname",customer_name);
            object.put("dob",dob);
            object.put("mobile",mob_no);
            object.put("email",email);
            object.put("city",city);
            object.put("state",state);
            object.put("address",address);
            object.put("gender",gender);
            object.put("pan",pan_card);
            object.put("gstin",gstin);
            object.put("wallet_balance","0");
            object.put("due_balance","0");
            object.put("notes",notes);
            object.put("status",spinner_status);
            object.put("credit_time",credit_time);
            object.put("tds_bill_applicable",spinner_tds_application);
            object.put("tanno","4747388737");
            object.put("date_created","2021-04-01 14:14:36");
            object.put("updatedBy","ITIC-00105928");
            object.put("date_modified","2021-04-01 14:16:04");
            object.put("tds_amount","393993");

             String url = ApiList.SUPPLIER_URL+"?loginID="+ PaperDbManager.getLoginData().loginID+"&company_id="+PaperDbManager.getCompany().Company_Id;
           // String url = ApiList.CUSTOMER_URL+"?loginID="+"ITIC-00005161"+"&company_id="+"COM00000001";
            RequestApi api = new RequestApi(this, this);
            api.requestJson(url, object, 101);
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
