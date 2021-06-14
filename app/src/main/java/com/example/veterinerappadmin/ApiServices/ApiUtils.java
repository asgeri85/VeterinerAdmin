package com.example.veterinerappadmin.ApiServices;

public class ApiUtils {
    public static final String URL="http://microwebservice.net/ecodation/veteriner/";
    public static AdminDao getDao(){
        return Retrofitclient.getRetrofit(URL).create(AdminDao.class);
    }
}
