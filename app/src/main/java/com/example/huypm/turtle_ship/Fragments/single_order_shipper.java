package com.example.huypm.turtle_ship.Fragments;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.Customer_Employee;
import com.example.huypm.turtle_ship.model.DiaChi;
import com.example.huypm.turtle_ship.model.DonHangFullInfo;
import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class single_order_shipper extends Fragment {
    TextView tv_single_ngaydat,tv_single_mota,tv_single_kl,tv_single_gia,tv_single_sl,tv_single_maDH,tv_single_ngaygiao,
            tv_single_ngaynhan,tv_single_hinhthuc,tv_single_nguoinhan,tv_single_dcnhan,tv_single_nguoigui,tv_single_dcgui,tv_single_stt,tv_ship;
    Button btn_single_sdtnhan,btn_single_sdtgui,btn_NhanDon_shipper,btn_LayHang_shipper,btn_GuiHang_shipper;
    String number;
    DiaChi dc;
    private static final int REQUEST_CALL = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.single_order_shipper,container,false);

        final Bundle bundle = getArguments();
        final ProgressDialog progress = ProgressDialog.show(getActivity(),
                "Tải thông tin", "Đợi 1 chút xíu....", false, true, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
        progress.show();
        final DonHangFullInfo itemDonHang = (DonHangFullInfo) bundle.getSerializable("ItemDonHang");

        tv_single_stt = view.findViewById(R.id.tv_single_stt);

        btn_NhanDon_shipper = view.findViewById(R.id.btn_NhanDon_shipper);
        final String idshipper = String.valueOf(bundle.getInt("ID"));
        btn_NhanDon_shipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClient shippernhandon = APIManagerment.getData();
                Call<String> callback = shippernhandon.ShipperNhanDonHang(itemDonHang.getId(),idshipper);
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("ok")){
                            Fragment fragment = null;
                            fragment = new single_order_shipper();
                            FragmentManager fm = getFragmentManager();
                            itemDonHang.setShipper(idshipper);
                            bundle.putSerializable("ItemDonHang",itemDonHang);
                            fragment.setArguments(bundle);
                            FragmentTransaction ft =  fm.beginTransaction();
                            ft.replace(R.id.content_main_shipper,fragment);
                            ft.commit();
                            Toast.makeText(getContext(), "Nhận đơn thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("nhandon",t.getMessage());
                    }
                });
            }
        });
        btn_LayHang_shipper = view.findViewById(R.id.btn_LayHang_shipper);
        btn_LayHang_shipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClient layhang = APIManagerment.getData();
                final String ngaygiao = String.valueOf(Calendar.getInstance().getTime());
                Call<String> callback = layhang.ShipperNhanHang(itemDonHang.getId(),ngaygiao);
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("ok")){
                            Fragment fragment = null;
                            fragment = new single_order_shipper();
                            FragmentManager fm = getFragmentManager();
                            itemDonHang.setTrangThai("2");
                            itemDonHang.setNgayGiao(ngaygiao);
                            bundle.putSerializable("ItemDonHang",itemDonHang);
                            fragment.setArguments(bundle);
                            FragmentTransaction ft =  fm.beginTransaction();
                            ft.replace(R.id.content_main_shipper,fragment);
                            ft.commit();
                            Toast.makeText(getContext(), "Lấy hàng thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("nhandon",t.getMessage());
                    }
                });
            }
        });
        btn_GuiHang_shipper = view.findViewById(R.id.btn_GuiHang_shipper);
        btn_GuiHang_shipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClient guihang = APIManagerment.getData();
                final String ngaynhan = String.valueOf(Calendar.getInstance().getTime());
                Call<String> callback = guihang.ShipperGiaoHang(itemDonHang.getId(),ngaynhan);
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("ok")){
                            Fragment fragment = null;
                            fragment = new single_order_shipper();
                            FragmentManager fm = getFragmentManager();
                            itemDonHang.setTrangThai("3");
                            itemDonHang.setNgayNhan(ngaynhan);
                            bundle.putSerializable("ItemDonHang",itemDonHang);
                            fragment.setArguments(bundle);
                            FragmentTransaction ft =  fm.beginTransaction();
                            ft.replace(R.id.content_main_shipper,fragment);
                            ft.commit();
                            Toast.makeText(getContext(), "Gửi hàng thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("nhandon",t.getMessage());
                    }
                });
            }
        });
        switch (itemDonHang.getTrangThai()) {
            case "-1":
                tv_single_stt.setText("Chờ shipper nhận");
                btn_NhanDon_shipper.setVisibility(view.VISIBLE);
                break;
            case "0":
                tv_single_stt.setText("Đã hủy đơn");
                break;
            case "1":
                    tv_single_stt.setText("Chờ shipper lấy hàng");
                    btn_LayHang_shipper.setVisibility(view.VISIBLE);
                break;
            case "2":
                tv_single_stt.setText("Đang giao hàng");
                btn_GuiHang_shipper.setVisibility(view.VISIBLE);
                break;
            case "3":
                tv_single_stt.setText("Đã giao hàng");
        }
        tv_ship = view.findViewById(R.id.tv_ship);
        tv_ship.setText(itemDonHang.getThanhTien()+" đ");

        tv_single_ngaydat = view.findViewById(R.id.tv_single_ngaydat);
        tv_single_ngaydat.setText(tv_single_ngaydat.getText()+itemDonHang.getNgayDat());

        tv_single_mota = view.findViewById(R.id.tv_single_mota);
        tv_single_mota.setText(tv_single_mota.getText()+itemDonHang.getMota());

        tv_single_kl = view.findViewById(R.id.tv_single_kl);
        tv_single_kl.setText(tv_single_kl.getText()+itemDonHang.getKhoiLuong());

        tv_single_gia = view.findViewById(R.id.tv_single_gia);
        tv_single_gia.setText(tv_single_gia.getText()+itemDonHang.getDinhGia());

        tv_single_sl = view.findViewById(R.id.tv_single_sl);
        tv_single_sl.setText(tv_single_sl.getText()+itemDonHang.getSoLuong());

        tv_single_maDH = view.findViewById(R.id.tv_single_maDH);
        tv_single_maDH.setText(itemDonHang.getId());

        tv_single_ngaygiao = view.findViewById(R.id.tv_single_ngaygiao);
        tv_single_ngaygiao.setText(itemDonHang.getNgayGiao());

        tv_single_ngaynhan = view.findViewById(R.id.tv_single_ngaynhan);
        tv_single_ngaynhan.setText(itemDonHang.getNgayNhan());

        tv_single_hinhthuc = view.findViewById(R.id.tv_single_hinhthuc);
        if (itemDonHang.getHTGiaoHang().equals("1")){
            tv_single_hinhthuc.setText("Người gửi trả");
        }else {
            tv_single_hinhthuc.setText("Người nhận trả");
        }
        tv_single_nguoinhan = view.findViewById(R.id.tv_single_nguoinhan);
        tv_single_nguoinhan.setText(itemDonHang.getTenNguoiNhan());

        tv_single_dcnhan = view.findViewById(R.id.tv_single_dcnhan);


        tv_single_nguoigui = view.findViewById(R.id.tv_single_nguoigui);
        tv_single_nguoigui.setText(itemDonHang.getTen());

        tv_single_dcgui = view.findViewById(R.id.tv_single_dcgui);


        btn_single_sdtnhan = view.findViewById(R.id.btn_single_sdtnhan);
        btn_single_sdtnhan.setText(itemDonHang.getSDTNguoiNhan());

        btn_single_sdtgui = view.findViewById(R.id.btn_single_sdtgui);
        btn_single_sdtgui.setText(itemDonHang.getSDT());

        DataClient getdb = APIManagerment.getData();
        Call<List<DiaChi>> callback = getdb.getDiaChiID(itemDonHang.getDCGiaoHang());
        callback.enqueue(new Callback<List<DiaChi>>() {
            @Override
            public void onResponse(Call<List<DiaChi>> call, Response<List<DiaChi>> response) {
                ArrayList<DiaChi> dcgui = (ArrayList<DiaChi>) response.body();
                dc = dcgui.get(0);
                tv_single_dcnhan.setText(dcgui.get(0).getDuong()+", "+dcgui.get(0).getPhuong()+", "+dcgui.get(0).getQuan()+", Tp. Hồ Chí Minh");
                DataClient getdb1 = APIManagerment.getData();
                Call<List<DiaChi>> callback = getdb1.getDiaChiID(itemDonHang.getDCNhanHang());
                callback.enqueue(new Callback<List<DiaChi>>() {
                    @Override
                    public void onResponse(Call<List<DiaChi>> call, Response<List<DiaChi>> response) {
                        ArrayList<DiaChi> dcnhan = (ArrayList<DiaChi>) response.body();
                        tv_single_dcgui.setText(dcnhan.get(0).getDuong()+", "+dcnhan.get(0).getPhuong()+", "+dcnhan.get(0).getQuan()+", Tp. Hồ Chí Minh");
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<DiaChi>> call, Throwable t) {
                        progress.dismiss();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<DiaChi>> call, Throwable t) {
                progress.dismiss();
            }
        });

        btn_single_sdtnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = btn_single_sdtnhan.getText().toString().trim();
                makePhoneCall();
            }
        });


        btn_single_sdtgui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = btn_single_sdtgui.getText().toString().trim();
                makePhoneCall();
            }
        });

        Button btn_map = view.findViewById(R.id.btn_map_shipper);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new map_view();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft =  fm.beginTransaction();
                ft.addToBackStack(null);
                bundle.putString("spn_sent_list_add",tv_single_dcgui.getText().toString());
                bundle.putString("et_diachi_step1",dc.getDuong());
                bundle.putString("spn_state_receive",dc.getPhuong());
                bundle.putString("spn_district_receive",dc.getQuan());
                fragment.setArguments(bundle);
                ft.replace(R.id.content_main_shipper,fragment);
                ft.commit();
            }
        });
        return view;

    }

    public void makePhoneCall(){
        if (number.trim().length()>0){
            if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }else {
                String dial =  "tel:" +number;
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else {
                Toast.makeText(getContext(), "denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
