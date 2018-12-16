package com.example.huypm.turtle_ship.Fragments;

import android.graphics.Color;
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
import android.widget.ListView;

import com.example.huypm.turtle_ship.ADapter.ADapter2;
import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.DonHangForShipper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class status_oder extends Fragment {
    private  Button btn_daship;
    private  Button btn_nhanship;
    private  Boolean statusbt1=true;
    private  ListView lv;
    ArrayList<DonHangForShipper> mangdonhang1,mangdonhang2;
    ADapter2 adapterInfoDonHang;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Quản lý đơn hàng");
        lv = view.findViewById(R.id.lv_quanlydonhang);
        btn_nhanship = view.findViewById(R.id.btn_nhanship);
        btn_daship = view.findViewById(R.id.btn_daship);
        final EditText et =  view.findViewById(R.id.et_search);
        Bundle bundle = getArguments();

        //Đây iD shipper
        String IdShipper = String.valueOf(bundle.getInt("ID"));
        DataClient getDonHangOfShipper = APIManagerment.getData();
        Call<List<DonHangForShipper>> callback = getDonHangOfShipper.getDonHangOfShipper(IdShipper);
        callback.enqueue(new Callback<List<DonHangForShipper>>() {
            @Override
            public void onResponse(Call<List<DonHangForShipper>> call, Response<List<DonHangForShipper>> response) {
                ArrayList<DonHangForShipper> mangdonhang = (ArrayList<DonHangForShipper>) response.body();
                mangdonhang1 = mangdonhang;
                if (mangdonhang.size() >0){
                    adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang);
                    lv.setAdapter(adapterInfoDonHang);
                }

            }

            @Override
            public void onFailure(Call<List<DonHangForShipper>> call, Throwable t) {
                Log.d("donhang",t.getMessage());


            }
        });

        Button btn_search = (Button) view.findViewById(R.id.bt_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangdonhang1.size()>0){
                    mangdonhang2 = new ArrayList<DonHangForShipper>();

                    for (int i = 0;i<mangdonhang1.size();i++){
                        if (mangdonhang1.get(i).getId().contains(et.getText().toString())){
                            mangdonhang2.add(mangdonhang1.get(i));
                        };
                    }
                }
                if (mangdonhang2!=null){
                    adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang2);
                    lv.setAdapter(adapterInfoDonHang);
                }
            }
        });



        btn_nhanship.setBackgroundColor(getResources().getColor(R.color.green));

        btn_nhanship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(statusbt1==false) {
                    btn_nhanship.setBackgroundColor(getResources().getColor(R.color.green));
                    btn_daship.setBackgroundColor(getResources().getColor(R.color.white));
                }
                statusbt1=true;
                Bundle bundle = getArguments();

                //Đây iD shipper
                String IdShipper = String.valueOf(bundle.getInt("ID"));
                DataClient getDonHangOfShipper = APIManagerment.getData();
                Call<List<DonHangForShipper>> callback = getDonHangOfShipper.getDonHangOfShipper(IdShipper);
                callback.enqueue(new Callback<List<DonHangForShipper>>() {
                    @Override
                    public void onResponse(Call<List<DonHangForShipper>> call, Response<List<DonHangForShipper>> response) {
                        ArrayList<DonHangForShipper> mangdonhang = (ArrayList<DonHangForShipper>) response.body();
                        mangdonhang1 = mangdonhang;
                        if (mangdonhang.size() >0){
                            adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang);
                            lv.setAdapter(adapterInfoDonHang);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<DonHangForShipper>> call, Throwable t) {
                        Log.d("donhang",t.getMessage());


                    }
                });
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
                Bundle bundle = getArguments();

                //Đây iD shipper
                String IdShipper = String.valueOf(bundle.getInt("ID"));
                DataClient getDonHangShipped = APIManagerment.getData();
                Call<List<DonHangForShipper>> callback = getDonHangShipped.getDonHangShipped(IdShipper);
                callback.enqueue(new Callback<List<DonHangForShipper>>() {
                    @Override
                    public void onResponse(Call<List<DonHangForShipper>> call, Response<List<DonHangForShipper>> response) {
                        ArrayList<DonHangForShipper> mangdonhang = (ArrayList<DonHangForShipper>) response.body();
                        mangdonhang1 = mangdonhang;
                        if (mangdonhang.size() >0){
                            adapterInfoDonHang = new ADapter2(getActivity(),1,mangdonhang);
                            lv.setAdapter(adapterInfoDonHang);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<DonHangForShipper>> call, Throwable t) {
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
        lv = view.findViewById(R.id.lv_quanlydonhang);
        Bundle bundle = getArguments();
        String IdShipper = String.valueOf(bundle.getInt("ID"));

        DataClient getDonHangShipped = APIManagerment.getData();
        Call<List<DonHangForShipper>> callback = getDonHangShipped.getDonHangShipped(IdShipper);
        callback.enqueue(new Callback<List<DonHangForShipper>>() {
            @Override
            public void onResponse(Call<List<DonHangForShipper>> call, Response<List<DonHangForShipper>> response) {
                ArrayList<DonHangForShipper> mangdonhang = (ArrayList<DonHangForShipper>) response.body();
                mangdonhang1 = mangdonhang;
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
