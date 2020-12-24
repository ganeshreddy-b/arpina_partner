package com.sanjay.androidamcservice.ui.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sanjay.androidamcservice.R;
import com.sanjay.androidamcservice.databinding.FragmentHomeBinding;
import com.sanjay.androidamcservice.ui.activities.QrcodeScanner;
import com.sanjay.androidamcservice.ui.fragments.profile.MyProfileFragment;
import com.sanjay.androidamcservice.utils.AppSharedPreference;


import java.io.IOException;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding binding;
    AppSharedPreference appSharedPreference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View root = binding.getRoot();
        appSharedPreference=new AppSharedPreference(getActivity());
        binding.cardScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QrcodeScanner.class));
            }
        });
        binding.cardViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyProfileFragment.class));
            }
        });
        binding.txtCompanyName.setText(appSharedPreference.getCompanyNameKey());
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return root;
    }
}