package com.example.huypm.turtle_ship.ADapter;

import android.app.Activity;
import android.database.Cursor;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.huypm.turtle_ship.DBManager.TurtleShipManager;
import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.DiaChi;
import com.example.huypm.turtle_ship.model.DonHang;
import com.example.huypm.turtle_ship.model.DonHangForShipper;
import com.example.huypm.turtle_ship.model.ItemDonHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class ADapter2 extends ArrayAdapter<DonHangForShipper> {

    private Activity context;
    private TextView lv_MaDH;
    private TextView lv_Km;
    private TextView lv_DiachiGui,lv_DiachiNhan,lv_Tienhang,lv_Tienship;
    public ADapter2(Activity context, int layoutID, List<DonHangForShipper> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list2, null,
                    false);
        }

        final DonHangForShipper donHangForShipper = getItem(position);


        lv_MaDH = (TextView) convertView.findViewById(R.id.lv_MaDH);
        lv_Km = (TextView) convertView.findViewById(R.id.lv_Km);
        lv_DiachiGui = (TextView) convertView.findViewById(R.id.lv_DiachiGui);
        lv_DiachiNhan = (TextView) convertView.findViewById(R.id.lv_DiachiNhan);
        lv_Tienhang = (TextView) convertView.findViewById(R.id.lv_Tienhang);
        lv_Tienship = (TextView) convertView.findViewById(R.id.lv_Tienship);
        // Extract properties from cursor

        lv_MaDH.setText(donHangForShipper.getId());
        lv_Km.setText(donHangForShipper.getCaySo()+" km");
        DataClient diachiKH = APIManagerment.getData();
        retrofit2.Call<List<DiaChi>> callback = diachiKH.getDiaChiID(donHangForShipper.getId());
        callback.enqueue(new Callback<List<DiaChi>>() {
            @Override
            public void onResponse(retrofit2.Call<List<DiaChi>> call, Response<List<DiaChi>> response) {
                ArrayList<DiaChi> diachiKH = (ArrayList<DiaChi>) response.body();
                lv_DiachiGui.setText("Địa chỉ gửi hàng:"+diachiKH.get(0).getDuong()+", "+diachiKH.get(0).getPhuong()+", "+diachiKH.get(0).getQuan());
                DataClient diachiNH = APIManagerment.getData();
                retrofit2.Call<List<DiaChi>> callback = diachiNH.getDiaChiID(donHangForShipper.getId());
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

        lv_Tienhang.setText("Tiền hàng: "+donHangForShipper.getThanhTien());
        //roif test thu ok



        return convertView;
    }
}