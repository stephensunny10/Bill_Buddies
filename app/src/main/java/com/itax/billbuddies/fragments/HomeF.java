package com.itax.billbuddies.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itax.billbuddies.activities.ListA;
import com.itax.billbuddies.adapter.SliderAdapter;
import com.itax.billbuddies.controller.Dashboard;
import com.itax.billbuddies.databinding.FragmentHomeBinding;
import com.itax.billbuddies.dialog.ListDialog;
import com.itax.billbuddies.dialog.SalesTypeDialog;
import com.itax.billbuddies.listener.ClickListener;
import com.itax.billbuddies.models.ListItem;
import com.itax.billbuddies.models.SliderItem;
import com.itax.billbuddies.R;
import com.itax.billbuddies.utils.Constants;


import com.itax.billbuddies.utils.SessionManager;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeF extends Fragment {
    FragmentHomeBinding binding;
    View view;
    SalesTypeDialog salesTypeDialog;
    ArrayList<ListItem>assessmentList = new ArrayList<>();
    ListDialog dialog;
    SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        view = binding.getRoot();

        sessionManager = new SessionManager(getActivity());
        initView();
        setSlider();
        setSlider2();
        return view;
    }

    private void setSlider2(){
        String url1 = "https://th.bing.com/th/id/OIP.30vi5UpS1wQ2KPkqO-d04QHaEy?pid=ImgDet&rs=1";
        String url2 = "https://th.bing.com/th/id/OIP.TwPiC7rl5EqBwMQXZoFI_gHaHa?pid=ImgDet&w=860&h=860&rs=1";
        String url3 = "https://en.wikipedia.org/wiki/City#/media/File:Taj_Hotel,_Mumbai_-_India._(14132561875).jpg";
        String url4 = "https://th.bing.com/th/id/OIP.30vi5UpS1wQ2KPkqO-d04QHaEy?pid=ImgDet&rs=1";
        String url5 = "https://th.bing.com/th/id/OIP.TwPiC7rl5EqBwMQXZoFI_gHaHa?pid=ImgDet&w=860&h=860&rs=1";
        String url6 = "https://en.wikipedia.org/wiki/City#/media/File:Taj_Hotel,_Mumbai_-_India._(14132561875).jpg";

        ArrayList<SliderItem> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderItem(url1));
        sliderDataArrayList.add(new SliderItem(url2));
        sliderDataArrayList.add(new SliderItem(url3));
        sliderDataArrayList.add(new SliderItem(url4));
        sliderDataArrayList.add(new SliderItem(url5));
        sliderDataArrayList.add(new SliderItem(url6));
        SliderAdapter adapter= new SliderAdapter(getActivity(),sliderDataArrayList);
        binding.imageSlider2.setSliderAdapter(adapter);

        binding.imageSlider2.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider2.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider2.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.imageSlider2.setIndicatorSelectedColor(Color.WHITE);
        binding.imageSlider2.setIndicatorUnselectedColor(Color.GRAY);
        binding.imageSlider2.setScrollTimeInSec(4); //set scroll delay in seconds :
        binding.imageSlider2.startAutoCycle();
    }
    private void setSlider(){
        String url1 = "https://th.bing.com/th/id/OIP.30vi5UpS1wQ2KPkqO-d04QHaEy?pid=ImgDet&rs=1";
        String url2 = "https://th.bing.com/th/id/OIP.TwPiC7rl5EqBwMQXZoFI_gHaHa?pid=ImgDet&w=860&h=860&rs=1";
        String url3 = "https://en.wikipedia.org/wiki/City#/media/File:Taj_Hotel,_Mumbai_-_India._(14132561875).jpg";
        String url4 = "https://th.bing.com/th/id/OIP.30vi5UpS1wQ2KPkqO-d04QHaEy?pid=ImgDet&rs=1";
        String url5 = "https://th.bing.com/th/id/OIP.TwPiC7rl5EqBwMQXZoFI_gHaHa?pid=ImgDet&w=860&h=860&rs=1";
        String url6 = "https://en.wikipedia.org/wiki/City#/media/File:Taj_Hotel,_Mumbai_-_India._(14132561875).jpg";

        ArrayList<SliderItem> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderItem(url1));
        sliderDataArrayList.add(new SliderItem(url2));
        sliderDataArrayList.add(new SliderItem(url3));
        sliderDataArrayList.add(new SliderItem(url4));
        sliderDataArrayList.add(new SliderItem(url5));
        sliderDataArrayList.add(new SliderItem(url6));
        SliderAdapter adapter= new SliderAdapter(getActivity(),sliderDataArrayList);
        binding.imageSlider.setSliderAdapter(adapter);
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        binding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        binding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        binding.imageSlider.startAutoCycle();}

    private void initView(){
        new Dashboard(getActivity(),view);
        salesTypeDialog = new SalesTypeDialog(getContext());

        binding.txtChangeAssementYear.setOnClickListener(v->{
            dialog.showDialog();
        });
        initListDialog();

        if (!sessionManager.getAssessmentYear().isEmpty()){
            binding.txtAssessmentYear.setText(sessionManager.getAssessmentYear());
        }
    }

    private void moveToSales(){
        startActivity(new Intent(getActivity(), ListA.class).putExtra(Constants.page_name,Constants.purchase));
    }

    private void initListDialog(){
        addAssessmentYear();
        dialog = new ListDialog(getActivity(), assessmentList, position -> {
            sessionManager.setAssessmentYear(assessmentList.get(position).title);
            binding.txtAssessmentYear.setText(assessmentList.get(position).title);
        });
    }

    private void addAssessmentYear(){
        assessmentList.add(new ListItem("1","2017-2018",""));
        assessmentList.add(new ListItem("1","2018-2019",""));
        assessmentList.add(new ListItem("1","2019-2020",""));
        assessmentList.add(new ListItem("1","2020-2021",""));
        assessmentList.add(new ListItem("1","2022-2023",""));
        assessmentList.add(new ListItem("1","2023-2024",""));
        assessmentList.add(new ListItem("1","2024-2025",""));
    }
}