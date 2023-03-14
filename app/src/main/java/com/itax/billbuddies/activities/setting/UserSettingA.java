package com.itax.billbuddies.activities.setting;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.controller.UserSettings;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.ActivityUserSettingBinding;
import com.itax.billbuddies.dialog.DatePicker;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.models.ResponseModel;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;
import com.itax.billbuddies.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class UserSettingA extends AppCompatActivity implements ResponseListener {
    ActivityUserSettingBinding binding;
    String purchase_invoice_prefix, purchase_invoice_last_no, purchase_return_invoice_prefix, sales_invoice_prefix, sales_invoice_last_no, sales_return_invoice_prefix,
            sales_performa_invoice_last_no, sales_retail_invoice_last_no, sales_export_invoice_last_no, sales_delivery_ch_invoice_last_no, sales_rcm_invoice_last_no, sales_stock_transfer_invoice_last_no,
            sales_fixed_asset_invoice_last_no, use_authorised_sig_image, terms_condition, invoice_footer_text, purchase_return_invoice_last_no, user_setting;
    Integer item_wise_discount, bill_wise_discount, tds_applicable, cess_applicable, eway_bill_applicable,
            dispatch_though_applicable, do_you_want_total_out_sale, do_you_want_notification;
    Functions functions;
    String license_expiry,useAuthorizedImage = "1";
    ActivityResultLauncher<Intent> resultLauncher;
    int requestCode = 0;// set 1 for image and 2 for sign
    String imageString = "",signString = "";
    String TAG = "UserSettingA";
    Bitmap bitmap;
    SessionManager sessionManager;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        functions = new Functions(this);
        sessionManager = new SessionManager(this);
        datePicker = new DatePicker(this);

        initView();
    }

    private void initView() {
        new UserSettings(this,binding);
        binding.imgBack.setOnClickListener(v -> {
            finish();
        });

        binding.btnSubmit.setOnClickListener(v -> {
            if (validateInput()) {
                try {
                    call_user_update_api();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        binding.imgSelectAuthImage.setOnClickListener(v->{
            selectImage(1);
        });

        binding.etLicenseExpiry.setOnClickListener(v->{
            datePicker.setDate(binding.etLicenseExpiry);
        });

        getActivityResult();
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
        sales_fixed_asset_invoice_last_no = binding.etSalesFixedAsset.getText().toString();
        terms_condition = binding.etTermsCondition.getText().toString();
        invoice_footer_text = binding.etInvoiceFooterText.getText().toString();
        item_wise_discount = binding.radioGroupItem.getCheckedRadioButtonId();
        bill_wise_discount = binding.radioGroupBill.getCheckedRadioButtonId();
        license_expiry = binding.etLicenseExpiry.getText().toString();

        if (purchase_invoice_prefix.isEmpty()) {
            binding.etPurchaseInvoicePrefix.setError("Field should no be empty");
            binding.etPurchaseInvoicePrefix.requestFocus();
            return false;
        }
        if (purchase_invoice_last_no.isEmpty()) {
            binding.etPurchaseInvoiceLastNo.setError("Please enter purchase invoice last no");
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
            binding.etSalesFixedAsset.setError("Please enter sales fixed asset");
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

        if (license_expiry.isEmpty()){
            binding.etLicenseExpiry.setError("Please enter expiry date");
            binding.etLicenseExpiry.requestFocus();
            return false;
        }

        if (binding.rbAuthorizedImageYes.isChecked()){
            useAuthorizedImage = "1";
        }
        else {
            useAuthorizedImage = "0";
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
        object.put("loginID", PaperDbManager.getLoginData().loginID);//sessionManager.getloginId());
        object.put("company_id", PaperDbManager.getCompany().getCompany_Id()); // sessionManager.getCompanyId());
        object.put("id", "581");
        object.put("session_fin_year", sessionManager.getFinancialYear());
        object.put("purchase_invoice_prefix", purchase_invoice_prefix);
        object.put("purchase_invoice_last_no", purchase_invoice_last_no);
        object.put("purchase_invoice_total_allot", "59");
        object.put("purchase_return_invoice_prefix", purchase_return_invoice_prefix);
        object.put("purchase_return_invoice_last_no", purchase_return_invoice_last_no);
        object.put("purchase_return_invoice_total_allot", "88");
        object.put("sales_invoice_prefix", sales_invoice_prefix);//sales_invoice_last_no
        object.put("sales_invoice_last_no", sales_invoice_last_no);
        object.put("sales_return_invoice_last_no", sales_retail_invoice_last_no);
        object.put("sales_return_invoice_total_allot", "1000");
        object.put("sales_performa_invoice_last_no", sales_performa_invoice_last_no);
        object.put("sales_retail_invoice_last_no", sales_retail_invoice_last_no);
        object.put("sales_export_invoice_last_no", sales_export_invoice_last_no);
        object.put("sales_dc_invoice_last_no", sales_delivery_ch_invoice_last_no);
        object.put("sales_rcm_invoice_last_no", sales_rcm_invoice_last_no);
        object.put("sales_st_invoice_last_no", sales_stock_transfer_invoice_last_no);
        object.put("sales_fa_invoice_last_no", sales_fixed_asset_invoice_last_no);
        object.put("license_expiry", license_expiry);
        object.put("use_atuthorized_signatory_image", useAuthorizedImage);
        object.put("atuthorized_signatory_image_base64", imageString);
        object.put("terms_conditions", "yes");
        object.put("invoice_footer_text", "yes");
        object.put("item_wise_discount", "yes");
        object.put("bill_wise_discount", "yes");
        RequestApi api = new RequestApi(this, this);
        api.requestJson(ApiList.USER_SETTINGS_URL, object, 101);
    }

    @Override
    public void onResponse(int requestCode, String response) {
        functions.hideLoading();
        verifyAuth(response);
    }

    @Override
    public void onError(int requestCode, String error) {
        functions.hideLoading();
    }

    private void verifyAuth(String response) {
        ResponseModel model = new Gson().fromJson(response, ResponseModel.class);
        if (model.success) {
            Toasty.success(this, "Settings updated").show();
            finish();
        }
    }

    private void selectImage(int code){
        requestCode = code;
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        resultLauncher.launch(intent);
    }

    private void getActivityResult(){
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                Uri filePath = data.getData();
                if (filePath != null){
                    binding.imgSelectAuthImage.setImageURI(filePath);
                    bitmap = ((BitmapDrawable)binding.imgSelectAuthImage.getDrawable()).getBitmap();
                }
                else {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    binding.imgSelectAuthImage.setImageBitmap(bitmap);
                }
                imageString = convertToBase64(bitmap);
                Log.d(TAG, "getActivityResult: "+ imageString);
            }
        });
    }

    private String convertToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String imageString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return imageString;
    }
}
