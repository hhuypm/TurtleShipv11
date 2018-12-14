package com.example.huypm.turtle_ship.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.Customer_Employee;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class account extends Fragment {
    Switch switchCompat;
    EditText passPre, passNew, passRep;
    String matkhau;
    String matkhaumoi;
    String nhaplaimatkhau;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("TÀI KHOẢN");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.account,container,false);
        final Bundle bundle = getArguments();
        final DataClient getInfo = APIManagerment.getData();
        final Call<List<Customer_Employee>> callback = getInfo.getCusEmpInfo(String.valueOf(bundle.getInt("ID")));
        callback.enqueue(new Callback<List<Customer_Employee>>() {
            @Override
            public void onResponse(Call<List<Customer_Employee>> call, Response<List<Customer_Employee>> response) {
                ArrayList<Customer_Employee> mangthongtin = (ArrayList<Customer_Employee>) response.body();
                EditText et_name_edit = (EditText) view.findViewById(R.id.textName);
                et_name_edit.setText(mangthongtin.get(0).getTen());
                EditText et_mail_edit = (EditText) view.findViewById(R.id.textMail);
                et_mail_edit.setText(mangthongtin.get(0).getEmail());
                EditText et_phone_edit = (EditText) view.findViewById(R.id.textPhone);
                et_phone_edit.setText(mangthongtin.get(0).getSDT());


            }

            @Override
            public void onFailure(Call<List<Customer_Employee>> call, Throwable t) {

            }
        });
        final Button btn_xacnhan = (Button) view.findViewById(R.id.btn_update_user);
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matkhau = passPre.getText().toString();
                matkhaumoi = passNew.getText().toString();
                nhaplaimatkhau = passPre.getText().toString();
                Bundle bundle = getArguments();
                DataClient dataClient = APIManagerment.getData();
                Call<String> callback1 = dataClient.update_cus("Ten", "Email", "SDT");
                callback1.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String uploadthongtin = response.body();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
        return view;
    }
}



