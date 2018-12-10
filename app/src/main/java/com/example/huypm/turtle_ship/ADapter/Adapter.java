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
import com.example.huypm.turtle_ship.model.ItemDonHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class Adapter extends ArrayAdapter<ItemDonHang> {
    private Activity context;

    public Adapter(Activity context, int layoutID, List<ItemDonHang> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, null,
                    false);
        }

        final ItemDonHang itemDonHang = getItem(position);

        TextView lv_Ten = (TextView) convertView.findViewById(R.id.lv_Ten);
        final TextView lv_DiachiGui = (TextView) convertView.findViewById(R.id.lv_DiachiGui);
        final TextView lv_DiachiNhan = (TextView) convertView.findViewById(R.id.lv_DiachiNhan);
        TextView lv_Sodienthoai = (TextView) convertView.findViewById(R.id.lv_Sodienthoai);
        // Extract properties from cursor
        DataClient diachiKH = APIManagerment.getData();
        retrofit2.Call<List<DiaChi>> callback = diachiKH.getDiaChiID(itemDonHang.getNoiNhanHang());
        callback.enqueue(new Callback<List<DiaChi>>() {
            @Override
            public void onResponse(retrofit2.Call<List<DiaChi>> call, Response<List<DiaChi>> response) {
                ArrayList<DiaChi> diachiKH = (ArrayList<DiaChi>) response.body();
                lv_DiachiGui.setText("Địa chỉ gửi hàng:"+diachiKH.get(0).getDuong()+", "+diachiKH.get(0).getPhuong()+", "+diachiKH.get(0).getQuan());
                DataClient diachiNH = APIManagerment.getData();
                retrofit2.Call<List<DiaChi>> callback = diachiNH.getDiaChiID(itemDonHang.getNoiGiaoHang());
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
        lv_Ten.setText(itemDonHang.getNguoiNhan());
        /*lv_DiachiGui.setText("Địa chỉ gửi hàng:"+dcgui.getString(5)+", "+dcgui.getString(4)+", "+dcgui.getString(3));
        lv_DiachiNhan.setText("Địa chỉ nhận hàng:"+dcnhan.getString(5)+", "+dcnhan.getString(4)+", "+dcnhan.getString(3));*/
        lv_Sodienthoai.setText(itemDonHang.getSDTNguoiNhan());



        return convertView;
    }
}
