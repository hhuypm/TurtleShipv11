package com.example.huypm.turtle_ship.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_address extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Thêm địa chỉ");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.add_address,container,false);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spn_add_district);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.District, R.layout.dropdown_item);
        spinner.setAdapter(adapter);
        final Spinner spn_add_state = (Spinner) view.findViewById(R.id.spn_add_state);
        // Phường thay đổi theo quận
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<CharSequence> adapter1 = null;

                switch (position) {
                    case 0:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d1, R.layout.dropdown_item);
                        break;
                    case 1:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d2, R.layout.dropdown_item);
                        break;
                    case 2:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d3, R.layout.dropdown_item);
                        break;
                    case 3:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d4, R.layout.dropdown_item);
                        break;
                    case 4:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d5, R.layout.dropdown_item);
                        break;
                    case 5:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d6, R.layout.dropdown_item);
                        break;
                    case 6:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d7, R.layout.dropdown_item);
                        break;
                    case 7:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d8, R.layout.dropdown_item);
                        break;
                    case 8:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d9, R.layout.dropdown_item);
                        break;
                    case 9:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d10, R.layout.dropdown_item);
                        break;
                    case 10:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d11, R.layout.dropdown_item);
                        break;
                    case 11:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.d12, R.layout.dropdown_item);
                        break;
                    case 12:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dGoVap, R.layout.dropdown_item);
                        break;
                    case 13:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dBinhThanh, R.layout.dropdown_item);
                        break;
                    case 14:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dThuDuc, R.layout.dropdown_item);
                        break;
                    case 15:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dTanBinh, R.layout.dropdown_item);
                        break;
                    case 16:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dTanPhu, R.layout.dropdown_item);
                        break;
                    case 17:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dPhuNhuan, R.layout.dropdown_item);
                        break;
                    case 18:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dBinhTan, R.layout.dropdown_item);
                        break;
                    case 19:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dCuChi, R.layout.dropdown_item);
                        break;
                    case 20:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dHoocMon, R.layout.dropdown_item);
                        break;
                    case 21:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dBinhChanh, R.layout.dropdown_item);
                        break;
                    case 22:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dNhaBe, R.layout.dropdown_item);
                        break;
                    case 23:
                        adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.dCanGio, R.layout.dropdown_item);
                        break;
                    default:
                }
                spn_add_state.setAdapter(adapter1);
                Log.d("testtt","thuwr");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button btn_add_address = view.findViewById(R.id.btn_add_address);
        final Bundle bundle = getArguments();
        final String id =String.valueOf(bundle.getInt("ID"));
        btn_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_street = view.findViewById(R.id.et_add_street);
                DataClient insertAdd = APIManagerment.getData();

                Call<String> callback = insertAdd.InsertAddress(id,"Tp. Hồ Chí MInh",spinner.getSelectedItem().toString(),spn_add_state.getSelectedItem().toString(),et_street.getText().toString(),"-1","0");
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (!response.body().equals("not")){
                            FragmentManager fm = getFragmentManager();
                            fm.popBackStack();
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
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
