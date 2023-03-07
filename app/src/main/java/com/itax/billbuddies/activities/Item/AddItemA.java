package com.itax.billbuddies.activities.Item;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.activities.Brand.AddBrandA;
import com.itax.billbuddies.activities.HsnA;
import com.itax.billbuddies.activities.TaxClass.AddTaxClassA;
import com.itax.billbuddies.activities.Unit.AddUnitA;
import com.itax.billbuddies.activities.category.AddCategoryA;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.ActivityAddItemBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.models.ItemBrandModel;
import com.itax.billbuddies.models.ItemCategoryModel;
import com.itax.billbuddies.models.ItemTaxClassModel;
import com.itax.billbuddies.models.ItemUnitModel;
import com.itax.billbuddies.models.ResponseModel;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;
import com.itax.billbuddies.utils.InputValidator;
import com.itax.billbuddies.utils.SessionManager;
import com.itax.billbuddies.zxing.BarcodeScanner;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddItemA extends AppCompatActivity {
    ActivityAddItemBinding binding;
    ActivityResultLauncher<Intent> resultLauncher,scanResultLauncher;
    Bitmap bitmap;
    String TAG = "AddItemA",imageString = "";
    Functions functions;
    SessionManager sessionManager;

    ArrayList<ItemUnitModel.ItemUnitData>unitDataList = new ArrayList<>();
    ArrayList<ItemCategoryModel.ItemCategoryData>categoryDataList = new ArrayList<>();
    ArrayList<ItemBrandModel.ItemBrandData>brandDataList = new ArrayList<>();
    ArrayList<ItemTaxClassModel.ItemTaxClass>taxClassDataList = new ArrayList<>();

    ArrayList<String>unitList = new ArrayList<>();
    ArrayList<String>categoryList = new ArrayList<>();
    ArrayList<String>brandList = new ArrayList<>();
    ArrayList<String>taxClassList = new ArrayList<>();
    ArrayList<String>taxTypeList = new ArrayList<>();

    ArrayAdapter unitAdapter,categoryAdapter,brandAdapter,taxClassAdapter,taxTypeAdapter;

    String hsc_code,product_name,sku,barcode,unit,category_id,brand_id,description,actual_price,tax_class,tax_type,selling_price;
    String filename = "",tax_value = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        functions = new Functions(this);
        sessionManager  = new SessionManager(this);

        initView();
    }

    private void initView(){
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
        binding.imgSelect.setOnClickListener(v->{
            selectImage();
        });
        binding.imgAddUnit.setOnClickListener(v->{
            moveToAddUnit();
        });
        binding.imgAddBrand.setOnClickListener(v->{
            moveToAddBrand();
        });
        binding.imgAddCategory.setOnClickListener(v->{
            moveToAddCategory();
        });
        binding.imgAddTaxClass.setOnClickListener(v->{
            moveToAddTaxClass();
        });
        binding.btnSubmit.setOnClickListener(v->{
            if (validateInput()){
                saveProduct();
            }
        });

        binding.txtHsnCode.setOnClickListener(v->{
            startActivity(new Intent(this, HsnA.class));
        });
        getActivityResult();

        binding.imgBarcode.setOnClickListener(v->{
            openScanner();
        });
        getScanResult();
    }

    private void addSpinnerClick(){
        binding.spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!unitList.isEmpty()){
                    unit = unitList.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!categoryList.isEmpty()){
                    category_id = categoryDataList.get(i).id;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!brandDataList.isEmpty()){
                    brand_id = brandDataList.get(i).id;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spinnerTaxClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!taxClassDataList.isEmpty()){
                    tax_class = taxClassDataList.get(i).tax_name;
                    tax_value = taxClassDataList.get(i).tax_percentage;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spinnerTaxType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!taxTypeList.isEmpty()){
                    tax_type = taxTypeList.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onResume(){
        super.onResume();
        addSpinner();
    }

    private void selectImage(){
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
                    binding.imgSelect.setImageURI(filePath);
                    bitmap = ((BitmapDrawable)binding.imgSelect.getDrawable()).getBitmap();
                }
                else {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    binding.imgSelect.setImageBitmap(bitmap);
                    Log.d(TAG, "getActivityResult: "+ "file path null");
                }
                convertToBase64(bitmap);
            }
        });
    }

    private void convertToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        imageString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.d(TAG, "convertToBase64: "+ imageString);
    }

    private void moveToAddUnit(){
        startActivity(new Intent(this, AddUnitA.class));
    }
    private void moveToAddBrand(){
        startActivity(new Intent(this, AddBrandA.class));
    }
    private void moveToAddCategory(){
        startActivity(new Intent(this, AddCategoryA.class));
    }
    private void moveToAddTaxClass(){
        startActivity(new Intent(this, AddTaxClassA.class));
    }

    private void addSpinner(){
        unitList.clear();
        categoryList.clear();
        brandList.clear();
        taxClassList.clear();
        taxTypeList.clear();

        taxTypeList.add("Exclusive");
        taxTypeList.add("Inclusive");

        unitAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,unitList);
        categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,categoryList);
        brandAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,brandList);
        taxClassAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,taxClassList);
        taxTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,taxTypeList);

        binding.spinnerUnit.setAdapter(unitAdapter);
        binding.spinnerCategory.setAdapter(categoryAdapter);
        binding.spinnerBrand.setAdapter(brandAdapter);
        binding.spinnerTaxClass.setAdapter(taxClassAdapter);
        binding.spinnerTaxType.setAdapter(taxTypeAdapter);

        getUnit();
        getCategory();
        getBrand();
        getTaxClass();

        addSpinnerClick();
    }

    private void getUnit(){
        String url = ApiList.UNIT_URL+"?loginID="+ PaperDbManager.getLoginData().loginID+"&company_id="+PaperDbManager.getCompany().Company_Id;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(url, response -> {
            Log.d(TAG, "getUnit: "+ response);
            ItemUnitModel model = new Gson().fromJson(response,ItemUnitModel.class);
            if (model.success){
                unitDataList.addAll(model.data);
                addUnitSpinnerItem();
            }

        }, error -> {
            Log.d(TAG, "getUnit: "+ error);
        });
        requestQueue.add(request);
    }

    private void getCategory(){
        String url = ApiList.CATEGORY_URL+"?loginID="+ PaperDbManager.getLoginData().loginID+"&company_id="+PaperDbManager.getCompany().Company_Id;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(url, response -> {
            Log.d(TAG, "getCategory: "+ response);
            ItemCategoryModel model = new Gson().fromJson(response,ItemCategoryModel.class);
            if (model.success){
                categoryDataList.addAll(model.data);
                addCategorySpinnerItem();
            }

        }, error -> {
            Log.d(TAG, "getCategory: "+ error);
        });
        requestQueue.add(request);
    }

    private void getBrand(){
        String url = ApiList.BRAND_URL+"?loginID="+ PaperDbManager.getLoginData().loginID+"&company_id="+PaperDbManager.getCompany().Company_Id;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(url, response -> {
            Log.d(TAG, "getBrand: "+ response);
            ItemBrandModel model = new Gson().fromJson(response,ItemBrandModel.class);
            if (model.success){
                brandDataList.addAll(model.data);
                addBrandSpinnerItem();
            }

        }, error -> {
            Log.d(TAG, "getBrand: "+ error);
        });
        requestQueue.add(request);
    }

    private void getTaxClass(){
        String url = ApiList.TAX_CLASS_URL+"?loginID="+ PaperDbManager.getLoginData().loginID+"&company_id="+PaperDbManager.getCompany().Company_Id;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(url, response -> {
            Log.d(TAG, "getTaxClass: "+ response);
            ItemTaxClassModel model = new Gson().fromJson(response,ItemTaxClassModel.class);
            if (model.success){
                taxClassDataList.addAll(model.data);
                addTaxClassSpinnerItem();
            }

        }, error -> {
            Log.d(TAG, "getTaxClass: "+ error);
        });
        requestQueue.add(request);
    }

    private void addUnitSpinnerItem(){
        unitList.clear();
        for (int i=0; i<unitDataList.size(); i++){
            if (unitDataList.get(i).unitname != null){
                unitList.add(unitDataList.get(i).unitname);
            }
        }
        unitAdapter.notifyDataSetChanged();
    }

    private void addCategorySpinnerItem(){
        categoryList.clear();
        for (int i=0; i<categoryDataList.size(); i++){
            if (categoryDataList.get(i).category_name != null){
                categoryList.add(categoryDataList.get(i).category_name);
            }
        }
        categoryAdapter.notifyDataSetChanged();
    }

    private void addBrandSpinnerItem(){
        brandList.clear();
        for (int i=0; i<brandDataList.size(); i++){
            if (brandDataList.get(i).brand_name != null){
                brandList.add(brandDataList.get(i).brand_name);
            }
        }
        brandAdapter.notifyDataSetChanged();
    }

    private void addTaxClassSpinnerItem(){
        taxClassList.clear();
        for (int i=0; i<taxClassDataList.size(); i++){
            if (taxClassDataList.get(i).tax_name != null){
                taxClassList.add(taxClassDataList.get(i).tax_name);
            }
        }
        taxClassAdapter.notifyDataSetChanged();
    }

    private boolean validateInput(){
        hsc_code = binding.etHsnCode.getText().toString();
        product_name = binding.etProductName.getText().toString();
        sku = binding.etSku.getText().toString();
        barcode = binding.etBarcode.getText().toString();
        // unit
        // category
        //brand name
        description = binding.etDescription.getText().toString();
        actual_price = binding.etActualPrice.getText().toString();
        // tax class
        // tax type
        selling_price = binding.etSellingPrice.getText().toString();

        if (hsc_code.isEmpty()){
            InputValidator.validateInput(binding.etHsnCode);
            return false;
        }
        else if (product_name.isEmpty()){
            InputValidator.validateInput(binding.etProductName);
            return false;
        }
        else if (sku.isEmpty()){
            InputValidator.validateInput(binding.etSku);
            return false;
        }
        else if (barcode.isEmpty()){
            InputValidator.validateInput(binding.etBarcode);
            return false;
        }
        else if (description.isEmpty()){
            InputValidator.validateInput(binding.etDescription);
            return false;
        }
        else if (actual_price.isEmpty()){
            InputValidator.validateInput(binding.etActualPrice);
            return false;
        }
        else if (selling_price.isEmpty()){
            InputValidator.validateInput(binding.etSellingPrice);
            return false;
        }

        filename = System.currentTimeMillis() + ".png";
        return true;
    }
    private void saveProduct(){
        try {
            JSONObject object = new JSONObject();
            object.put("loginID", PaperDbManager.getLoginData().loginID);
            object.put("company_id",PaperDbManager.getCompany().getCompany_Id());
            object.put("session_fin_year",sessionManager.getAssessmentYear());
            object.put("type","");
            object.put("brand_id",brand_id);
            object.put("product_category_id",category_id);
            object.put("product_name",product_name);
            object.put("sku",sku);
            object.put("hsn",hsc_code);
            object.put("barcode",barcode);
            object.put("product_description",description);
            object.put("lot_no","");
            object.put("weight","");
            object.put("weight_class","");
            object.put("size_class","");
            object.put("height","");
            object.put("width","");
            object.put("length","");
            object.put("main_image",filename);
            object.put("inventory","");
            object.put("price","");
            object.put("tax_class","");
            object.put("tax_value","");
            object.put("tax_type","");
            object.put("actual_price",actual_price);
            object.put("selling_price",selling_price);
            object.put("status","");
            object.put("image_base64",imageString);

            RequestApi api = new RequestApi(this, new ResponseListener() {
                @Override
                public void onResponse(int requestCode, String response) {
                    ResponseModel model = new Gson().fromJson(response,ResponseModel.class);
                    functions.hideLoading();
                    if (model.success){
                        functions.showSuccess("Product saved");
                        finish();
                    }
                }

                @Override
                public void onError(int requestCode, String error) {
                    Log.d(TAG, "onError: "+ error);
                    functions.hideLoading();
                }
            });
            api.requestJson(ApiList.ITEM_PRODUCT_URL,object,101);
            functions.showLoading();
        }
        catch (Exception e){
            Log.d(TAG, "callApi: "+ e.getMessage());
        }
    }

    public void openScanner(){
        Intent intent = new Intent(this, BarcodeScanner.class);
        scanResultLauncher.launch(intent);
    }

    private void getScanResult(){
        scanResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                binding.etBarcode.setText(Constants.scanned_result);
            }
        });
    }
}