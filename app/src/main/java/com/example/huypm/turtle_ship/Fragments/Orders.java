package com.example.huypm.turtle_ship.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.huypm.turtle_ship.ADapter.Adapter;

import com.example.huypm.turtle_ship.OnFragmentManager;
import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.DonHangFullInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Orders extends Fragment  {
    private List<Cursor> items;
    Adapter adapterInfoDonHang;
    ListView lv;
    OnFragmentManager listener;
    ArrayList<DonHangFullInfo> manginfodonhang = null;
    String idcus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.oders,container,false);

        final Bundle bundle = getArguments();
        idcus = String.valueOf(bundle.getInt("ID"));

        lv = view.findViewById(R.id.lv_orders);

        final ProgressDialog progress = ProgressDialog.show(getActivity(),
                "Tải thông tin", "Đợi 1 chút xíu....", false, true, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
        progress.show();

        DataClient getInfoDonHang = APIManagerment.getData();
        Call<List<DonHangFullInfo>> callback = getInfoDonHang.getInfoDonHang(String.valueOf(bundle.getInt("ID")));
        callback.enqueue(new Callback<List<DonHangFullInfo>>() {
            @Override
            public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                manginfodonhang = (ArrayList<DonHangFullInfo>) response.body();
                adapterInfoDonHang = new Adapter(getActivity(),1,manginfodonhang);
                lv.setAdapter(adapterInfoDonHang);
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                Log.d("aasd",t.getMessage());
                progress.dismiss();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                fragment = new single_info();
                FragmentManager fm = getFragmentManager();
                bundle.putSerializable("ItemDonHang",manginfodonhang.get(position));
                fragment.setArguments(bundle);
                FragmentTransaction ft =  fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.content_main,fragment);
                ft.commit();
            }
        });
        FloatingActionButton add_order = (FloatingActionButton) view.findViewById(R.id.add_order);
        add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new order_step1();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Đơn hàng");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        lv.setAdapter(null);
        final ProgressDialog progress = ProgressDialog.show(getActivity(),
                "Tải thông tin", "Đợi 1 chút xíu....", false, true, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
        progress.show();
        switch (item.getItemId()) {
            case R.id.All :
            {

                DataClient getInfoDonHang = APIManagerment.getData();
                Call<List<DonHangFullInfo>> callback = getInfoDonHang.getInfoDonHang(idcus);
                callback.enqueue(new Callback<List<DonHangFullInfo>>() {
                    @Override
                    public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                        manginfodonhang = (ArrayList<DonHangFullInfo>) response.body();
                        adapterInfoDonHang = new Adapter(getActivity(),1,manginfodonhang);
                        lv.setAdapter(adapterInfoDonHang);
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                        Log.d("aasd",t.getMessage());
                        progress.dismiss();
                    }
                });
                //Write here what to do you on click
                return true;
            }
            case R.id.not_shipper :
            {

                DataClient getInfoDonHang = APIManagerment.getData();
                Call<List<DonHangFullInfo>> callback = getInfoDonHang.getDonHangSTT1notShipper(idcus);
                callback.enqueue(new Callback<List<DonHangFullInfo>>() {
                    @Override
                    public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                        manginfodonhang = (ArrayList<DonHangFullInfo>) response.body();
                        adapterInfoDonHang = new Adapter(getActivity(),1,manginfodonhang);
                        lv.setAdapter(adapterInfoDonHang);
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                        Log.d("aasd",t.getMessage());
                        progress.dismiss();
                    }
                });
                //Write here what to do you on click
                return true;
            }
            case R.id.Doi_shipper :
            {

                DataClient getInfoDonHang = APIManagerment.getData();
                Call<List<DonHangFullInfo>> callback = getInfoDonHang.getDonHangSTT1Shipper(idcus);
                callback.enqueue(new Callback<List<DonHangFullInfo>>() {
                    @Override
                    public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                        manginfodonhang = (ArrayList<DonHangFullInfo>) response.body();
                        adapterInfoDonHang = new Adapter(getActivity(),1,manginfodonhang);
                        lv.setAdapter(adapterInfoDonHang);
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                        Log.d("aasd",t.getMessage());
                        progress.dismiss();
                    }
                });
                //Write here what to do you on click
                return true;
            }
            case R.id.Giao_hang :
            {

                DataClient getInfoDonHang = APIManagerment.getData();
                Call<List<DonHangFullInfo>> callback = getInfoDonHang.getDonHangShipping(idcus);
                callback.enqueue(new Callback<List<DonHangFullInfo>>() {
                    @Override
                    public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                        manginfodonhang = (ArrayList<DonHangFullInfo>) response.body();
                        adapterInfoDonHang = new Adapter(getActivity(),1,manginfodonhang);
                        lv.setAdapter(adapterInfoDonHang);
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                        Log.d("aasd",t.getMessage());
                        progress.dismiss();
                    }
                });
                //Write here what to do you on click
                return true;
            }
            case R.id.Da_giao :
            {

                DataClient getInfoDonHang = APIManagerment.getData();
                Call<List<DonHangFullInfo>> callback = getInfoDonHang.getDonHangFinished(idcus);
                callback.enqueue(new Callback<List<DonHangFullInfo>>() {
                    @Override
                    public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                        manginfodonhang = (ArrayList<DonHangFullInfo>) response.body();
                        adapterInfoDonHang = new Adapter(getActivity(),1,manginfodonhang);
                        lv.setAdapter(adapterInfoDonHang);
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                        Log.d("aasd",t.getMessage());
                        progress.dismiss();
                    }
                });
                //Write here what to do you on click
                return true;
            }
            case R.id.Da_huy :
            {

                DataClient getInfoDonHang = APIManagerment.getData();
                Call<List<DonHangFullInfo>> callback = getInfoDonHang.getDonHangDeleted(idcus);
                callback.enqueue(new Callback<List<DonHangFullInfo>>() {
                    @Override
                    public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                        manginfodonhang = (ArrayList<DonHangFullInfo>) response.body();
                        adapterInfoDonHang = new Adapter(getActivity(),1,manginfodonhang);
                        lv.setAdapter(adapterInfoDonHang);
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                        Log.d("aasd",t.getMessage());
                        progress.dismiss();
                    }
                });
                //Write here what to do you on click
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
