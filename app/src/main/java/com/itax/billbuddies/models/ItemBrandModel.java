package com.itax.billbuddies.models;

import java.util.ArrayList;

public class ItemBrandModel {
    public boolean success;
    public String message;
    public ArrayList<ItemBrandData> data;
    
    public static class ItemBrandData{
        public String id;
        public String loginID;
        public String company_id;
        public String session_fin_year;
        public String brand_name;
        public String date_created = null;
        public String updatedBy = null;
        public String date_modified = null;
    }
}
