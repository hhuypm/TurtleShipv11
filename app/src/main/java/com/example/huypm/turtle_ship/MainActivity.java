package com.example.huypm.turtle_ship;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


import com.example.huypm.turtle_ship.DBManager.TurtleShipManager;
import com.example.huypm.turtle_ship.Service.APIManagerment;
import com.example.huypm.turtle_ship.Service.DataClient;
import com.example.huypm.turtle_ship.model.Customer_Employee;
import com.example.huypm.turtle_ship.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btn_register;
    Button btn_login;
    EditText et_account;
    EditText et_pass;
    private Dialog dialog;
    TurtleShipManager db = new TurtleShipManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        setContentView(R.layout.activity_main);
        findViewByIds();
        Date currentTime = Calendar.getInstance().getTime();
        Log.d("datetest",String.valueOf(currentTime));
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progress = ProgressDialog.show(MainActivity.this,
                        "Đang đăng nhập", "Đợi 1 chút xíu....", false, true, new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {

                            }
                        });
                progress.show();
                DataClient Sign_in = APIManagerment.getData();
                Call<String> callback = Sign_in.sign_in(et_account.getText().toString(),et_pass.getText().toString());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        if (!response.body().equals("")){
                            final int id = Integer.valueOf(response.body());
                            Bundle bundle = new Bundle();
                            bundle.putInt("ID",id);
                            DataClient check_nv = APIManagerment.getData();
                            Call<String> callback = check_nv.check_nv(response.body());
                            callback.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if (response.body().equals("0")){
                                        progress.dismiss();
                                        Intent intent = new Intent(getApplicationContext(), MainContent.class);
                                        intent.putExtra("ID",id);
                                        Log.d("item_list",String.valueOf(id));
                                        showAlertDialog_DN();
                                        startActivity(intent);
                                    }else {
                                        // nhaan vien dang nhap o day
                                        progress.dismiss();
                                        Intent intent = new Intent(getApplicationContext(), MainContentShiper.class);
                                        intent.putExtra("ID",id);
                                        Log.d("item_list",String.valueOf(id));
                                        showAlertDialog_DN();
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    progress.dismiss();
                                    showAlertDialog_DNTB();
                                    Log.d("login",t.getMessage());
                                }
                            });

                        }else {
                            showAlertDialog_DNTB();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        progress.dismiss();
                        showAlertDialog_DNTB();
                        et_account.setText("");
                        et_pass.setText("");
                        Log.d("login1",t.getMessage());
                    }
                });
            }
        });

        Button btn_test2 = (Button) findViewById(R.id.btn_fogot_pass);
        btn_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progress = ProgressDialog.show(MainActivity.this,
                        "Đang đăng nhập", "Đợi 1 chút xíu....", false, false, new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {

                            }
                        });
                progress.show();
            }
        });

        //TurtleShipManager db = new TurtleShipManager(this);
    }

    public void showAlertDialog_DN(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo");
        builder.setMessage("Đăng Nhập Thành Công");
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
    public void showAlertDialog_DNTB(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo");
        builder.setMessage("Đăng Nhập Thất Bại");
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
    public void findViewByIds(){
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_sign_in);
        et_account = (EditText) findViewById(R.id.email_phone);
        et_pass = (EditText) findViewById(R.id.password);
    }
    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
