package com.itax.billbuddies.models;

import java.util.ArrayList;

public class PrintSettingModel {
    public boolean success;
    public String message;
    public ArrayList<PrintSetting> data;

    public static class PrintSetting {
        public String id;
        public String loginID;
        public String company_id;
        public String session_fin_year;
        public String header;
        public String footer;
        public String signature;
        public String use_atuthorized_signatory_image;
        public String atuthorized_signatory_image;
        public String tds_applicable_sale;
        public String cess_applicable_sale;
        public String eway_bill_applicable_sale;
        public String dispatch_though_applicable_sale;
        public String tds_applicable_purchase;
        public String cess_applicable_purchase;
        public String eway_bill_applicable_purchase;
        public String dispatch_though_applicable_purchase;
        public String total_outstaing_sale;
        public String other;
    }
}
