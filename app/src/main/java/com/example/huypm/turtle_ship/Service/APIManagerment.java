package com.example.huypm.turtle_ship.Service;

public class APIManagerment {
    public static final String Base_Url = "http://turtleship.atspace.cc/server/";
    public static DataClient getData(){
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}
