package com.example.huypm.turtle_ship.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.DiaChi;
import com.example.huypm.turtle_ship.model.DonHangForShipper;
import com.example.huypm.turtle_ship.model.DonHangFullInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class map_view_shipper extends Fragment implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    View view;
    private GoogleMap mMap;
    private List<Marker> originMarkers = new ArrayList<>();
    private ProgressDialog progressDialog;
    private static final int LOCATION_REQUEST = 500;
    ArrayList<DonHangFullInfo> mangdonhang1;
    ArrayList<DiaChi> mangdiachinguoigui,mangdiachinguoinhan;
    String idshipper;
    int i;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.map_view_shipper, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_shipper);
        Bundle bundle = getArguments();
        idshipper = String.valueOf(bundle.getInt("ID"));
        mangdiachinguoigui = new ArrayList<DiaChi>();
        mangdiachinguoinhan = new ArrayList<DiaChi>();


        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final ProgressDialog progress = ProgressDialog.show(getActivity(),
                "Tải thông tin", "Đợi 1 chút xíu....", false, true, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
        progress.show();
        DataClient getDonHang = APIManagerment.getData();
        Call<List<DonHangFullInfo>> callback = getDonHang.getDonHangShipper();
        callback.enqueue(new Callback<List<DonHangFullInfo>>() {
            @Override
            public void onResponse(Call<List<DonHangFullInfo>> call, Response<List<DonHangFullInfo>> response) {
                ArrayList<DonHangFullInfo> mangdonhang = (ArrayList<DonHangFullInfo>) response.body();
                mangdonhang1 = mangdonhang;
                for (int i = 0;i<mangdonhang1.size();i++){
                    DataClient latlng = APIManagerment.getData();
                    final String id = mangdonhang1.get(0).getId();
                    Call<List<DiaChi>> callback = latlng.getDiaChiID(mangdonhang1.get(0).getDCNhanHang());
                    callback.enqueue(new Callback<List<DiaChi>>() {
                        @Override
                        public void onResponse(Call<List<DiaChi>> call, Response<List<DiaChi>> response) {
                            ArrayList<DiaChi> mangdiachi = (ArrayList<DiaChi>) response.body();
                            final LatLng addresss = getLocationFromAddress(mangdiachi.get(0).getDuong()+", "+mangdiachi.get(0).getPhuong()+", "+mangdiachi.get(0).getQuan()+", Tp. Hồ Chí Minh");
                            if (mangdiachi.get(0)!= null){
                                mangdiachinguoigui.add(mangdiachi.get(0));
                            }

                            DataClient latlng = APIManagerment.getData();
                            Call<List<DiaChi>> callback = latlng.getDiaChiID(mangdonhang1.get(0).getDCGiaoHang());
                            callback.enqueue(new Callback<List<DiaChi>>() {
                                @Override
                                public void onResponse(Call<List<DiaChi>> call, Response<List<DiaChi>> response) {
                                    ArrayList<DiaChi> mangdiachi = (ArrayList<DiaChi>) response.body();
                                    mangdiachinguoinhan.add(mangdiachi.get(0));
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(addresss, 15));
                                    originMarkers.add(mMap.addMarker(new MarkerOptions()
                                            .title("Đơn hàng: "+id)
                                            .position(addresss)));
                                }

                                @Override
                                public void onFailure(Call<List<DiaChi>> call, Throwable t) {

                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<List<DiaChi>> call, Throwable t) {
                            Log.d("diachiloi",t.getMessage());
                            progress.dismiss();
                        }
                    });
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<List<DonHangFullInfo>> call, Throwable t) {
                Log.d("mangloi",t.getMessage());
                LatLng b = new LatLng( 10.766116, 106.659252);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(b, 15));
                progress.dismiss();

            }
        });

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                Log.d("markertest",marker.getTitle());
                for (i = 0; i<mangdonhang1.size();i++){
                    if (mangdonhang1.get(i).getId().equals(marker.getTitle().substring(10,marker.getTitle().length()))){
                        for (int j=0; j<mangdiachinguoigui.size(); j++){
                            if (mangdiachinguoigui.get(j).getId().equals(mangdonhang1.get(i).getDCNhanHang())){
                                EditText et_origin = view.findViewById(R.id.etOrigin);
                                et_origin.setText(mangdiachinguoigui.get(j).getDuong()+", "+mangdiachinguoigui.get(j).getPhuong()+", "+mangdiachinguoigui.get(j).getQuan()+", Tp. Hồ Chí Minh");
                                EditText et_des = view.findViewById(R.id.etDestination);
                                et_des.setText(mangdiachinguoinhan.get(j).getDuong()+", "+mangdiachinguoinhan.get(j).getPhuong()+", "+mangdiachinguoinhan.get(j).getQuan()+", Tp. Hồ Chí Minh");
                                break;
                            }
                        }
                        Button btn = (Button) view.findViewById(R.id.btn_nhandon_map);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final ProgressDialog progress1 = ProgressDialog.show(getActivity(),
                                        "Tải Nhận đơn", "Đợi 1 chút xíu....", false, true, new DialogInterface.OnCancelListener() {
                                            @Override
                                            public void onCancel(DialogInterface dialog) {

                                            }
                                        });
                                progress1.show();
                                DataClient nhandonhang = APIManagerment.getData();
                                Call<String> callback = nhandonhang.ShipperNhanDonHang(mangdonhang1.get(i).getId(),idshipper);
                                callback.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        if (response.body().equals("ok")){
                                            EditText et_origin = view.findViewById(R.id.etOrigin);
                                            et_origin.setText("");
                                            EditText et_des = view.findViewById(R.id.etDestination);
                                            et_des.setText("");
                                            marker.remove();
                                            progress1.dismiss();
                                            Toast.makeText(getContext(), "Nhận đơn thành công", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Log.d("markerloi",t.getMessage());
                                    }
                                });
                            }
                        });
                        break;

                    }
                }
                return false;
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case LOCATION_REQUEST:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }
    }

    public LatLng getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(getContext());
        List<Address> address;
        LatLng p1 = null  ;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(),
                    location.getLongitude());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return p1;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("markertest",marker.getTitle());
        return false;
    }
}
