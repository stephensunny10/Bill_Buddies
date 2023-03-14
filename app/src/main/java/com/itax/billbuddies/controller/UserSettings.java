package com.itax.billbuddies.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.ActivityUserSettingBinding;
import com.itax.billbuddies.models.SalesModel;
import com.itax.billbuddies.models.UserSettingModel;
import com.itax.billbuddies.utils.SessionManager;

public class UserSettings {
    Context context;
    ActivityUserSettingBinding binding;
    String TAG = "UserSettings";
    SessionManager sessionManager;

    public UserSettings(Context context, ActivityUserSettingBinding binding) {
        this.context = context;
        this.binding = binding;
        init();
    }

    private void init(){
        sessionManager = new SessionManager(context);
        callSettingsApi();
    }

    private void callSettingsApi(){
        String url = ApiList.USER_SETTINGS_URL + "?loginID=" + PaperDbManager.getLoginData().loginID + "&company_id=" + PaperDbManager.getCompany().Company_Id + "&session_fin_year="+ sessionManager.getFinancialYear();
        //url = ApiList.USER_SETTINGS_URL+"?loginID="+"ITIC-00001596"+"&company_id="+"COM00000179" + "&session_fin_year="+ "2022-2023";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(url, response -> {
            Log.d(TAG, "UserSettingModel: "+ response);
            UserSettingModel model = new Gson().fromJson(response,UserSettingModel.class);
            if (model.success && !model.data.isEmpty()){
                setData(model.data.get(0));
            }
        }, error -> {
            Log.d(TAG, "UserSettingModel: "+ error);
        });
        requestQueue.add(request);
    }

    private void setData(UserSettingModel.UserSettingsItem item){
        binding.etPurchaseInvoicePrefix.setText(item.purchase_invoice_prefix);
        binding.etPurchaseInvoiceLastNo.setText(item.purchase_invoice_last_no);
        binding.etPurchReturnInvoicePrefix.setText(item.purchase_return_invoice_prefix);
        binding.etPurReturnInvLastNo.setText(item.purchase_return_invoice_last_no);
        binding.etSalesInvoicePrefix.setText(item.sales_invoice_prefix);
        binding.etSalesInvoiceLastNo.setText(item.sales_invoice_last_no);
        binding.etSalesPerfInvoiceLastNo.setText(item.sales_performa_invoice_last_no);
        binding.etSalesRetailInvoiceLastNo.setText(item.sales_retail_invoice_last_no);
        binding.etSalesExportInvoiceLastNo.setText(item.sales_export_invoice_last_no);
        binding.etSalesDeliveryChallan.setText(item.sales_dc_invoice_last_no);
        binding.etSalesRcmInvoice.setText(item.sales_rcm_invoice_last_no);
        binding.etSalesStockTransfer.setText(item.sales_st_invoice_last_no);
        binding.etSalesFixedAsset.setText(item.sales_fa_invoice_last_no);
        binding.etLicenseExpiry.setText(item.license_expiry);
        // image
        // image
        binding.etTermsCondition.setText(item.terms_conditions);
        binding.etInvoiceFooterText.setText(item.invoice_footer_text);

        if (item.item_wise_discount != null){
            if (item.bill_wise_discount.equalsIgnoreCase("Y")){
                binding.rbItemWiseYes.setChecked(true);
            }
            else {
                binding.rbItemWiseNo.setChecked(true);
            }
        }
        if (item.bill_wise_discount != null){
            if (item.bill_wise_discount.equalsIgnoreCase("Y")){
               binding.rbBillWiseYes.setChecked(true);
            }
            else {
                binding.rbBillWiseNo.setChecked(true);
            }
        }

        if (item.use_atuthorized_signatory_image != null){
            if (item.bill_wise_discount.equalsIgnoreCase("1")){
                binding.rbAuthorizedImageYes.setChecked(true);
            }
            else {
                binding.rbAuthorizedImageNo.setChecked(true);
            }
        }

        Log.d(TAG, "setData: "+ ApiList.BILL_BUDDIES_IMAGE_URL + item.atuthorized_signatory_image);
        Glide.with(context).load(ApiList.BILL_BUDDIES_IMAGE_URL + item.atuthorized_signatory_image).error(R.drawable.ic_image).into(binding.imgSelectAuthImage);
    }
}
