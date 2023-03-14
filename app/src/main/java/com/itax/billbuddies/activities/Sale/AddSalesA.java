package com.itax.billbuddies.activities.Sale;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.activities.Customer.SelectCustomerA;
import com.itax.billbuddies.activities.Item.ItemDetailA;
import com.itax.billbuddies.activities.Item.ItemSelectA;
import com.itax.billbuddies.adapter.CartAdapter;
import com.itax.billbuddies.controller.Cart;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.ActivityAddSalesBinding;
import com.itax.billbuddies.dialog.DatePicker;
import com.itax.billbuddies.layout.CustomLayoutManager;
import com.itax.billbuddies.listener.CallBackListener;
import com.itax.billbuddies.listener.ClickListener;
import com.itax.billbuddies.models.AddSaleModel;
import com.itax.billbuddies.models.CartItem;
import com.itax.billbuddies.models.CustomerModel;
import com.itax.billbuddies.models.ItemModel;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;
import com.itax.billbuddies.utils.SessionManager;

import java.util.ArrayList;

public class AddSalesA extends AppCompatActivity {
    ActivityAddSalesBinding binding;
    String spinner_shipping_detail, spinner_other_charges, spinner_tds_receiver;
    ArrayList<String> payment_type_list = new ArrayList<>();
    ArrayList<String> shipping_detail_List = new ArrayList<>();
    ArrayList<String> other_charges_list = new ArrayList<>();
    ArrayList<String> tds_receiver_List = new ArrayList<>();
    ArrayList<String> dispatch_list = new ArrayList<>();
    ArrayList<String> e_way_bill_list = new ArrayList<>();
    Functions functions;
    ArrayAdapter payment_type_adapter,shipping_detail_Adapter,other_charges_Adapter,tds_receiver_Adapter;
    ActivityResultLauncher<Intent> resultLauncher;
    CustomerModel.CustomerItem customerItem;
    DatePicker datePicker;
    int requestCode = 0;/// 1 for customer and 2 for item
    Cart cart;

    ArrayList<CartItem> itemList = new ArrayList<>();
    CartAdapter adapter;
    CustomLayoutManager mLayoutManager;
    String TAG = "AddSalesA";
    int item_position = 0;
    SessionManager sessionManager;

