package com.itax.billbuddies.models;

import java.util.ArrayList;

public class ItemCategoryModel {
    public boolean success;
    public String message;
    public ArrayList<ItemCategoryData> data;

    public static class ItemCategoryData{
        public String id;
        public String loginID;
        public String company_id;
        public String session_fin_year;
        public String type = null;
        public String parent_category_id;
        public String category_name;
        public String date_created = null;
        public String updatedBy = null;
        public String date_modified = null;
    }
}
