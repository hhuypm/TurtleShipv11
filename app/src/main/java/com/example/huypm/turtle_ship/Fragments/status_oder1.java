package com.example.huypm.turtle_ship.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.huypm.turtle_ship.ADapter.ADapter2;
import com.example.huypm.turtle_ship.ADapter.Adapter;
import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.Customer_Employee;
import com.example.huypm.turtle_ship.model.DonHangForShipper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class status_oder1 extends Fragment {
    ADapter2 adapterInfoDonHang;
    ListView lv;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Đơn hàng yêu cầu ship");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.status_oder1,container,false);
        lv = view.findViewById(R.id.lv_donhang_shipper);
        DataClient getDonHangShipper = APIManagerment.getData();
        Call<List<DonHangForShipper>> callback = getDonHangShipper.getDonHangShipper();
        callback.enqueue(new Callback<List<DonHangForShipper>>() {
            @Override
            public void onResponse(Call<List<DonHangForShipper>> call, Response<List<DonHangForShipper>> response) {
                ArrayList<DonHangForShipper> mangdonhang = (ArrayList<DonHangForShipper>) response.body();
                if (mangdonhang.size() >0){
                    adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang);
                    lv.setAdapter(adapterInfoDonHang);
                }

            }

            @Override
            public void onFailure(Call<List<DonHangForShipper>> call, Throwable t) {

            }
        });

        return view;
    }
}
