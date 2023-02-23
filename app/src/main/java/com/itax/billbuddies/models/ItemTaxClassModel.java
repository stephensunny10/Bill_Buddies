package com.itax.billbuddies.models;

import java.util.ArrayList;

public class ItemTaxClassModel {
    public boolean success;
    public String message;
    public ArrayList<ItemTaxClass> data;
    
    public static class ItemTaxClass{
        public String id;
        public String loginID;
        public String company_id;
        public String session_fin_year;
        public String tax_name;
        public String tax_percentage;
        public String date_created = null;
        public String updatedBy = null;
        public String date_modified = null;
    }
}