    String customer_name,sales_date,reference_no,invoice_id,payment_method,shipping_charges,dispatch_through,eway_bill_no;
    String tds_bill_applicable,tds_amount,driver_name,driver_mobile_no,vehicle_no,notes,total,sub_total,discount_amount;
    String tax_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        binding = ActivityAddSalesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        functions = new Functions(this);
        datePicker = new DatePicker(this);
        sessionManager = new SessionManager(this);
        Constants.saleItemList.clear();
        initView();
    }

    private void initView(){
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
        binding.etCustomerName.setOnClickListener(v->{
            requestCode = 1;
            selectCustomer();
        });
        binding.etSaleDate.setOnClickListener(v->{
            datePicker.setDate(binding.etSaleDate);
        });
        binding.btnAddItem.setOnClickListener(view -> {
            requestCode = 2;
            selectItem();
        });

        addPaymentType();
        add_other_charges_Spinner();
        add_shipping_detail_Spinner();
        add_tds_receiver_Spinner();
        add_e_way_bill_spinner();
        add_dispatch_list();

        initLauncher();
        setupRecyclerView();
    }

    private void addPaymentType(){
        payment_type_list.add("Cash payment");
        payment_type_list.add("Bank payment");
        payment_type_list.add("Due payment");
        payment_type_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,payment_type_list);
        binding.spinnerPaymentType.setAdapter(payment_type_adapter);
        binding.spinnerPaymentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payment_method = Integer.toString(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void add_shipping_detail_Spinner() {
        shipping_detail_List.add("yes");
        shipping_detail_List.add("no");

        shipping_detail_Adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,shipping_detail_List);
        binding.spinnerShiping.setAdapter(shipping_detail_Adapter);

        binding.spinnerShiping.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_shipping_detail = shipping_detail_List.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void add_tds_receiver_Spinner() {
        tds_receiver_List.add("No");
        tds_receiver_List.add("0.25%");
        tds_receiver_List.add("0.50%");
        tds_receiver_List.add("0.75%");
        tds_receiver_List.add("1%");
        tds_receiver_List.add("2%");
        tds_receiver_List.add("3%");
        tds_receiver_List.add("3.75%");
        tds_receiver_List.add("5%");
        tds_receiver_List.add("7%");
        tds_receiver_List.add("7.75%");
        tds_receiver_List.add("10%");
        tds_receiver_List.add("20%");
        tds_receiver_List.add("30%");

        tds_receiver_Adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,tds_receiver_List);
        binding.spinnerTdsReceivable.setAdapter(tds_receiver_Adapter);

        binding.spinnerTdsReceivable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_tds_receiver = tds_receiver_List.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void add_other_charges_Spinner() {
        other_charges_list.add("Select Charges");
        other_charges_list.add("Loading/Unloading Charges");
        other_charges_list.add("Transport Charges");
        other_charges_list.add("Service Charges");

        other_charges_Adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,other_charges_list);
        binding.spinnerOtherCharges.setAdapter(other_charges_Adapter);

        binding.spinnerOtherCharges.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_other_charges = other_charges_list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void add_e_way_bill_spinner(){
        e_way_bill_list.add("Yes");
        ArrayAdapter e_way_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,e_way_bill_list);
        binding.spinnerBillApplicable.setAdapter(e_way_adapter);
        binding.spinnerBillApplicable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void add_dispatch_list(){
        dispatch_list.add("Yes");
        ArrayAdapter dispatch_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,dispatch_list);
        binding.spinnerDispatchThrough.setAdapter(dispatch_adapter);
        binding.spinnerDispatchThrough.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dispatch_through = dispatch_list.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void selectCustomer(){
        Intent intent = new Intent(this, SelectCustomerA.class);
        resultLauncher.launch(intent);
    }

    private void selectItem(){
        Intent intent = new Intent(this, ItemSelectA.class);
        resultLauncher.launch(intent);
    }

    private void initLauncher(){
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK){
                String data = result.getData().getStringExtra("result");
                String action = result.getData().getStringExtra("action");
                if (!data.isEmpty()){
                    if (requestCode == 1){
                        customerItem = new Gson().fromJson(data, CustomerModel.CustomerItem.class);
                        binding.etCustomerName.setText(customerItem.fname);
                    }
                    else if (requestCode == 2){
                        CartItem item = new Gson().fromJson(data,CartItem.class);
                        manageItem(item,action);
                    }
                }
            }
        });
    }

    private void setupRecyclerView(){
        binding.pb.setVisibility(View.GONE);
        mLayoutManager = new CustomLayoutManager(this,CustomLayoutManager.VERTICAL, false);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        adapter = new CartAdapter(this, itemList, position -> {
            if (position != Constants.quantity_update){
                String data = new Gson().toJson(itemList.get(position));
                requestCode = 2;
                resultLauncher.launch(new Intent(this, ItemDetailA.class).putExtra(Constants.sale_item,data));
            }
            else {
                calculateItem();
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private boolean isExistingItem(CartItem item){
        for (int i=0; i < itemList.size(); i++){
            if (itemList.get(i).barcode.equals(item.barcode)){
                //functions.showError(getString(R.string.item_already_added));
                item_position = i;
                return true;
            }
        }
        return false;
    }

    private void manageItem(CartItem item,String action){
        isExistingItem(item);
        if (action != null){
            if (action.equalsIgnoreCase("0")){
                itemList.remove(item_position);
            }
        }
        else if (isExistingItem(item)){
           itemList.set(item_position,item);
        }
        else {
            itemList.add(item);
        }
        calculateItem();
    }

    private void calculateItem(){
        double total_taxable_value = 0;
        double total_tax_amount = 0;
        double gst_amount = 0;
        double total_sub_total = 0;
        double total_grand_total = 0;

        for (int i=0; i<itemList.size(); i++){

            int quantity = Functions.ParseInteger(itemList.get(i).quantity);
            if (quantity == 0){
                quantity = 1;
            }
            double actual_price = Functions.ParseDouble(itemList.get(i).actual_price);
            double amount = actual_price * quantity;
            double discount_percentage = Functions.ParseDouble(itemList.get(i).discount);
            double tax_percentage = Functions.ParseDouble(itemList.get(i).tax_value);
            // calculate
            double tax_amount = (tax_percentage * amount)/100;
            amount = amount - ((discount_percentage * amount)/100);

            total_tax_amount = total_tax_amount + tax_amount;
            total_sub_total = total_sub_total + amount;
        }

        binding.txtTotalTaxAmount.setText(Double.toString(total_tax_amount));
        binding.txtSubTotal.setText(Double.toString(total_sub_total));
        binding.txtGrandTotal.setText(Double.toString(total_sub_total));
        Log.d(TAG, "calculateItem: "+ total_sub_total);

        adapter.notifyDataSetChanged();
    }

    private boolean validateInput(){
        sales_date = binding.etSaleDate.getText().toString();
        reference_no = binding.etRefno.getText().toString();
        invoice_id = binding.etinvoiceid.getText().toString();
        //payment_method
        // shipping
        //dispatch
        // e way
        // tax amount
        return false;
    }

    private void callAddSaleApi(){
        AddSaleModel model = new AddSaleModel();
        model.loginID = PaperDbManager.getLoginData().loginID;
        model.company_id = PaperDbManager.getCompany().Company_Id;
        model.session_fin_year = sessionManager.getFinancialYear();
        model.customer_id = customerItem.id;

    }
}