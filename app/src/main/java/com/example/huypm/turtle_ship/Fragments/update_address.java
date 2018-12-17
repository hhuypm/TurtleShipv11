package com.example.huypm.turtle_ship.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.huypm.turtle_ship.R;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.DiaChi;

import java.sql.Array;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class update_address extends Fragment {
    String[] mTestArrayState;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Sửa địa chỉ");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_address,container,false);
        Bundle bundle = getArguments();
        final DiaChi itemDiaChi = (DiaChi) bundle.getSerializable("diachi");
        final Spinner spn_address = (Spinner) view.findViewById(R.id.spn_district_update);
        ArrayAdapter<CharSequence> adapter_address = ArrayAdapter.createFromResource(getContext(), R.array.District, R.layout.dropdown_item);
        spn_address.setAdapter(adapter_address);

        final View v = view;
        final Spinner spn_state_receive = (Spinner) v.findViewById(R.id.spn_state_update);
        spn_address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter<CharSequence> adapter_state = null;
                switch (position){
                    case 0:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d1, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d1);

                        break;
                    case 1:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d2, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d2);
                        break;
                    case 2:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d3, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d3);
                        break;
                    case 3:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d4, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d4);
                        break;
                    case 4:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d5, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d5);
                        break;
                    case 5:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d6, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d6);
                        break;
                    case 6:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d7, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d7);
                        break;
                    case 7:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d8, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d8);
                        break;
                    case 8:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d9, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d9);
                        break;
                    case 9:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d10, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d10);
                        break;
                    case 10:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d11, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d11);
                        break;
                    case 11:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.d12, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.d12);
                        break;
                    case 12:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dGoVap, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dGoVap);
                        break;
                    case 13:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dBinhThanh, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dBinhThanh);
                        break;
                    case 14:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dThuDuc, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dThuDuc);
                        break;
                    case 15:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dTanBinh, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dTanBinh);
                        break;
                    case 16:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dTanPhu, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dTanPhu);
                        break;
                    case 17:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dPhuNhuan, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dPhuNhuan);
                        break;
                    case 18:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dBinhTan, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dBinhTan);
                        break;
                    case 19:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dCuChi, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dCuChi);
                        break;
                    case 20:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dHoocMon, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dHoocMon);
                        break;
                    case 21:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dBinhChanh, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dBinhChanh);
                        break;
                    case 22:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dNhaBe, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dNhaBe);
                        break;
                    case 23:
                        adapter_state = ArrayAdapter.createFromResource(getContext(), R.array.dCanGio, R.layout.dropdown_item);
                        mTestArrayState = getResources().getStringArray(R.array.dCanGio);
                        break;
                    default:
                }
                spn_state_receive.setAdapter(adapter_state);
                for (int i =0;i<mTestArrayState.length;i++){
                    if (mTestArrayState[i].equals(itemDiaChi.getPhuong()) && (spn_address.getSelectedItem().equals(itemDiaChi.getQuan()))){
                        spn_state_receive.setSelection(i);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String[] mTestArrayDistrict = getResources().getStringArray(R.array.District);
        for (int i =0;i<mTestArrayDistrict.length;i++){
            if (mTestArrayDistrict[i].equals(itemDiaChi.getQuan())){
                spn_address.setSelection(i);
            }
        }
        final EditText et_address_update = view.findViewById(R.id.et_address_update);
        et_address_update.setText(itemDiaChi.getDuong());
        Button btn_update_address = view.findViewById(R.id.btn_update_address);
        btn_update_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataClient updateAddress = APIManagerment.getData();
                Call<String> callback = updateAddress.updateAddress(itemDiaChi.getId(),spn_address.getSelectedItem().toString(),spn_state_receive.getSelectedItem().toString(),et_address_update.getText().toString());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("ok")){
                            FragmentManager fm = getFragmentManager();
                            fm.popBackStack();
                            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });

        return view;
    }




}
