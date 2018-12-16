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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.huypm.turtle_ship.ADapter.Adapter;
import com.example.huypm.turtle_ship.ADapter.adapter_itemlist;
import com.example.huypm.turtle_ship.DBManager.TurtleShipManager;
import com.example.huypm.turtle_ship.OnFragmentManager;
import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.DonHangForShipper;
import com.example.huypm.turtle_ship.model.DonHangFullInfo;
import com.example.huypm.turtle_ship.model.ItemDonHang;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.oders,container,false);
        TurtleShipManager db = new TurtleShipManager(getContext());
        final Bundle bundle = getArguments();
        Cursor cursor = db.getOrders(bundle.getInt("ID"));
        lv = view.findViewById(R.id.lv_orders);
        /*adapter_itemlist itemlist  = new adapter_itemlist(getActivity(),cursor);
        lv.setAdapter(itemlist);
        itemlist.changeCursor(cursor);*/
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


}
