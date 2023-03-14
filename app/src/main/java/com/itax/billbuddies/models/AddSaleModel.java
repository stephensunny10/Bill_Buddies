package com.itax.billbuddies.models;

import java.util.ArrayList;

public class AddSaleModel {
    public String id;
    public String loginID;
    public String company_id;
    public String session_fin_year;
    public String due_date = null;
    public String bank_id;
    public String customer_id;
    public String sales_date;
    public String invoice_id;
    public String reference_no;
    public String invoice_discount_type = null;
    public String invoice_discount;
    public String invoice_discount_amount;
    public String shipping_charges;
    public String tax_amount;
    public String discount_amount;
    public String other_charges;
    public String roundoff;
    public String subtotal;
    public String total;
    public String status;
    public String paymentmethod;
    public String total_paid_amount;
    public String last_paid_amount_on;
    public String total_due_amount;
    public String extra_amount_received = null;
    public String notes;
    public String different_shipping_details;
    public String dispatch_through;
    public String vehicle_no;
    public String driver_name;
    public String driver_mobile_no;
    public String eway_bill_applicable;
    public String eway_bill_number;
    public String performa_to_tax = null;
    public String tds_bill_applicable;
    public String tds_amount;
    public String date_created;
    public String updatedBy = null;
    public String date_modified = null;
    public ArrayList<CartItem>items;

}
