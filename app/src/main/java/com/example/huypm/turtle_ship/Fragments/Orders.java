package com.example.huypm.turtle_ship.Fragments;

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
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.huypm.turtle_ship.ADapter.Adapter;
import com.example.huypm.turtle_ship.ADapter.adapter_itemlist;
import com.example.huypm.turtle_ship.DBManager.TurtleShipManager;
import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
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

        DataClient getInfoDonHang = APIManagerment.getData();
        Call<List<ItemDonHang>> callback = getInfoDonHang.getInfoDonHang(String.valueOf(bundle.getInt("ID")));
        callback.enqueue(new Callback<List<ItemDonHang>>() {
            @Override
            public void onResponse(Call<List<ItemDonHang>> call, Response<List<ItemDonHang>> response) {
                ArrayList<ItemDonHang> manginfodonhang = (ArrayList<ItemDonHang>) response.body();
                adapterInfoDonHang = new Adapter(getActivity(),1,manginfodonhang);
                lv.setAdapter(adapterInfoDonHang);
            }

            @Override
            public void onFailure(Call<List<ItemDonHang>> call, Throwable t) {
                Log.d("aasd",t.getMessage());
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
