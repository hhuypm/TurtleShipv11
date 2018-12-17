package com.example.huypm.turtle_ship.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.huypm.turtle_ship.ADapter.ADapter2;
import com.example.huypm.turtle_ship.ADapter.Adapter;
import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.Customer_Employee;
import com.example.huypm.turtle_ship.model.DonHangForShipper;
import com.example.huypm.turtle_ship.model.DonHangFullInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class status_oder1 extends Fragment {
    ADapter2 adapterInfoDonHang;
    ListView lv;
    ArrayList<DonHangFullInfo> mangdonhang1,mangdonhang2;
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
        final Bundle bundle = getArguments();
        final String Idshipper = String.valueOf(bundle.getInt("ID"));
        DataClient getDonHangShipper = APIManagerment.getData();
        Call<List<DonHangFullInfo>> callback = getDonHangShipper.getDonHangShipper();
        callback.enqueue(new Callback<List<DonHangFullInfo>>() {
            @Override
            public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                ArrayList<DonHangFullInfo> mangdonhang = (ArrayList<DonHangFullInfo>) response.body();
                mangdonhang1 = mangdonhang;
                mangdonhang2 = mangdonhang1;
                for (int i = 0; i<mangdonhang2.size();i++){
                    mangdonhang2.get(i).setShipper(Idshipper);
                    mangdonhang2.get(i).setTrangThai("-1");
                }
                if (mangdonhang.size() >0){
                    adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang2);
                    lv.setAdapter(adapterInfoDonHang);
                }

            }

            @Override
            public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {

            }
        });
        Button btn_search = (Button) view.findViewById(R.id.search_all);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangdonhang1.size()>0){
                    mangdonhang2 = new ArrayList<DonHangFullInfo>();
                    EditText et = (EditText) view.findViewById(R.id.et_search_all);

                    for (int i = 0;i<mangdonhang1.size();i++){
                        if (mangdonhang1.get(i).getId().contains(et.getText().toString())){
                            mangdonhang2.add(mangdonhang1.get(i));
                        };
                    }

                }
                if (mangdonhang2!=null){
                    for (int i = 0; i<mangdonhang2.size();i++){
                        mangdonhang2.get(i).setShipper(Idshipper);
                        mangdonhang2.get(i).setTrangThai("-1");
                    }
                    adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang2);
                    lv.setAdapter(adapterInfoDonHang);
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                fragment = new single_order_shipper();
                FragmentManager fm = getFragmentManager();
                bundle.putSerializable("ItemDonHang",mangdonhang2.get(position));
                fragment.setArguments(bundle);
                FragmentTransaction ft =  fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.content_main_shipper,fragment);
                ft.commit();
            }
        });


        return view;
    }
}
