package com.itax.billbuddies.controller;

import android.content.Context;

import com.itax.billbuddies.models.NavItem;
import com.itax.billbuddies.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NavMenuData {
    public static HashMap<String, List<String>> getData(Context context) {
        HashMap<String, List<String>> expandableDetailList = new HashMap<String, List<String>>();

        // As we are populating List of fruits, vegetables and nuts, using them here
        // We can modify them as per our choice.
        // And also choice of fruits/vegetables/nuts can be changed
        List<String> dashboard = new ArrayList<String>();
        dashboard.add(context.getString(R.string.home));

        List<String> sales = new ArrayList<String>();
        sales.add(context.getString(R.string.add_sales_services));
        sales.add(context.getString(R.string.list_sales));
        sales.add(context.getString(R.string.credit_note));
        sales.add(context.getString(R.string.add_debtors));

        List<String> purchases = new ArrayList<String>();
        purchases.add(context.getString(R.string.add_sales_services));
        purchases.add(context.getString(R.string.list_sales));
        purchases.add(context.getString(R.string.credit_note));
        purchases.add(context.getString(R.string.add_debtors));

        List<String> items = new ArrayList<String>();
        items.add(context.getString(R.string.add_item));
        items.add(context.getString(R.string.add_services));
        items.add(context.getString(R.string.add_category));
        items.add(context.getString(R.string.manage_unit));
        items.add(context.getString(R.string.manage_brands));
        items.add(context.getString(R.string.stock_converters));
        items.add(context.getString(R.string.manage_tax_classes));

        List<String> bank_cash = new ArrayList<String>();
        bank_cash.add(context.getString(R.string.manage_bank));
        bank_cash.add(context.getString(R.string.cash_opening));
        bank_cash.add(context.getString(R.string.manage_cash));
        bank_cash.add(context.getString(R.string.cash_to_bank));
        bank_cash.add(context.getString(R.string.bank_to_cash));

        // their respective children using an ArrayList of Strings.
        expandableDetailList.put(context.getString(R.string.dashboard), dashboard);
        expandableDetailList.put(context.getString(R.string.sale), sales);
        expandableDetailList.put(context.getString(R.string.purchase), purchases);
        expandableDetailList.put(context.getString(R.string.item_service), items);
        expandableDetailList.put(context.getString(R.string.bank_cash), bank_cash);
        expandableDetailList.put(context.getString(R.string.payment), new ArrayList<String>());
        expandableDetailList.put(context.getString(R.string.receipt), new ArrayList<String>());
        expandableDetailList.put(context.getString(R.string.category), new ArrayList<String>());
        expandableDetailList.put(context.getString(R.string.settings), new ArrayList<String>());
        expandableDetailList.put(context.getString(R.string.reports), new ArrayList<String>());


        NavItem item = new NavItem();
        item.title = context.getString(R.string.sale);
        item.detailList = sales;
        return expandableDetailList;
    }

    public static List<NavItem>navItemList(Context context){
        ArrayList<NavItem>itemList = new ArrayList<>();

        NavItem item = new NavItem();
        item.title = context.getString(R.string.dashboard);
        item.detailList = new ArrayList<>();
        itemList.add(item);


        List<String> sales = new ArrayList<String>();
        sales.add(context.getString(R.string.add_sales_services));
        sales.add(context.getString(R.string.list_sales));
        sales.add(context.getString(R.string.credit_note));
        sales.add(context.getString(R.string.add_debtors));

        item = new NavItem();
        item.title = context.getString(R.string.sale);
        item.detailList = sales;
        itemList.add(item);


        List<String> purchases = new ArrayList<String>();
        purchases.add(context.getString(R.string.add_sales_services));
        purchases.add(context.getString(R.string.list_sales));
        purchases.add(context.getString(R.string.credit_note));
        purchases.add(context.getString(R.string.add_debtors));

        item = new NavItem();
        item.title = context.getString(R.string.purchase);
        item.detailList = purchases;
        itemList.add(item);

        List<String> items = new ArrayList<String>();
        items.add(context.getString(R.string.add_item));
        items.add(context.getString(R.string.add_services));
        items.add(context.getString(R.string.add_category));
        items.add(context.getString(R.string.manage_unit));
        items.add(context.getString(R.string.manage_brands));
        items.add(context.getString(R.string.stock_converters));
        items.add(context.getString(R.string.manage_tax_classes));
        item = new NavItem();
        item.title = context.getString(R.string.item_service);
        item.detailList = items;
        itemList.add(item);


        List<String> bank_cash = new ArrayList<String>();
        bank_cash.add(context.getString(R.string.manage_bank));
        bank_cash.add(context.getString(R.string.cash_opening));
        bank_cash.add(context.getString(R.string.manage_cash));
        bank_cash.add(context.getString(R.string.cash_to_bank));
        bank_cash.add(context.getString(R.string.bank_to_cash));
        item = new NavItem();
        item.title = context.getString(R.string.bank_cash);
        item.detailList = bank_cash;
        itemList.add(item);

        item = new NavItem();
        item.title = context.getString(R.string.payment);
        item.detailList = items;
        itemList.add(item);

        item = new NavItem();
        item.title = context.getString(R.string.receipt);
        item.detailList = items;
        itemList.add(item);

        item = new NavItem();
        item.title = context.getString(R.string.category);
        item.detailList = items;
        itemList.add(item);

        item = new NavItem();
        item.title = context.getString(R.string.settings);
        item.detailList = new ArrayList<>();
        itemList.add(item);

        item = new NavItem();
        item.title = context.getString(R.string.receipt);
        item.detailList = new ArrayList<>();
        itemList.add(item);

        item = new NavItem();
        item.title = context.getString(R.string.reports);
        item.detailList = new ArrayList<>();
        itemList.add(item);

        return itemList;
    }
}
