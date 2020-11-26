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

    public static final String ACTION_LOGGED_IN = "ejabberd.loggedin";
    public static final String SEND_MESSAGE = "com.ejabberd.oelp.sendmessage";
    public static final String BUNDLE_MESSAGE_BODY = "b_body";
    public static final String BUNDLE_TO = "b_to";
    public static final String NEW_MESSAGE = "com.ejabberd.oelp.newmessage";
    public static final String BUNDLE_FROM_JID = "b_from";
    public static final String CHAT_PASSWORD = "123456";
    public static final String USER_NAME = "k";
    //    public static String HOST = "3.86.245.10";
//    public static String HOST = "http://192.169.29.110";
    public static String HOST = "http://10.0.2.2";
//    public static String HOST = "206.189.136.186";
    public static DatabaseHandlerClass databaseHandlerClass = new DatabaseHandlerClass(MyApplication.getContext());
    public static ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
}
