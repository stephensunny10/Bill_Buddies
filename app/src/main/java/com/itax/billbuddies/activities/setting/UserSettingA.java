package com.itax.billbuddies.activities.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.databinding.ActivityAddCustomerBinding;
import com.itax.billbuddies.databinding.ActivityUserSettingBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.models.ResponseModel;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import org.json.JSONException;
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
                try {
                    call_user_update_api();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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


        if (purchase_return_invoice_prefix.isEmpty()) {
            binding.etPurchaseInvoicePrefix.setError("Please enter puchase invoice prefix");
            binding.etPurchaseInvoicePrefix.requestFocus();
            return false;}
        if (purchase_return_invoice_last_no.isEmpty()) {
            binding.etPurchaseInvoiceLastNo.setError("Please enter puchase invoice last no");
            binding.etPurchaseInvoiceLastNo.requestFocus();
            return false;
        }
        if (purchase_return_invoice_prefix.isEmpty()) {
            binding.etPurchaseInvoicePrefix.setError("Please enter purchase return invoice prefix");
            binding.etPurchaseInvoicePrefix.requestFocus();
            return false;

        }
        if (purchase_return_invoice_last_no.isEmpty()) {
            binding.etPurReturnInvLastNo.setError("Please enter purchase return last no");
            binding.etPurReturnInvLastNo.requestFocus();
            return false;

        }
        if (sales_invoice_prefix.isEmpty()) {
            binding.etSalesInvoicePrefix.setError("Please sales invoice prefix");
            binding.etSalesInvoicePrefix.requestFocus();
            return false;
        }
        if (sales_invoice_last_no.isEmpty()) {
            binding.etSalesInvoiceLastNo.setError("Please enter sales invoice last no");
            binding.etSalesInvoiceLastNo.requestFocus();
            return false;
        }
        if (sales_performa_invoice_last_no.isEmpty()) {
            binding.etSalesPerfInvoiceLastNo.setError("Please enter sales performa invoice last no");
            binding.etSalesPerfInvoiceLastNo.requestFocus();
            return false;
        }
        if (sales_fixed_asset_invoice_last_no.isEmpty()) {
            binding.etSalesFixedAsset.setError("Please entersales fixed asset");
            binding.etSalesFixedAsset.requestFocus();
            return false;
        }
        if (sales_stock_transfer_invoice_last_no.isEmpty()) {
            binding.etSalesStockTransfer.setError("Please enter stock transfer");
            binding.etSalesStockTransfer.requestFocus();
            return false;
        }
        if (sales_retail_invoice_last_no.isEmpty()) {
            binding.etSalesRetailInvoiceLastNo.setError("Please enter sales retailer");
            binding.etSalesRetailInvoiceLastNo.requestFocus();
            return false;
        }
        if (sales_export_invoice_last_no.isEmpty()) {
            binding.etSalesExportInvoiceLastNo.setError("Please enter sales export invoice");
            binding.etSalesExportInvoiceLastNo.requestFocus();
            return false;
        }
        if (sales_delivery_ch_invoice_last_no.isEmpty()) {
            binding.etSalesDeliveryChallan.setError("Please enter sales delivery challan");
            binding.etSalesDeliveryChallan.requestFocus();
            return false;
        }

        if (sales_rcm_invoice_last_no.isEmpty()) {
            binding.etSalesRcmInvoice.setError("Please enter saled rcm invoice");
            binding.etSalesRcmInvoice.requestFocus();
            return false;
        }

        if (sales_fixed_asset_invoice_last_no.isEmpty()) {
            binding.etSalesFixedAsset.setError("Please enter sales fixed");
            binding.etSalesFixedAsset.requestFocus();
            return false;
        }

        return true;
    }

    private void call_user_update_api() throws JSONException {
        functions.showLoading();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json; charset=UTF-8");
        params.put("token", Constants.UAT_ACCESS_TOKEN);
        //input your API parameters
        JSONObject object = new JSONObject();
        object.put("LoginId", "ITIC-00000007");//sessionManager.getloginId());
        object.put("Company_Id", "COM00000023"); // sessionManager.getCompanyId());
        object.put("id", "581");
        object.put("session_fin_year", "2022-2023");
        object.put("purchase_invoice_prefix", purchase_invoice_prefix);
        object.put("purchase_invoice_last_no",purchase_invoice_last_no);
        object.put("purchase_invoice_total_allot", "59");
        object.put("purchase_return_invoice_prefix", purchase_return_invoice_prefix);
        object.put("purchase_return_invoice_last_no", purchase_return_invoice_last_no);
        object.put("purchase_return_invoice_total_allot", "88");
        object.put("sales_invoice_prefix", sales_invoice_prefix);
        object.put("sales_return_invoice_last_no", sales_retail_invoice_last_no);
        object.put("sales_return_invoice_total_allot", sales_return_invoice_prefix);
        object.put("sales_performa_invoice_last_no", sales_performa_invoice_last_no);
        object.put("sales_retail_invoice_last_no", sales_retail_invoice_last_no);
        object.put("sales_export_invoice_last_no", sales_export_invoice_last_no);
        object.put("sales_dc_invoice_last_no", sales_delivery_ch_invoice_last_no);
        object.put("sales_rcm_invoice_last_no", sales_rcm_invoice_last_no);
        object.put("sales_st_invoice_last_no", sales_stock_transfer_invoice_last_no);
        object.put("sales_fa_invoice_last_no", sales_fixed_asset_invoice_last_no);
        object.put("license_expiry","yes");
        object.put("use_atuthorized_signatory_image", "yes");
        object.put("atuthorized_signatory_image", "yes");
        object.put("terms_conditions","yes");
        object.put("invoice_footer_text", "yes");
        object.put("item_wise_discount","yes");
        object.put("bill_wise_discount","yes");
        RequestApi api = new RequestApi(this, this);
        api.requestJson(ApiList.userSettingUrl, object, 101);
    }

    @Override
    public void onResponse(int requestCode, String response) {
        functions.hideLoading();
        verifyAuth(response);
    }

    @Override
    public void onError(int requestCode, String error) {

    }

    private void verifyAuth(String response) {
        ResponseModel model = new Gson().fromJson(response,ResponseModel.class);
        if (model.success){
            Toasty.success(this, "Settings updated").show();
            finish();
        }
    }
}
