package com.example.huypm.turtle_ship.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
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
import com.example.huypm.turtle_ship.model.ItemDonHang;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class single_info extends Fragment {
    TextView tv_single_ngaydat,tv_single_mota,tv_single_kl,tv_single_gia,tv_single_sl,tv_single_maDH,tv_single_ngaygiao,
            tv_single_ngaynhan,tv_single_hinhthuc,tv_single_nguoinhan,tv_single_dcnhan,tv_single_nguoigui,tv_single_dcgui
            ,tv_single_shipper,tv_single_tienship,tv_single_stt;
    Button btn_single_sdtshipper,btn_single_sdtnhan,btn_single_sdtgui;
    String number;
    DiaChi dc;

    private static final int REQUEST_CALL = 1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.single_infomation,container,false);
        final Bundle bundle = getArguments();
        final ProgressDialog progress = ProgressDialog.show(getActivity(),
                "Tải thông tin", "Đợi 1 chút xíu....", false, true, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
        progress.show();
        final DonHangFullInfo itemDonHang = (DonHangFullInfo) bundle.getSerializable("ItemDonHang");

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

        tv_single_shipper = view.findViewById(R.id.tv_single_shipper);


        tv_single_tienship = view.findViewById(R.id.tv_single_tienship);
        tv_single_tienship.setText(itemDonHang.getThanhTien());

        btn_single_sdtshipper = view.findViewById(R.id.btn_single_sdtshipper);

        btn_single_sdtnhan = view.findViewById(R.id.btn_single_sdtnhan);
        btn_single_sdtnhan.setText(itemDonHang.getSDTNguoiNhan());

        btn_single_sdtgui = view.findViewById(R.id.btn_single_sdtgui);
        btn_single_sdtgui.setText(itemDonHang.getSDT());
        tv_single_stt = view.findViewById(R.id.tv_single_stt);
        switch (itemDonHang.getTrangThai()) {
            case "0":
                tv_single_stt.setText("Đã hủy đơn");
                break;
            case "1":
                if (itemDonHang.getShipper().equals("0")){
                    tv_single_stt.setText("Chờ shipper nhận");
                }else {
                    tv_single_stt.setText("Chờ shipper lấy hàng");
                }
                break;
            case "2":
                tv_single_stt.setText("Đang giao hàng");
                break;
            case "3":
                tv_single_stt.setText("Đã giao hàng");
        }

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
                        DataClient getdb = APIManagerment.getData();
                        Call<List<Customer_Employee>> callback = getdb.getCusEmpInfo(itemDonHang.getShipper());
                        callback.enqueue(new Callback<List<Customer_Employee>>() {
                            @Override
                            public void onResponse(Call<List<Customer_Employee>> call, Response<List<Customer_Employee>> response) {
                                ArrayList<Customer_Employee> shipper = (ArrayList<Customer_Employee>) response.body();
                                tv_single_shipper.setText(shipper.get(0).getTen());
                                btn_single_sdtshipper.setText(shipper.get(0).getSDT());
                                progress.dismiss();
                            }

                            @Override
                            public void onFailure(Call<List<Customer_Employee>> call, Throwable t) {
                                progress.dismiss();
                            }
                        });
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




        btn_single_sdtshipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = btn_single_sdtshipper.getText().toString().trim();
                makePhoneCall();
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

        Button btn_xem_map = view.findViewById(R.id.btn_view_map);
        btn_xem_map.setOnClickListener(new View.OnClickListener() {
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
        getActivity().setTitle("Thông tin đơn hàng");
    }
}
