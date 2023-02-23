package com.itax.billbuddies.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.itax.billbuddies.R;
import com.itax.billbuddies.adapter.HsnAdapter;
import com.itax.billbuddies.databinding.ActivityHsnBinding;
import com.itax.billbuddies.models.HsnItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class HsnA extends AppCompatActivity {
    ActivityHsnBinding binding;
    RecyclerView recyclerView;
    List<HsnItem> itemList = new ArrayList<>();
    HsnAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsn);
        binding = ActivityHsnBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("HSN CODE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        findView();
    }

    private void findView(){
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new HsnAdapter(itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        readCSV();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.hsn_menu,menu);
        MenuItem searchViewItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void readCSV(){
        InputStream is = getResources().openRawResource(R.raw.gst_rate);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";
        JSONArray array = new JSONArray();
        try {
            while ((line = reader.readLine()) != null) {
                // Split the line into different tokens (using the comma as a separator).
                String[] tokens = line.split("/=");

                // Read the data and store it in the HsnItem.
                HsnItem model = new HsnItem();
                model.setBrandName(tokens[0]);
                model.setProductName(tokens[1]);
                model.setRate(tokens[2]);
                model.setHsnCode(tokens[3]);
                itemList.add(model);
                Log.d("MainActivity" ,"Just Created ");

                JSONObject object = new JSONObject();
                object.put("product",tokens[1]);
                object.put("rate",tokens[2]);
                object.put("hsn",tokens[3]);
                array.put(object);
            }
            adapter.notifyDataSetChanged();
            //copyToClipboard();// copy to clipboard
        } catch (IOException | JSONException e1) {
            Log.e("MainActivity", "Error" + line, e1);
            e1.printStackTrace();
        }
    }

    public void filter(String text){
        List<HsnItem> temp = new ArrayList();
        for(HsnItem d: itemList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getProductName().toLowerCase().contains(text.toLowerCase()) || d.getHsnCode().contains(text.toLowerCase()) || d.getRate().contains(text.toLowerCase())){
                temp.add(d);
            }
        }
        //update recyclerview
        adapter.updateList(temp);
    }
}