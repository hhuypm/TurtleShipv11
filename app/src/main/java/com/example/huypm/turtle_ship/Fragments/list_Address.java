package com.example.huypm.turtle_ship.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.huypm.turtle_ship.R;

public class list_Address extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Danh sách địa chỉ");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.address_list,container,false);
        Button btn_sua_address = (Button) view.findViewById(R.id.btn_sua_address);
        btn_sua_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new update_address();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft =  fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.content_main,fragment);

                ft.commit();
            }
        });
        Fragment fm = new update_address();

        return view;
    }




}
