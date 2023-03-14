package com.itax.billbuddies.controller;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.itax.billbuddies.adapter.ImageSliderAdapter;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.models.BannerItem;
import com.itax.billbuddies.models.SliderItem;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class ImageSlider {
    Context context;
    View view;
    SliderView sliderView;
    ImageSliderAdapter adapter;


    public ImageSlider(Context context, SliderView sliderView) {
        this.context = context;
        this.sliderView = sliderView;
        initView();
    }

    private void initView(){
        adapter = new ImageSliderAdapter(context);
        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        addItem();
    }

    private void addItem(){
        SliderItem item = new SliderItem();
        item.setImageUrl("http://fastbillingsoftware.com/images/Inventory-banner.jpg");
        adapter.addItem(item);

        item = new SliderItem();
        item.setImageUrl("https://www.versionx.in/wp-content/uploads/2020/04/Inventory-Management-Web-Responsive-Banner_1320x500.jpg");
        adapter.addItem(item);

        item = new SliderItem();
        item.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgCDsdeHy7e6vI4XBjO6vXGoKB25min4D6jKKFhq6SoxjAqTrTQ73oxGSOpcPvwVWE0KA&usqp=CAU");
        adapter.addItem(item);
    }

}
