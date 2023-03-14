package com.itax.billbuddies.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itax.billbuddies.activities.ListA;
import com.itax.billbuddies.controller.BarGraph;
import com.itax.billbuddies.controller.Dashboard;
import com.itax.billbuddies.controller.ImageSlider;
import com.itax.billbuddies.databinding.FragmentHomeBinding;
import com.itax.billbuddies.dialog.ListDialog;
import com.itax.billbuddies.dialog.SalesTypeDialog;
import com.itax.billbuddies.models.ListItem;
import com.itax.billbuddies.R;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.SessionManager;

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
        return view;
    }

    private void initView(){
        new Dashboard(getActivity(),view);
        new ImageSlider(getActivity(), binding.imageSlider);
        new ImageSlider(getActivity(), binding.imageSlider2);
        new BarGraph(getActivity(),binding.barchart);
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