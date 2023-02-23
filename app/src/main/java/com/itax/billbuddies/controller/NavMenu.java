package com.itax.billbuddies.controller;

import android.content.Context;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.itax.billbuddies.adapter.NavItemAdapter;
import com.itax.billbuddies.models.NavItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NavMenu {
    Context context;
    ExpandableListView expandableListViewExample;
    NavItemAdapter expandableListAdapter;
    List<String> expandableTitleList = new ArrayList<>();
    HashMap<String, List<String>> expandableDetailList = new HashMap<>();
    ArrayList<NavItem>itemList = new ArrayList<>();
    String TAG = "NavMenu";



    public NavMenu(Context context, ExpandableListView expandableListView) {
        this.context = context;
        this.expandableListViewExample = expandableListView;

        initView();
    }

    private void initView(){
//        expandableDetailList = NavMenuData.getData(context);
//        expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
        getNavData();
        expandableListAdapter = new NavItemAdapter(context, expandableTitleList, expandableDetailList);
        expandableListViewExample.setAdapter(expandableListAdapter);

        // This method is called when the group is expanded
        expandableListViewExample.setOnGroupExpandListener(groupPosition -> {
            //Toast.makeText(context, expandableTitleList.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
        });

        // This method is called when the group is collapsed
        expandableListViewExample.setOnGroupCollapseListener(groupPosition -> {
            //Toast.makeText(context, expandableTitleList.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
        });

        // This method is called when the child in any group is clicked
        // via a toast method, it is shown to display the selected child item as a sample
        // we may need to add further steps according to the requirements
        expandableListViewExample.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Toast.makeText(context, expandableTitleList.get(groupPosition)
                    + " -> "
                    + expandableDetailList.get(
                    expandableTitleList.get(groupPosition)).get(
                    childPosition), Toast.LENGTH_SHORT
            ).show();
            return false;
        });
    }

    private void getNavData(){
        itemList.addAll(NavMenuData.navItemList(context));
        Log.d(TAG, "getNavData: "+ itemList.size());

        for (int i=0; i<itemList.size(); i++){
            Log.d(TAG, "getNavData: "+ itemList.get(i).title);
            expandableTitleList.add(itemList.get(i).title);
            expandableDetailList.put(itemList.get(i).title,itemList.get(i).detailList);
        }
    }

}
