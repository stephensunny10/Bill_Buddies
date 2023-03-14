package com.itax.billbuddies.layout;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class CustomLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled;

    // orientation should be LinearLayoutManager.VERTICAL or HORIZONTAL
    public CustomLayoutManager(Context context, int orientation, boolean isScrollEnabled) {
        super(context, orientation, false);
        this.isScrollEnabled = isScrollEnabled;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}