package com.itax.billbuddies.models;

import java.util.ArrayList;

public class CustomerModel {
    public boolean success;
    public String message;
    public ArrayList<Customer> data;


    public static class Customer{
    public String id;
    public String loginID;
    public String company_id;
    public String session_fin_year;
    public String due_date;
    public String bank_id;
    public String supplier_id;
    public String purchase_date;
    public String supplier_invoice_no;
    public String supplier_invoice_date;
    public String invoice_id;
    public String reference_no;
    public Object invoice_discount_type;
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
    public Object last_paid_amount_on;
    public String total_due_amount;
    public Object extra_amount_received;
    public String notes;
    public Object scan_supplier_bill;
    public String rcm_applicable;
    public String pan_card;
    public String tds_bill_applicable;
    public String tds_amount;
    public String date_created;
    public String updatedBy;
    public String date_modified;

}}
