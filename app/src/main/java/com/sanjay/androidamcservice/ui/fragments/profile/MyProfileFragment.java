package com.sanjay.androidamcservice.ui.fragments.profile;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sanjay.androidamcservice.R;
import com.sanjay.androidamcservice.databinding.FragmentMyProfileBinding;
import com.sanjay.androidamcservice.utils.AppSharedPreference;


public class MyProfileFragment extends Fragment {

    FragmentMyProfileBinding binding;
AppSharedPreference appSharedPreference;
    public MyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false);
        appSharedPreference = new AppSharedPreference(getActivity());
        View root = binding.getRoot();
        Glide.with(this).load(appSharedPreference.getProfileImageKey()).into(binding.IVprofileImg);
        binding.TVemail.setText(appSharedPreference.getEmailKey());
        binding.TVmyprofilePhone.setText(appSharedPreference.getPhoneKey());
        return root;
    }
}