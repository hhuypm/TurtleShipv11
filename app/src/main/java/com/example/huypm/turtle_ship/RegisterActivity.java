package com.example.huypm.turtle_ship;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.huypm.turtle_ship.DBManager.TurtleShipManager;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.Service.Server;
import com.example.huypm.turtle_ship.Service.TurtleShipDBManagerment;
import com.example.huypm.turtle_ship.model.Customer_Employee;
import com.example.huypm.turtle_ship.model.DiaChi;
import com.example.huypm.turtle_ship.model.Users;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {
    Button btn_back;
    Button btn_accpect_register;
    EditText et_name;
    EditText et_phone;
    EditText et_pass;
    EditText et_re_pass;
    EditText et_mail;
    Spinner spn_district;
    Spinner spn_state;
    EditText et_address;
    Context context = this;
    Dialog dialog;
    int  maxidAddress = -1;
    int maxidCus_Emp = -1;
    int maxidUser = -1;
    TurtleShipDBManagerment turtleShipDBManagerment = new TurtleShipDBManagerment();
    AlertDialog alertDialog;
    TurtleShipManager db = new TurtleShipManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register_layout);
        FindViewByIds();

        // Bỏ quận vào
        final Spinner spinner = (Spinner) findViewById(R.id.spn_district);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.District, R.layout.dropdown_item);
        spinner.setAdapter(adapter);
        // Phường thay đổi theo quận
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner1 = (Spinner) findViewById(R.id.spn_state);
                ArrayAdapter<CharSequence> adapter1 = null;

                switch (position) {
                    case 0:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d1, R.layout.dropdown_item);
                        break;
                    case 1:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d2, R.layout.dropdown_item);
                        break;
                    case 2:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d3, R.layout.dropdown_item);
                        break;
                    case 3:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d4, R.layout.dropdown_item);
                        break;
                    case 4:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d5, R.layout.dropdown_item);
                        break;
                    case 5:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d6, R.layout.dropdown_item);
                        break;
                    case 6:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d7, R.layout.dropdown_item);
                        break;
                    case 7:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d8, R.layout.dropdown_item);
                        break;
                    case 8:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d9, R.layout.dropdown_item);
                        break;
                    case 9:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d10, R.layout.dropdown_item);
                        break;
                    case 10:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d11, R.layout.dropdown_item);
                        break;
                    case 11:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.d12, R.layout.dropdown_item);
                        break;
                    case 12:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dGoVap, R.layout.dropdown_item);
                        break;
                    case 13:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dBinhThanh, R.layout.dropdown_item);
                        break;
                    case 14:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dThuDuc, R.layout.dropdown_item);
                        break;
                    case 15:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dTanBinh, R.layout.dropdown_item);
                        break;
                    case 16:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dTanPhu, R.layout.dropdown_item);
                        break;
                    case 17:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dPhuNhuan, R.layout.dropdown_item);
                        break;
                    case 18:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dBinhTan, R.layout.dropdown_item);
                        break;
                    case 19:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dCuChi, R.layout.dropdown_item);
                        break;
                    case 20:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dHoocMon, R.layout.dropdown_item);
                        break;
                    case 21:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dBinhChanh, R.layout.dropdown_item);
                        break;
                    case 22:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dNhaBe, R.layout.dropdown_item);
                        break;
                    case 23:
                        adapter1 = ArrayAdapter.createFromResource(context, R.array.dCanGio, R.layout.dropdown_item);
                        break;
                    default:
                }
                spinner1.setAdapter(adapter1);
                Log.d("testtt","thuwr");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });


        btn_accpect_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chỗ này là đăng ký t mới chỉ có xét pass 2 cái giống nhau thôi. M làm cho t xét sdt thì chỉ được ghi số, còn gmail phải có @, còn nếu khó thì để sau chèn dialog vô chỗ else, còn thành công thì this.finish đó
                if (et_pass.getText().toString().equals(et_re_pass.getText().toString())) {
                    //Thêm vào cơ sở dữ liệu
                    //Thêm Khách hàng trước
                    DataClient insertCus_Emp = APIManagerment.getData();
                    Call<String> callback = insertCus_Emp.InsertCus_Emp(et_name.getText().toString(), et_phone.getText().toString(), et_mail.getText().toString(), "0");
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                            if (response.body().equals("Ok")){
                                //Sau khi thêm khách hàng sẽ lấy id của khách hàng mới được tạo
                                DataClient getMaxidCus_emp = APIManagerment.getData();
                                Call<String> callback = getMaxidCus_emp.getMaxIDCus_Emp(et_mail.getText().toString());
                                callback.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                                        final String maxid ;
                                        if (response.body().trim().length() != 0){
                                            Log.d("maxid",response.body());
                                            maxid = response.body();
                                            //Thêm địa chỉ của khách hàng
                                            DataClient insertAddress = APIManagerment.getData();
                                            Call<String> callback = insertAddress.InsertAddress(maxid,"Tp. Hồ Chí Minh",spn_district.getSelectedItem().toString(),spn_state.getSelectedItem().toString(),et_address.getText().toString(),"-1","1");
                                            callback.enqueue(new Callback<String>() {
                                                @Override
                                                public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                                                    Log.d("Ok",response.body());
                                                }

                                                @Override
                                                public void onFailure(Call<String> call, Throwable t) {
                                                    Log.d("fail1",t.getMessage());
                                                }
                                            });
                                            // Thêm tài khoản của khách hàng
                                            DataClient insertUser = APIManagerment.getData();
                                            Call<String> callback1 = insertUser.InsertUser(maxid,et_pass.getText().toString());
                                            callback1.enqueue(new Callback<String>() {
                                                @Override
                                                public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                                                    Log.d("user",response.body());
                                                    showAlertDialog();
                                                }

                                                @Override
                                                public void onFailure(Call<String> call, Throwable t) {
                                                    Log.d("failuser",t.getMessage());
                                                }
                                            });
                                        }

                                    }
                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Log.d("failid",t.getMessage());
                                    }
                                });
                            }else {
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("id",t.getMessage());
                            Toast.makeText(RegisterActivity.this, "Email hoặc sdt đã đăng ký", Toast.LENGTH_SHORT).show();
                            et_pass.setText("");
                            et_re_pass.setText("");

                        }
                    });

                }
                else {
                    et_pass.setText("");
                    et_re_pass.setText("");
                    showAlertDialog_DKTB();
                }}
        });
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo");
        builder.setMessage("Đăng Ký Thành Công");
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                RegisterActivity.this.finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
    public void showAlertDialog_DKTB(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo");
        builder.setMessage("Re-Pass Không trùng khớp");
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
    public void showAlertDialog_DKTB2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo");
        builder.setMessage("Email hoặc SĐT đã được dùng để đăng kí");
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
    public  void FindViewByIds(){
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_accpect_register = (Button) findViewById(R.id.btn_accept_register);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_mail = (EditText) findViewById(R.id.et_mail);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_re_pass = (EditText) findViewById(R.id.et_reenter_pass);
        spn_district = (Spinner) findViewById(R.id.spn_district);
        spn_state = (Spinner) findViewById(R.id.spn_state);
        et_address = (EditText) findViewById(R.id.et_address);
    }

    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
