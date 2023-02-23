package com.itax.billbuddies.models;

import java.util.ArrayList;

public class CashTransModel {
    public boolean success;
    public String message;
    public ArrayList<CashTransaction> data;

    public static class CashTransaction{
        public String id;
        public String loginID;
        public String company_id;
        public String session_fin_year;
        public String payments_id;
        public String date;
        public String transaction_id;
        public String txn_type;
        public String txn_reference;
        public String dr_cr_type;
        public String amount;
        public String balance;
        public String notes;
        public String date_created;
        public String updatedBy = null;
        public String date_modified = null;
    }
}
