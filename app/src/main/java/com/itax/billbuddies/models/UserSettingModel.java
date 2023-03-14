package com.itax.billbuddies.models;

import java.util.ArrayList;

public class UserSettingModel {
    public boolean success;
    public String message;
    public ArrayList<UserSettingsItem> data;

    public static class UserSettingsItem {
        public String id;
        public String loginID;
        public String company_id;
        public String session_fin_year;
        public String purchase_invoice_prefix;
        public String purchase_invoice_last_no;
        public String purchase_invoice_total_allot;
        public String purchase_return_invoice_prefix;
        public String purchase_return_invoice_last_no;
        public String purchase_return_invoice_total_allot;
        public String sales_invoice_prefix;
        public String sales_invoice_last_no;
        public String sales_invoice_total_allot;
        public String sales_return_invoice_prefix;
        public String sales_return_invoice_last_no;
        public String sales_return_invoice_total_allot;
        public String sales_performa_invoice_last_no;
        public String sales_retail_invoice_last_no;
        public String sales_export_invoice_last_no;
        public String sales_dc_invoice_last_no;
        public String sales_rcm_invoice_last_no;
        public String sales_st_invoice_last_no;
        public String sales_fa_invoice_last_no;
        public String license_expiry;
        public String use_atuthorized_signatory_image = null;
        public String atuthorized_signatory_image = null;
        public String terms_conditions = null;
        public String invoice_footer_text;
        public String item_wise_discount;
        public String bill_wise_discount;
    }

}
