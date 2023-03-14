package com.itax.billbuddies.models;

import java.util.ArrayList;

public class CustomerModel {
    public boolean success;
    public String message;
    public ArrayList<CustomerItem> data;

    public static class CustomerItem {
        public String id;
        public String loginID;
        public String company_id;
        public String session_fin_year;
        public Object ccode;
        public String business_type;
        public String nature_of_business;
        public String registration_no;
        public String registration_date;
        public String fname;
        public String mobile;
        public String email;
        public String dob;
        public String ad;
        public String gender;
        public String pan;
        public String gstin;
        public String address;
        public String pincode;
        public String city;
        public String state;
        public String wallet_balance;
        public String due_balance;
        public String notes;
        public String status;
        public String credit_time;
        public String credit_limit;
        public String tdsapplicable;
        public String tanno;
        public String date_created;
        public String updatedBy;
        public String date_modified;
    }
}
