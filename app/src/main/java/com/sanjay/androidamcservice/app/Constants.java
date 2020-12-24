/*
 * Copyright (c) 2019.
 * Project created and maintained by sanjay kranthi kumar
 * if need to contribute contact us on
 * kranthi0987@gmail.com
 */

package com.sanjay.androidamcservice.app;


import com.sanjay.androidamcservice.repository.offline.DatabaseHandlerClass;
import com.sanjay.androidamcservice.repository.online.ApiClient;
import com.sanjay.androidamcservice.repository.online.ApiInterface;

public class Constants {

    public static final int PORT = 5222;
    public static final int API_PORT = 8000;


    //    public static String HOST = "3.86.245.10";
//    public static String HOST = "http://192.169.29.110";
//    public static String HOST = "http://192.168.1.119";
    public static String HOST = "http://192.168.29.110";
    public static String KOMMUNICATE_APP_ID = "112056eaffdd25842a8d4f555d999c0ef";
//    public static String HOST = "206.189.136.186";
    public static DatabaseHandlerClass databaseHandlerClass = new DatabaseHandlerClass(MyApplication.getContext());
    public static ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
}
