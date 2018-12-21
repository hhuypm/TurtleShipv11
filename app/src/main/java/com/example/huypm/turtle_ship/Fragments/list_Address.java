package com.example.huypm.turtle_ship.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.huypm.turtle_ship.ADapter.Adapter_address;
import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.DiaChi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class list_Address extends Fragment {
    private ListView lv_address ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
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
        final Bundle bundle = getArguments();
        final ProgressDialog progress = ProgressDialog.show(getActivity(),
                "Tải thông tin", "Đợi 1 chút xíu....", false, true, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
        progress.show();
        lv_address = view.findViewById(R.id.lv_address);
        DataClient getAddress = APIManagerment.getData();
        retrofit2.Call<List<DiaChi>> callback = getAddress.getDiaChi(String.valueOf(bundle.getInt("ID")));
        callback.enqueue(new Callback<List<DiaChi>>() {
            @Override
            public void onResponse(retrofit2.Call<List<DiaChi>> call, Response<List<DiaChi>> response) {
                ArrayList<DiaChi> mangdiachi = (ArrayList<DiaChi>) response.body();
                Adapter_address adapterDiaChi = new Adapter_address(getActivity(),1,mangdiachi);
                lv_address.setAdapter(adapterDiaChi);
                progress.dismiss();
            }

            @Override
            public void onFailure(retrofit2.Call<List<DiaChi>> call, Throwable t) {
                Log.d("diachiloi",t.getMessage());
            }
        });
        FloatingActionButton btn_add_address = view.findViewById(R.id.add_address);
        btn_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new add_address();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft =  fm.beginTransaction();
                fragment.setArguments(bundle);
                ft.addToBackStack(null);
                ft.replace(R.id.content_main,fragment);
                ft.commit();
            }
        });


        return view;
    }






}
