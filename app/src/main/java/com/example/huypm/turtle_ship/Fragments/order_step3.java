package com.example.huypm.turtle_ship.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.ChiTietDonHang;
import com.example.huypm.turtle_ship.model.DiaChi;
import com.example.huypm.turtle_ship.model.DonHang;
import com.example.huypm.turtle_ship.model.NguoiNhan;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;
import retrofit2.Callback;
import retrofit2.Response;

public class order_step3 extends Fragment implements DirectionFinderListener {
    TextView tv_name_sent,tv_phone_sent;
    Spinner spn_sent_list_add,spn_district_receive,spn_state_receive;
    EditText et_name_receive,et_phone_step1,et_diachi_step1;
    TextView tv_info_dinhgia,tv_info_hinhthuc,vt_info_sl,vt_info_kl,tv_name_sent_step_3,tv_sdt_sent,tv_diachi_sent,tv_name_receive,tv_sdt_receive,tv_diachi_receive,vt_info_mota;
    Button btn_xacnhan, btn_map;
    Bundle bundle1;
    String quangduong,phi;
    View view;
    private ProgressDialog progressDialog;

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_step_3, container, false);
        final Bundle bundle = getArguments();
        bundle1 = bundle;
        sendRequest();
        tv_name_sent_step_3 = view.findViewById(R.id.tv_name_sent_step_3);
        tv_name_sent_step_3.setText("Người gửi: "+bundle.getString("tv_name_sent"));

        tv_sdt_sent = view.findViewById(R.id.tv_sdt_sent);
        tv_sdt_sent.setText(bundle.getString("tv_phone_sent"));


        tv_diachi_sent = view.findViewById(R.id.tv_diachi_sent);
        tv_diachi_sent.setText("Địa chỉ:"+bundle.getString("spn_sent_list_add"));


        tv_name_receive = view.findViewById(R.id.tv_name_receive);
        tv_name_receive.setText("Người nhận: "+bundle.getString("et_name_receive"));


        tv_sdt_receive = view.findViewById(R.id.tv_sdt_receive);
        tv_sdt_receive.setText("Số điện thoại: "+bundle.getString("et_phone_step1"));

        tv_diachi_receive = view.findViewById(R.id.tv_diachi_receive);
        tv_diachi_receive.setText("Địa chỉ: "+bundle.getString("et_diachi_step1")+","+bundle.getString("spn_state_receive")+","+bundle.getString("spn_district_receive"));

        vt_info_mota = view.findViewById(R.id.vt_info_mota);
        vt_info_mota.setText("Mô tả: "+bundle.getString("et_mota"));

        vt_info_kl = view.findViewById(R.id.vt_info_kl);
        vt_info_kl.setText("Khối lượng: "+bundle.getString("et_KhoiLuong"));

        vt_info_sl = view.findViewById(R.id.vt_info_sl);
        vt_info_sl.setText("So lượng: "+bundle.getString("et_soluong"));

        tv_info_hinhthuc = view.findViewById(R.id.tv_info_hinhthuc);
        if (bundle.getString("hinhthuc").toString().equals("2"))
            tv_info_hinhthuc.setText("Người nhận trả phí");
        if (bundle.getString("hinhthuc").toString().equals("1"))
            tv_info_hinhthuc.setText("Người gửi trả phí");

        tv_info_dinhgia = view.findViewById(R.id.tv_info_dinhgia);
        tv_info_dinhgia.setText("Gia san pham: "+bundle.getString("et_dinhgia"));

        Button btn_back = (Button) view.findViewById(R.id.btn_back_step_2);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });
        btn_map = view.findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new map_view();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft =  fm.beginTransaction();
                ft.addToBackStack(null);
                fragment.setArguments(bundle);
                ft.replace(R.id.content_main,fragment);
                ft.commit();
            }
        });
        btn_xacnhan = view.findViewById(R.id.btn_accept_order);
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progress = ProgressDialog.show(getActivity(),
                        "Đang tạo đơn", "Đợi 1 chút xíu....", false, true, new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {

                            }
                        });
                progress.show();

                DataClient insertNguoiNhan = APIManagerment.getData();
                retrofit2.Call<String> callback = insertNguoiNhan.InsertNguoiNhan(bundle.getString("et_name_receive"),bundle.getString("et_phone_step1"));
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                        DataClient insertDiaChi = APIManagerment.getData();
                        final String idNguoiNhan = response.body();
                        retrofit2.Call<String> callback = insertDiaChi.InsertAddress(String.valueOf(bundle.getInt("ID")),"Tp. Hồ Chí Minh",bundle.getString("spn_district_receive"),bundle.getString("spn_state_receive"),bundle.getString("et_diachi_step1"),response.body(),"0");
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                                DataClient insertDonHang = APIManagerment.getData();
                                String ngaydat = String.valueOf(Calendar.getInstance().getTime());
                                final retrofit2.Call<String> callback = insertDonHang.InsertDonHang(String.valueOf(bundle.getInt("ID")),idNguoiNhan,ngaydat ,"","",bundle.getString("id_diachi_kh"),response.body(),bundle.getString("hinhthuc"),quangduong,phi);
                                callback.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                                        DataClient insertCTDH = APIManagerment.getData();
                                        retrofit2.Call<String> callback = insertCTDH.InsertCTDH(response.body(),"",bundle.getString("et_mota"),bundle.getString("et_dinhgia"),bundle.getString("et_KhoiLuong"),bundle.getString("et_soluong"));
                                        callback.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                                                Log.d("tuyet voi","xong");
                                                progress.dismiss();
                                                Fragment fragment = new Orders();
                                                FragmentManager fm = getFragmentManager();
                                                fragment.setArguments(bundle);
                                                FragmentTransaction ft =  fm.beginTransaction();
                                                ft.replace(R.id.content_main,fragment);
                                                ft.commit();
                                                Toast.makeText(getContext(), "Tạo đơn thành công", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                                                progress.dismiss();
                                                Toast.makeText(getContext(), "Server lỗi thử lại", Toast.LENGTH_SHORT).show();
                                                Log.d("bugCTDH",t.getMessage());
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(retrofit2.Call<String> call, Throwable t) {
                                        progress.dismiss();
                                        Toast.makeText(getContext(), "Server lỗi thử lại", Toast.LENGTH_SHORT).show();
                                        Log.d("bugDonHang",t.getMessage());
                                    }
                                });
                            }

                            @Override
                            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                                progress.dismiss();
                                Toast.makeText(getContext(), "Server lỗi thử lại", Toast.LENGTH_SHORT).show();
                                Log.d("bugDiaChi",t.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onFailure(retrofit2.Call<String> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(getContext(), "Server lỗi thử lại", Toast.LENGTH_SHORT).show();
                        Log.d("bug",t.getMessage());
                    }
                });

            }
        });
        return view;
    }

    private void sendRequest() {
        String origin = bundle1.getString("spn_sent_list_add");
        String destination = bundle1.getString("et_diachi_step1")+","+bundle1.getString("spn_state_receive")+","+bundle1.getString("spn_district_receive") +"Tp. Hồ Chí Minh";

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(getContext(), "Please wait.",
                "Finding direction..!", true);
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {

        for (Route route : routes) {

            ((TextView) view.findViewById(R.id.vt_quangduong)).setText("Quãng đường:"+route.distance.text);
            int start = route.distance.text.length()-2;
            quangduong = route.distance.text.substring(0,start).trim();
            DataClient getFee = APIManagerment.getData();
            retrofit2.Call<String> callback = getFee.getTrnsprtFee(quangduong,bundle1.getString("et_dinhgia"),bundle1.getString("et_KhoiLuong"));
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                    progressDialog.dismiss();
                    ((TextView) view.findViewById(R.id.vt_phi)).setText("Phi:"+response.body());
                    phi = response.body();
                }

                @Override
                public void onFailure(retrofit2.Call<String> call, Throwable t) {
                    Log.d("phi",t.getMessage());
                }
            });
            Log.d("quang duong",quangduong);
        }

    }
}
