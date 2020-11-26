package com.sanjay.androidamcservice.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.gson.Gson;
import com.sanjay.androidamcservice.R;
import com.sanjay.androidamcservice.app.Constants;
import com.sanjay.androidamcservice.repository.dto.LoginResponse;
import com.sanjay.androidamcservice.utils.Logger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {
    Button cirLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cirLoginButton = findViewById(R.id.cirLoginButton);

        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                login();
            }
        });

    }

    private void login() {
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), "test1@gmail.com");
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), "123456");
        Call<LoginResponse> loginCall = Constants.apiInterface.LOGIN_CALL(email, password);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });


    }


}