package com.sanjay.androidamcservice.repository.online;

import com.sanjay.androidamcservice.repository.dto.LoginResponse;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @POST("api/login/")
    @Multipart
    Call<LoginResponse> LOGIN_CALL(@Part("email") RequestBody email,
                                   @Part("password") RequestBody password);
}
