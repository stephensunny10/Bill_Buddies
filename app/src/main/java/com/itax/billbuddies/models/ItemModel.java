package com.itax.billbuddies.models;

import java.util.ArrayList;

public class ItemModel {
    public boolean success;
    public String message;
    public ArrayList<Item> data;

    public static class Item{
        public String id;
        public String loginID;
        public String company_id;
        public String session_fin_year;
        public String type = null;
        public String brand_id;
        public String product_category_id;
        public String product_name;
        public String sku;
        public String hsn;
        public String barcode;
        public String product_description;
        public String lot_no;
        public String weight;
        public String weight_class;
        public String size_class;
        public String height;
        public String width;
        public String length;
        public String main_image;
        public String inventory;
        public String price;
        public String tax_class;
        public String tax_value;
        public String tax_type;
        public String actual_price;
        public String selling_price;
        public String status;
        public String expiry_date;
        public String date_created;
        public String updatedBy = null;
        public String date_modified;
        public String brand_name;
        public String category_name;
    }
}
