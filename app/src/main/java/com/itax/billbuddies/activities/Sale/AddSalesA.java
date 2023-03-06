package com.itax.billbuddies.activities.Sale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.itax.billbuddies.R;
import com.itax.billbuddies.databinding.ActivityAddSalesBinding;
import com.itax.billbuddies.utils.Functions;

import java.util.ArrayList;

public class AddSalesA extends AppCompatActivity {
    ActivityAddSalesBinding binding;
    String spinner_shipping_detail, spinner_other_charges, spinner_tds_receiver;
    ArrayList<String> shipping_detail_List = new ArrayList<>();
    ArrayList<String> other_charges_list = new ArrayList<>();
    ArrayList<String> tds_receiver_List = new ArrayList<>();
    Functions functions;
    ArrayAdapter shipping_detail_Adapter,other_charges_Adapter,tds_receiver_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        binding = ActivityAddSalesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView(){
        binding.imgBack.setOnClickListener(v->{
            finish();
        });
        add_other_charges_Spinner();
        add_shipping_detail_Spinner();
        add_tds_receiver_Spinner();
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
}