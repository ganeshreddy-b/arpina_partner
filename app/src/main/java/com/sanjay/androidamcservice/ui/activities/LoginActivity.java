package com.sanjay.androidamcservice.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.mukesh.OnOtpCompletionListener;
import com.sanjay.androidamcservice.R;
import com.sanjay.androidamcservice.app.Constants;
import com.sanjay.androidamcservice.databinding.ActivityLoginBinding;
import com.sanjay.androidamcservice.repository.dto.ValidatePhoneResponse;
import com.sanjay.androidamcservice.repository.dto.login.LoginPhoneResponse;
import com.sanjay.androidamcservice.utils.AppSharedPreference;

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
    AppSharedPreference appSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //    cirLoginButton = findViewById(R.id.btnRequestOTP);
        appSharedPreference = new AppSharedPreference(this);
        binding.btnRequestOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.editTextPhone.setEnabled(false);
                binding.otpView.setVisibility(View.VISIBLE);
                binding.btnRequestOTP.setVisibility(View.GONE);
//                login();
                validatePhone();

            }
        });
        binding.otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
//                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                // do Stuff
                Log.d("onOtpCompleted=>", otp);
                validateOtp(otp);
            }
        });
    }


    private void validatePhone() {
        String phone = binding.editTextPhone.getText().toString();
        Call<ValidatePhoneResponse> validatePhoneResponseCall = Constants.apiInterface.LOGIN_RESPONSE_CALL(phone);
        validatePhoneResponseCall.enqueue(new Callback<ValidatePhoneResponse>() {
            @Override
            public void onResponse(Call<ValidatePhoneResponse> call, Response<ValidatePhoneResponse> response) {
//                if (response.isSuccessful()) {
//                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
//                }
            }

            @Override
            public void onFailure(Call<ValidatePhoneResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"error"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateOtp(String otp) {
        String phone = binding.editTextPhone.getText().toString();

        Call<LoginPhoneResponse> validate_otp = Constants.apiInterface.VALIDATE_OTP_LOGIN(phone, otp);
        validate_otp.enqueue(new Callback<LoginPhoneResponse>() {
            @Override
            public void onResponse(Call<LoginPhoneResponse> call, Response<LoginPhoneResponse> response) {
                if (response.isSuccessful()) {
                    appSharedPreference.setFirstNameKey(response.body().getUser().getFirstName());
                    appSharedPreference.setLastNameKey(response.body().getUser().getLastName());
                    appSharedPreference.setPhoneKey(response.body().getUser().getPhone());
                    appSharedPreference.setEmailKey(response.body().getUser().getEmail());
                    appSharedPreference.setTokenKey(response.body().getToken());
                    appSharedPreference.setAboutmeKey(response.body().getUser().getAboutMe());
                    appSharedPreference.setAddressKey(response.body().getUser().getAddress());
                    appSharedPreference.setBirthKey(response.body().getUser().getBirthDate());
                    appSharedPreference.setCityKey(response.body().getUser().getCity());
                    appSharedPreference.setUsernameKey(response.body().getUser().getUsername());
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                }
            }

            @Override
            public void onFailure(Call<LoginPhoneResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"error"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}