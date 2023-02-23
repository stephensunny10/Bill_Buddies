package com.itax.billbuddies.activities.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.api.RequestApi;
import com.itax.billbuddies.databinding.FragmentBasicDetailBinding;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.models.ProfileModel;
import com.itax.billbuddies.R;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasicDetailF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasicDetailF extends Fragment implements ResponseListener {
    View view;
    FragmentBasicDetailBinding binding;
    Functions functions;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BasicDetailF() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasicDetailF.
     */
    // TODO: Rename and change types and number of parameters
    public static BasicDetailF newInstance(String param1, String param2) {
        BasicDetailF fragment = new BasicDetailF();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_basic_detail, container, false);
        binding = FragmentBasicDetailBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        functions= new Functions(getActivity());
        callprofileapi();
        return view;
    }
    private void callprofileapi(){
        HashMap<String,String> param = new HashMap<>();
        JSONObject object = new JSONObject();
        RequestApi api = new RequestApi(getActivity(),this);
        api.setMethod(Request.Method.GET);
        api.requestJsonGet(ApiList.ProfileUrl + "?LoginID="+ Constants.LOGIN_ID +"&CompanyId=" + "COM00000001",object,101);
        functions.showLoading();
    }

    @Override
    public void onResponse(int requestCode, String response) {
        functions.hideLoading();
        verifyAuth(response);
    }

    @Override
    public void onError(int requestCode, String error) {

    }
    private void verifyAuth(String response){
        ProfileModel model = new Gson().fromJson(response, ProfileModel.class);
        if (model.getCompanyModel()!=null){
        setdata(model);

        }}

    private void setdata(ProfileModel model) {
        binding.etBusinessAddress.setText(model.getCompanyModel().getCompany_Address());
        binding.etEmailId.setText(model.getCompanyModel().getCompany_Email());
        binding.etPhnNum.setText(model.getCompanyModel().getPf_no());
        binding.etbusinessName.setText(model.getCompanyModel().getCompany_Name());
        binding.etBusinessDiscription.setText(model.getCompanyModel().getNature_of_Business());
    }
}