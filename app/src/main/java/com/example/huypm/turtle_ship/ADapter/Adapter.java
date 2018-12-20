package com.example.huypm.turtle_ship.ADapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huypm.turtle_ship.Fragments.status_oder1;
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

public class Adapter extends ArrayAdapter<DonHangFullInfo> {
    private Activity context;
    private TextView lv_MaDH;
    private TextView lv_Km;
    private TextView lv_Tienhang,lv_Tienship;

    public Adapter(Activity context, int layoutID, List<DonHangFullInfo> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list2, null,
                    false);
        }

        final DonHangFullInfo donHangForShipper = getItem(position);


        lv_MaDH = (TextView) convertView.findViewById(R.id.lv_MaDH);
        lv_Km = (TextView) convertView.findViewById(R.id.lv_Km);
        final TextView lv_DiachiGui = (TextView) convertView.findViewById(R.id.lv_DiachiGui_shipper);
        final TextView lv_DiachiNhan = (TextView) convertView.findViewById(R.id.lv_DiachiNhan_shipper);
        lv_Tienhang = (TextView) convertView.findViewById(R.id.lv_Tienhang);
        lv_Tienship = (TextView) convertView.findViewById(R.id.lv_Tienship);
        // Extract properties from cursor

        switch (donHangForShipper.getTrangThai()){
            case "0":
                lv_MaDH.setText(donHangForShipper.getId()+"(Đã hủy)");
                break;
            case "1":
                if (donHangForShipper.getShipper().equals("0")){
                    lv_MaDH.setText(donHangForShipper.getId()+"(Chờ shipper nhận)");
                }else {
                    lv_MaDH.setText(donHangForShipper.getId()+"(Chờ shipper lấy hàng)");
                }
                break;
            case "2":
                lv_MaDH.setText(donHangForShipper.getId()+"(Đang giao)");
                break;
            case "3":
                lv_MaDH.setText(donHangForShipper.getId()+"(Đã giao)");
                break;
        }

        lv_Km.setText(donHangForShipper.getCaySo()+" km");
        DataClient diachiKH = APIManagerment.getData();
        retrofit2.Call<List<DiaChi>> callback = diachiKH.getDiaChiID(donHangForShipper.getDCNhanHang());
        callback.enqueue(new Callback<List<DiaChi>>() {
            @Override
            public void onResponse(retrofit2.Call<List<DiaChi>> call, Response<List<DiaChi>> response) {
                ArrayList<DiaChi> diachiKH = (ArrayList<DiaChi>) response.body();
                lv_DiachiGui.setText("Địa chỉ gửi hàng:"+diachiKH.get(0).getDuong()+", "+diachiKH.get(0).getPhuong()+", "+diachiKH.get(0).getQuan());
                DataClient diachiNH = APIManagerment.getData();
                retrofit2.Call<List<DiaChi>> callback = diachiNH.getDiaChiID(donHangForShipper.getDCGiaoHang());
                callback.enqueue(new Callback<List<DiaChi>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<DiaChi>> call, Response<List<DiaChi>> response) {
                        ArrayList<DiaChi> diachiNH = (ArrayList<DiaChi>) response.body();
                        lv_DiachiNhan.setText("Địa chỉ nhận hàng:"+diachiNH.get(0).getDuong()+", "+diachiNH.get(0).getPhuong()+", "+diachiNH.get(0).getQuan());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<List<DiaChi>> call, Throwable t) {
                        Log.d("loi~",t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(retrofit2.Call<List<DiaChi>> call, Throwable t) {
                Log.d("loi~1",t.getMessage());
            }
        });

        lv_Tienhang.setText("Tiền hàng: "+donHangForShipper.getDinhGia());
        lv_Tienship.setText("Tiền ship: "+donHangForShipper.getThanhTien());





        return convertView;
    }
}
