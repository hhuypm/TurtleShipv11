package com.example.huypm.turtle_ship.Service;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.huypm.turtle_ship.model.Customer_Employee;
import com.example.huypm.turtle_ship.model.DiaChi;
import com.example.huypm.turtle_ship.model.Users;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class TurtleShipDBManagerment {
    public int maxidUser = -1;
    public int maxidCus_Emp = -1;
    public int maxidAddress = -1;
    public void getMaxIdUser(Context ct){
        RequestQueue requestQueue = Volley.newRequestQueue(ct);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkGetMaxIdUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.trim().equals("")){
                    maxidUser = Integer.valueOf(response);
                    Log.d("iduser",response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("test","Loi");
            }
        });
        requestQueue.add(stringRequest);
    }
    public void AddUsers(String url, Context ct, final Users users){
        RequestQueue requestQueue = Volley.newRequestQueue(ct);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Ok")){
                    Log.d("ok","ok");
                }else {
                    Log.d("ok","not"+response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ok","Loi");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id",String.valueOf(users.getId()));
                params.put("Cus_Emp",String.valueOf(users.getCus_Emp()));
                params.put("Pass",users.getPass().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public  void getMaxidCus_Emp(Context ct){
        RequestQueue requestQueue = Volley.newRequestQueue(ct);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkGetMaxIdEmp_Cus, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.trim().equals("")){
                    maxidCus_Emp = Integer.valueOf(response);
                    Log.d("idcus_emp",response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("test","Loi");
            }
        });
        requestQueue.add(stringRequest);
    }
    public  void AddCus_Emp(String url, Context ct, final Customer_Employee cus_emp){
        RequestQueue requestQueue = Volley.newRequestQueue(ct);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Ok")){
                    Log.d("ok","ok");
                }else {
                    Log.d("ok","not");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ok","Loi");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Id",String.valueOf(cus_emp.getId()));
                params.put("Ten",cus_emp.getTen());
                params.put("SDT",cus_emp.getSDT().trim());
                params.put("Email",cus_emp.getEmail().trim());
                params.put("NV",String.valueOf(cus_emp.getNV()));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public  void getMaxidAddress(Context ct){
        RequestQueue requestQueue = Volley.newRequestQueue(ct);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkGetMaxIdAddress, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.trim().equals("")){
                    maxidAddress = Integer.valueOf(response);
                    Log.d("idaddress",response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("test","Loi");
            }
        });
        requestQueue.add(stringRequest);
    }


}
