package com.sanjay.androidamcservice.repository.online;

import com.sanjay.androidamcservice.repository.dto.ValidatePhoneResponse;
import com.sanjay.androidamcservice.repository.dto.login.LoginPhoneResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
//
//    @POST("api/login/")
//    @Multipart
//    Call<LoginResponse> LOGIN_CALL(@Part("email") RequestBody email,
//                                   @Part("password") RequestBody password);


    @POST("api/validate_phone/")
    @Multipart
    Call<ValidatePhoneResponse> VALIDATE_PHONE_RESPONSE_CALL(@Part("phone") String phone);


    @POST("api/validate_otp/")
    @Multipart
    Call<ValidatePhoneResponse> VALIDATE_OTP(@Part("phone") String phone, @Part("otp") String otp);


    @POST("api/validate_login_phone/")
    @Multipart
    Call<ValidatePhoneResponse> LOGIN_RESPONSE_CALL(@Part("phone") RequestBody phone);


    @POST("api/validate_login_otp/")
    @Multipart
    Call<LoginPhoneResponse> VALIDATE_OTP_LOGIN(@Part("phone") RequestBody phone, @Part("otp") RequestBody otp);


    @POST("api/register/")
    @Multipart
    Call<ValidatePhoneResponse> REGISTER(@Part("phone") RequestBody phone,
                                         @Part("username") RequestBody username,
                                         @Part("email") RequestBody email,
                                         @Part("first_name") RequestBody first_name,
                                         @Part("last_name") RequestBody last_name,
                                         @Part("receive_newsletter") RequestBody receive_newsletter,
                                         @Part("birth_date") RequestBody birthdate,
                                         @Part("address") RequestBody address,
                                         @Part("city") RequestBody city,
                                         @Part("about_me") RequestBody about_me,
                                         @Part("profile_image") MultipartBody.Part profile_image
    );




}
