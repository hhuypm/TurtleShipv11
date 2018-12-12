package com.example.huypm.turtle_ship.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.huypm.turtle_ship.R;

public class status_oder extends Fragment {
    private  Button btn_daship;
    private  Button btn_nhanship;
    private  Boolean statusbt1=true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Quản lý đơn hàng");

        btn_nhanship = view.findViewById(R.id.btn_nhanship);
        btn_daship = view.findViewById(R.id.btn_daship);


        btn_nhanship.setBackgroundColor(getResources().getColor(R.color.green));

        btn_nhanship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(statusbt1==false) {
                    btn_nhanship.setBackgroundColor(getResources().getColor(R.color.green));
                    btn_daship.setBackgroundColor(getResources().getColor(R.color.white));
                }
                statusbt1=true;
            }
        });

        btn_daship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(statusbt1==true) {
                    btn_nhanship.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_daship.setBackgroundColor(getResources().getColor(R.color.green));
                }
                statusbt1=false;
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.status_oder,container,false);
        return view;
    }
}
