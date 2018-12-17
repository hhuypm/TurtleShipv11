package com.example.huypm.turtle_ship.ADapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huypm.turtle_ship.DBManager.TurtleShipManager;
import com.example.huypm.turtle_ship.Fragments.list_Address;
import com.example.huypm.turtle_ship.Fragments.update_address;
import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.DiaChi;
import com.example.huypm.turtle_ship.model.DonHangForShipper;
import com.example.huypm.turtle_ship.model.DonHangFullInfo;
import com.example.huypm.turtle_ship.model.ItemDonHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_address extends ArrayAdapter<DiaChi> {
    private Activity context;

    public Adapter_address(Activity context, int layoutID, List<DiaChi> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlist_address, null,
                    false);
        }

        final DiaChi itemAddress = getItem(position);
        TextView tv_stt = convertView.findViewById(R.id.tv_stt);
        TextView tv_address = convertView.findViewById(R.id.tv_address);
        tv_stt.setText(String.valueOf(position+1));
        tv_address.setText(itemAddress.getDuong()+", "+itemAddress.getPhuong()+", "+itemAddress.getQuan()+", Tp. Hồ Chí Minh");
        Button btn_sua_address = (Button) convertView.findViewById(R.id.btn_sua_address);
        btn_sua_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new update_address();
                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction ft =  fm.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putSerializable("diachi",itemAddress);
                fragment.setArguments(bundle);
                ft.addToBackStack(null);
                ft.replace(R.id.content_main,fragment);

                ft.commit();
            }
        });
        Button btn_del_address = (Button) convertView.findViewById(R.id.btn_delete_address);
        final View view1 = convertView;
        btn_del_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemAddress.getDCChinh().equals("0")){
                    final ProgressDialog progress = ProgressDialog.show(context,
                            "Đang xóa", "Đợi 1 chút xíu....", false, true, new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {

                                }
                            });
                    progress.show();
                    DataClient deleteAddress = APIManagerment.getData();
                    retrofit2.Call<String> callback = deleteAddress.deleteAddress(itemAddress.getId());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                            if (response.body().equals("ok")){{
                                Fragment fragment = new list_Address();
                                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                                FragmentTransaction ft =  fm.beginTransaction();
                                Bundle bundle = new Bundle();
                                bundle.putInt("ID",Integer.valueOf(itemAddress.getCusEmp()));
                                fragment.setArguments(bundle);
                                ft.addToBackStack(null);
                                ft.replace(R.id.content_main,fragment);
                                ft.commit();
                                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }}
                            progress.dismiss();
                        }

                        @Override
                        public void onFailure(retrofit2.Call<String> call, Throwable t) {
                            progress.dismiss();
                            Toast.makeText(getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(getContext(), "Địa chỉ chính không thể xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });
        final SwitchCompat sw_address = convertView.findViewById(R.id.sw_main_address);
        if (itemAddress.getDCChinh().equals("1")){
            sw_address.setChecked(true);
        }else {
            sw_address.setChecked(false);
        }
        sw_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sw_address.isChecked()){
                    sw_address.setChecked(true);
                    Toast.makeText(getContext(), "Không thể bỏ địa chỉ chính", Toast.LENGTH_SHORT).show();
                }else {
                    DataClient updateMainAddress = APIManagerment.getData();
                    retrofit2.Call<String> callback = updateMainAddress.updateMainAddress(itemAddress.getId(),itemAddress.getCusEmp());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                                Fragment fragment = new list_Address();
                                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                                FragmentTransaction ft =  fm.beginTransaction();
                                Bundle bundle = new Bundle();
                                bundle.putInt("ID",Integer.valueOf(itemAddress.getCusEmp()));
                                fragment.setArguments(bundle);
                                ft.addToBackStack(null);
                                ft.replace(R.id.content_main,fragment);
                                ft.commit();
                                Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(retrofit2.Call<String> call, Throwable t) {
                            Log.d("loimain",t.getMessage());

                        }
                    });
                }
            }
        });


        return convertView;
    }
}
