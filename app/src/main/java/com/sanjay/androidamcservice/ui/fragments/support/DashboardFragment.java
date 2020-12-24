package com.sanjay.androidamcservice.ui.fragments.support;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sanjay.androidamcservice.R;
import com.sanjay.androidamcservice.databinding.FragmentDashboardBinding;
import com.sanjay.androidamcservice.ui.activities.ChatActivity;
import com.sanjay.androidamcservice.utils.AppSharedPreference;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    FragmentDashboardBinding binding;
    AppSharedPreference appSharedPreference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        appSharedPreference = new AppSharedPreference(getActivity());
        View root = binding.getRoot();
        binding.btnchatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });
        return root;
    }
}