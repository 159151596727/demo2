package com.example.util;

public class DataMaps {
    static DataMaps instance;
    public static final String LOGIN_USER = "loginUser";//获取用户常量




    public static  DataMaps getInstance(){
        if (instance == null){
            instance = new DataMaps();
        }
        return  instance;
    }
}
