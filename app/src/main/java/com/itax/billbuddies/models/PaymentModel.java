package com.itax.billbuddies.models;

import java.util.ArrayList;

public class PaymentModel {
    public boolean success;
    public String message;
    public ArrayList<PaymentItem> data;

    public static class PaymentItem{
        public String id;
        public String loginID;
        public String company_id;
        public String session_fin_year;
        public String payment_type;
        public String party_id = null;
        public String purchase_sales_id;
        public String invoice_id;
        public String amount_paid;
        public String payment_date;
        public String payment_method;
        public String bank_id;
        public String cheque_no = null;
        public String cheque_date = null;
        public String refrence_no = null;
        public String upi_refrence_no = null;
        public String notes;
        public String date_created;
        public String updatedBy = null;
        public String date_modified = null;
    }
}
