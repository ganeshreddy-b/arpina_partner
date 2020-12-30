package com.sanjay.androidamcservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sanjay.androidamcservice.ui.main.BusinessDetailsFormFragment;

public class BusinessDetailsForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_details_form_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, BusinessDetailsFormFragment.newInstance())
                    .commitNow();
        }
    }
}