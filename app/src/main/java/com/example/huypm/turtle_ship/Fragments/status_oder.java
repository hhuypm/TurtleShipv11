package com.example.huypm.turtle_ship.Fragments;

import android.graphics.Color;
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
import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.DonHangForShipper;
import com.example.huypm.turtle_ship.model.DonHangFullInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class status_oder extends Fragment {
    private  Button btn_daship;
    private  Button btn_nhanship;
    private  Boolean statusbt1=true;
    private  ListView lv_shipper;
    ArrayList<DonHangFullInfo> mangdonhang1,mangdonhang2;
    ADapter2 adapterInfoDonHang;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Quản lý đơn hàng");
        lv_shipper = view.findViewById(R.id.lv_quanlydonhang);
        mangdonhang2 = new ArrayList<DonHangFullInfo>();
        btn_nhanship = view.findViewById(R.id.btn_nhanship);
        btn_daship = view.findViewById(R.id.btn_daship);
        final EditText et =  view.findViewById(R.id.et_search);
        final Bundle bundle = getArguments();



        Button btn_search = (Button) view.findViewById(R.id.bt_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangdonhang1.size()>0){
                    mangdonhang2.clear();

                    for (int i = 0;i<mangdonhang1.size();i++){
                        if (mangdonhang1.get(i).getId().contains(et.getText().toString())){
                            mangdonhang2.add(mangdonhang1.get(i));
                        };
                    }
                }
                if (mangdonhang2!=null){
                    adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang2);
                    lv_shipper.setAdapter(adapterInfoDonHang);
                }
            }
        });



        btn_nhanship.setBackgroundColor(getResources().getColor(R.color.green));

        btn_nhanship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_shipper.setAdapter(null);
                et.setText("");
                if(statusbt1==false) {
                    btn_nhanship.setBackgroundColor(getResources().getColor(R.color.green));
                    btn_daship.setBackgroundColor(getResources().getColor(R.color.white));
                }
                statusbt1=true;
                Bundle bundle = getArguments();

                //Đây iD shipper
                String IdShipper = String.valueOf(bundle.getInt("ID"));
                DataClient getDonHangOfShipper = APIManagerment.getData();
                Call<List<DonHangFullInfo>> callback = getDonHangOfShipper.getDonHangOfShipper(IdShipper);
                callback.enqueue(new Callback<List<DonHangFullInfo>>() {
                    @Override
                    public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                        ArrayList<DonHangFullInfo> mangdonhang = (ArrayList<DonHangFullInfo>) response.body();
                        mangdonhang1 = mangdonhang;
                        mangdonhang2 = mangdonhang1;
                        if (mangdonhang.size() >0){
                            adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang);
                            lv_shipper.setAdapter(adapterInfoDonHang);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                        Log.d("donhang",t.getMessage());


                    }
                });
            }
        });

        btn_daship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_shipper.setAdapter(null);
                et.setText("");
                if(statusbt1==true) {
                    btn_nhanship.setBackgroundColor(getResources().getColor(R.color.white));
                    btn_daship.setBackgroundColor(getResources().getColor(R.color.green));
                }
                statusbt1=false;
                Bundle bundle = getArguments();

                //Đây iD shipper
                String IdShipper = String.valueOf(bundle.getInt("ID"));
                DataClient getDonHangShipped = APIManagerment.getData();
                Call<List<DonHangFullInfo>> callback = getDonHangShipped.getDonHangShipped(IdShipper);
                callback.enqueue(new Callback<List<DonHangFullInfo>>() {
                    @Override
                    public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                        ArrayList<DonHangFullInfo> mangdonhang = (ArrayList<DonHangFullInfo>) response.body();
                        mangdonhang1 = mangdonhang;
                        mangdonhang2 = mangdonhang1;
                        if (mangdonhang.size() >0){
                            adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang);
                            lv_shipper.setAdapter(adapterInfoDonHang);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                        Log.d("donhang",t.getMessage());


                    }
                });
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.status_oder,container,false);
        lv_shipper = view.findViewById(R.id.lv_quanlydonhang);
        final Bundle bundle = getArguments();
        String IdShipper = String.valueOf(bundle.getInt("ID"));
        Log.d("Idshipper",IdShipper);
        DataClient getDonHangOfShipper = APIManagerment.getData();
        Call<List<DonHangFullInfo>> callback = getDonHangOfShipper.getDonHangOfShipper(IdShipper);
        callback.enqueue(new Callback<List<DonHangFullInfo>>() {
            @Override
            public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                ArrayList<DonHangFullInfo> mangdonhang = (ArrayList<DonHangFullInfo>) response.body();
                mangdonhang1 = mangdonhang;
                mangdonhang2 = mangdonhang1;
                if (mangdonhang.size() >0){
                    adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang);
                    lv_shipper.setAdapter(adapterInfoDonHang);
                }
            }

            @Override
            public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                Log.d("mang",t.getMessage());
            }
        });
        lv_shipper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
