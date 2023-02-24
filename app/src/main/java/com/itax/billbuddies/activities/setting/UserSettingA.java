package com.itax.billbuddies.activities.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.itax.billbuddies.R;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.databinding.ActivityAddCustomerBinding;
import com.itax.billbuddies.databinding.ActivityUserSettingBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import org.json.JSONObject;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class UserSettingA extends AppCompatActivity implements ResponseListener {
ActivityUserSettingBinding binding;
String purchase_invoice_prefix,purchase_invoice_last_no,purchase_return_invoice_prefix,sales_invoice_prefix,sales_invoice_last_no,sales_return_invoice_prefix,
 sales_performa_invoice_last_no,sales_retail_invoice_last_no,sales_export_invoice_last_no,sales_delivery_ch_invoice_last_no,sales_rcm_invoice_last_no,sales_stock_transfer_invoice_last_no,
        sales_fixed_asset_invoice_last_no, use_authorised_sig_image,terms_condition,invoice_footer_text,purchase_return_invoice_last_no,user_setting;
Integer item_wise_discount,bill_wise_discount, tds_applicable,cess_applicable,eway_bill_applicable,
        dispatch_though_applicable,do_you_want_total_out_sale,do_you_want_notification;
Functions functions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        functions = new Functions(this);
        initView();
    }

    private void initView() {
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
        binding.btnSubmit.setOnClickListener(v -> {
            if( validateInput()){
                call_user_update_api();
            }
        });

    }
        private boolean validateInput() {
            purchase_invoice_prefix = binding.etPurchaseInvoicePrefix.getText().toString();
            purchase_invoice_last_no = binding.etPurchaseInvoiceLastNo.getText().toString();
            purchase_return_invoice_prefix = binding.etPurchReturnInvoicePrefix.getText().toString();
            purchase_return_invoice_last_no = binding.etPurReturnInvLastNo.getText().toString();
            sales_invoice_prefix = binding.etSalesInvoicePrefix.getText().toString();
            sales_invoice_last_no = binding.etSalesInvoiceLastNo.getText().toString();
            sales_performa_invoice_last_no = binding.etSalesPerfInvoiceLastNo.getText().toString();
            sales_retail_invoice_last_no = binding.etSalesRetailInvoiceLastNo.getText().toString();
            sales_export_invoice_last_no = binding.etSalesExportInvoiceLastNo.getText().toString();
            sales_delivery_ch_invoice_last_no = binding.etSalesDeliveryChallan.getText().toString();
            sales_rcm_invoice_last_no = binding.etSalesRcmInvoice.getText().toString();
            sales_stock_transfer_invoice_last_no = binding.etSalesStockTransfer.getText().toString();
            sales_fixed_asset_invoice_last_no= binding.etSalesFixedAsset.getText().toString();
            use_authorised_sig_image = binding.etUseAuthorisedSigImage.getText().toString();
            use_authorised_sig_image = binding.etAuthorisedSigImage.getText().toString();
            terms_condition = binding.etTermsCondition.getText().toString();
            invoice_footer_text = binding.etInvoiceFooterText.getText().toString();
            item_wise_discount = binding.radioGroupItem.getCheckedRadioButtonId();
            bill_wise_discount = binding.radioGroupBill.getCheckedRadioButtonId();
            tds_applicable = binding.radioGroupTds.getCheckedRadioButtonId();
            cess_applicable = binding.radioGroupCess.getCheckedRadioButtonId();
            eway_bill_applicable = binding.radioGroupEway.getCheckedRadioButtonId();
            dispatch_though_applicable = binding.radioGroupDispApp.getCheckedRadioButtonId();
            do_you_want_total_out_sale = binding.radioGroupOutsale.getCheckedRadioButtonId();
            do_you_want_notification = binding.radioGroupNotification.getCheckedRadioButtonId();

            /*if (pan_card.isEmpty()) {
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

        */return true;
    }

        private void call_user_update_api(){
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
            api.requestJson(ApiList.addCustomerUrl, object, 101);
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
