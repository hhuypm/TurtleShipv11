package com.example.huypm.turtle_ship.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huypm.turtle_ship.OnFragmentManager;
import com.example.huypm.turtle_ship.R;

public class order_step2 extends Fragment {
    OnFragmentManager listener;
    EditText et_mota,et_dinhgia,et_KhoiLuong,et_soluong;
    CheckBox cb_sent,cb_receive;
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.order_step_2, container, false);
        Button btn_back = (Button) view.findViewById(R.id.btn_back_step);
        final Bundle bundle = getArguments();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });

        Button btn_step_3 = (Button) view.findViewById(R.id.btn_next_step_3);
        btn_step_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_dinhgia.getText().toString().trim().equals("") && et_KhoiLuong.getText().toString().trim().equals("") && et_mota.getText().toString().trim().equals("")&& et_soluong.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(), "Điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    Fragment fragment = new order_step3();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.addToBackStack(null);
                    Bundle bd = new Bundle();
                    bd = listener.onDataSelected(bundle, "et_mota", et_mota.getText().toString());
                    bd = listener.onDataSelected(bd, "et_dinhgia", et_dinhgia.getText().toString());
                    bd = listener.onDataSelected(bd, "et_KhoiLuong", et_KhoiLuong.getText().toString());
                    bd = listener.onDataSelected(bd, "et_soluong", et_soluong.getText().toString());
                    if (cb_sent.isChecked())
                        bd = listener.onDataSelected(bd, "hinhthuc", "1");
                    if (cb_receive.isChecked())
                        bd = listener.onDataSelected(bd, "hinhthuc", "2");
                    fragment.setArguments(bd
                    );

                    ft.replace(R.id.content_main, fragment);

                    ft.commit();
                }
            }
        });
        et_mota = view.findViewById(R.id.et_mota);
        et_dinhgia = view.findViewById(R.id.et_dinhgia);
        et_KhoiLuong = view.findViewById(R.id.et_KhoiLuong);
        et_soluong = view.findViewById(R.id.et_soluong);
        cb_sent = view.findViewById(R.id.cb_sent);
        cb_receive = view.findViewById(R.id.cb_receive);
        Log.d("test5",bundle.getString("tv_name_sent"));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentManager ){
            listener= (OnFragmentManager) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement onViewSelected");
        }
    }



}
