package com.itax.billbuddies.activities.Item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.itax.billbuddies.R;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.ActivityItemDetailBinding;
import com.itax.billbuddies.models.CartItem;
import com.itax.billbuddies.models.ItemModel;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import java.util.ArrayList;

public class ItemDetailA extends AppCompatActivity {
    ActivityItemDetailBinding binding;
    ArrayList<CartItem>cartList = new ArrayList<>();
    ItemModel.Item item;
    CartItem cartItem;
    boolean isCartItem = false,isSaleItem = false;
    int cartPosition = 0;
    Functions functions;
    String TAG = "ItemDetailA",action = "1"; // 0 for delete ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        binding = ActivityItemDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        functions = new Functions(this);
        cartList.addAll(PaperDbManager.getCartList());
        if (getIntent().getStringExtra(Constants.data) != null){
            String itemData = getIntent().getStringExtra(Constants.data);
            item = new Gson().fromJson(itemData,ItemModel.Item.class);
            cartItem  = new Gson().fromJson(itemData,CartItem.class);
            initView();
        }
        else if (getIntent().getStringExtra(Constants.sale_item) != null){
            String itemData = getIntent().getStringExtra(Constants.sale_item);
            item = new Gson().fromJson(itemData,ItemModel.Item.class);
            cartItem  = new Gson().fromJson(itemData,CartItem.class);
            isSaleItem = true;
            initView();
        }
    }

    private void initView(){
        checkCartItem();
        binding.btnCancel.setOnClickListener(v->{
            if (!isCartItem && isSaleItem){
                finish();
            }
            else if (isSaleItem){
                action = "0";
                returnBack(new Gson().toJson(cartItem));
            }
            else {
                removeItem(); // remove item
            }
        });
        binding.btnAddToCart.setOnClickListener(v->{
            manageCart(); // add or update item
        });
        if (isCartItem || isSaleItem){
            binding.btnAddToCart.setText(getString(R.string.update));
            binding.btnCancel.setText(getString(R.string.remove));
        }
        setData();
        addKeyboardListener();
    }

    private void setData(){
        Glide.with(this).load(ApiList.BILL_BUDDIES_IMAGE_URL + item.main_image).error(R.drawable.ic_product).into(binding.imgProduct);
        binding.txtCategory.setText("Category > "+ item.category_name);
        binding.txtProductName.setText(item.product_name);
        binding.txtProductCode.setText(item.barcode);
        binding.txtUnit.setText("");
        binding.txtHsn.setText(item.hsn);
        binding.txtSku.setText(item.sku);
        binding.txtPrice.setText(item.actual_price);
        binding.txtDescription.setText(item.product_description);

        binding.etQuantity.setText(cartItem.quantity);
        binding.etDiscount.setText(cartItem.discount);
    }

    private void checkCartItem(){
        for (int i=0; i < cartList.size(); i++){
            if (cartList.get(i).barcode.equals(cartItem.barcode)){
                isCartItem = true;
                cartPosition = i;
                return;
            }
        }
    }

    private void manageCart(){
        String quantity = binding.etQuantity.getText().toString();
        String discount = binding.etDiscount.getText().toString();
        if (Functions.ParseInteger(quantity) == 0){
            quantity = "1";
        }
        if (Functions.ParseDouble(discount) == 0){
            discount = "0";
        }
        cartItem.quantity = quantity;
        cartItem.discount = discount;

        if (isSaleItem){
            returnBack(new Gson().toJson(cartItem));
            return;
        }
        if (!isCartItem){
            cartList.add(cartItem);
            functions.showSuccess(getString(R.string.item_added_to_cart));
        }
        else {
            cartList.set(cartPosition,cartItem);
            functions.showSuccess(getString(R.string.item_updated));
        }
        PaperDbManager.setCartList(cartList);
        returnBack(new Gson().toJson(cartItem));
    }

    private void removeItem(){
        cartList.remove(cartPosition);
        functions.showSuccess(getString(R.string.item_removed_from_cart));
        PaperDbManager.setCartList(cartList);
        returnBack("");
    }

    private void addKeyboardListener(){
        binding.parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            binding.parentLayout.getWindowVisibleDisplayFrame(r);
            int screenHeight = binding.parentLayout.getRootView().getHeight();
            int keypadHeight = screenHeight - r.bottom;
            if (keypadHeight > screenHeight * 0.15) {
                // keyboard is showing
                binding.bottomLayout.setVisibility(View.GONE);
                binding.etQuantity.setCursorVisible(true);
            } else {
                // keyboard is hidden
                binding.bottomLayout.setVisibility(View.VISIBLE);
                binding.etQuantity.setCursorVisible(false);
            }
        });
    }

    private void returnBack(String result){
        Constants.selected_item = result;
        Intent intent = new Intent();
        intent.putExtra("result",result);
        intent.putExtra("action",action);
        setResult(RESULT_OK,intent);
        finish();
    }
}