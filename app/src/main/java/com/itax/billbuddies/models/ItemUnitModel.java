package com.itax.billbuddies.models;

import java.util.ArrayList;

public class ItemUnitModel {
    public boolean success;
    public String message;
    public ArrayList<ItemUnitData> data;

    public static class ItemUnitData{
        public String id;
        public String loginID;
        public String company_id;
        public String session_fin_year;
        public String unitname;
        public String date_created = null;
        public String updatedBy = null;
        public String date_modified = null;
    }
}
