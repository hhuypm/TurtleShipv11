package com.example.huypm.turtle_ship.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huypm.turtle_ship.MainActivity;
import com.example.huypm.turtle_ship.MainContent;
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
    EditText et_name_edit,et_email_edit,et_phone_edit, et_pass_new_edit;
    Bundle bundle;
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
        bundle = getArguments();
        final String idcus = String.valueOf(bundle.getInt("ID"));
        DataClient getInfo = APIManagerment.getData();
        Log.d("id",String.valueOf(bundle.getInt("ID")));
        final Call<List<Customer_Employee>> callback = getInfo.getCusEmpInfo(String.valueOf(bundle.getInt("ID")));
        callback.enqueue(new Callback<List<Customer_Employee>>() {
            @Override
            public void onResponse(Call<List<Customer_Employee>> call, Response<List<Customer_Employee>> response) {
                ArrayList<Customer_Employee> mangthongtin = (ArrayList<Customer_Employee>) response.body();
                et_name_edit = (EditText) view.findViewById(R.id.textName);
                et_name_edit.setText(mangthongtin.get(0).getTen());
                et_email_edit = (EditText) view.findViewById(R.id.textMail);
                et_email_edit.setText(mangthongtin.get(0).getEmail());
                et_phone_edit = (EditText) view.findViewById(R.id.textPhone);
                et_phone_edit.setText(mangthongtin.get(0).getSDT());
            }

            @Override
            public void onFailure(Call<List<Customer_Employee>> call, Throwable t) {

            }
        });
        Button btn_xacnhan = (Button) view.findViewById(R.id.btn_update);
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClient updateUser = APIManagerment.getData();
                //update tên mail số điện thoại
                Call<String> callback1 = updateUser.updateCus(idcus,et_name_edit.getText().toString(),et_email_edit.getText().toString(),et_phone_edit.getText().toString());
                callback1.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        Log.d("result",result );
                        Toast toast=Toast.makeText(getActivity(), "Bạn đã thay đổi thông tin thành công",   Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("result",t.getMessage() );
                    }
                });
                //update pass
                et_pass_new_edit = (EditText) view.findViewById(R.id.textPassNew);
                Call<String> callback2 = updateUser.updatePass(idcus, et_pass_new_edit.getText().toString());
                callback2.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String newPass = response.body();
                        Toast.makeText(getActivity(), "Bạn đã thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
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
