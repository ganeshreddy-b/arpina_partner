package com.sanjay.androidamcservice.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewbinding.ViewBinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;
import com.sanjay.androidamcservice.R;
import com.sanjay.androidamcservice.app.Constants;
import com.sanjay.androidamcservice.databinding.ActivityLoginBinding;
import com.sanjay.androidamcservice.repository.dto.LoginResponse;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //    cirLoginButton = findViewById(R.id.btnRequestOTP);

        binding.btnRequestOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editTextPhone.setEnabled(false);
                binding.otpView.setVisibility(View.VISIBLE);
                binding.btnRequestOTP.setVisibility(View.GONE);
//                login();

            }
        });
        binding.otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                // do Stuff
                Log.d("onOtpCompleted=>", otp);
            }
        });
    }


    private void login() {
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), "test1@gmail.com");
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), "123456");
        Call<LoginResponse> loginCall = Constants.apiInterface.LOGIN_CALL(email, password);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }


}