package com.example.huypm.turtle_ship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.huypm.turtle_ship.Fragments.Orders;
import com.example.huypm.turtle_ship.Fragments.account;
import com.example.huypm.turtle_ship.Fragments.map_view;
import com.example.huypm.turtle_ship.Fragments.order_step1;
import com.example.huypm.turtle_ship.Fragments.single_info;

public class MainContent extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentManager{
    int id_cus;
    Bundle bundle = new Bundle();
    Bundle bundle2 = new Bundle();
    private CheckBox cb_sent, cb_receive;
    Switch switchCompat;
    EditText passPre, passNew, passRep;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        Intent intent = getIntent();
        id_cus = intent.getIntExtra("ID",-1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.DonHang) {
            fragment = new Orders();
            // Handle the camera action
        } else if (id == R.id.TheoDoiDH) {
            fragment = new single_info();
        } else if (id == R.id.TaoDonHang) {
            fragment = new order_step1();

        } else if (id == R.id.CachTinhPhi) {

        } else if (id == R.id.DonHangCTT) {
            fragment = new map_view();

        } else if (id == R.id.DangXuat) {

        } else if (id == R.id.ThongtinTK) {
            fragment = new account();

        } else if (id == R.id.DoiMatKhau) {

        }

        if (fragment != null){

            bundle.putInt("ID",id_cus);
            fragment.setArguments(bundle);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft =  fm.beginTransaction();
            ft.replace(R.id.content_main,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onCheckboxClicked(View view) {
        cb_sent = (CheckBox) findViewById(R.id.cb_sent);
        cb_receive = (CheckBox) findViewById(R.id.cb_receive);

        switch(view.getId()) {

            case R.id.cb_sent:

                cb_receive.setChecked(false);

                break;

            case R.id.cb_receive:

                cb_sent.setChecked(false);

                break;

        }
    }
/*    public void onCheckedChanged(View view){
        switchCompat = findViewById(R.id.sw);
        passPre = findViewById(R.id.textPassPre);
        passNew = findViewById(R.id.textPassNew);
        passRep = findViewById(R.id.textPassRepeatNew);
        if(switchCompat.isChecked()){
            passPre.setEnabled(true);
            passNew.setEnabled(true);
            passRep.setEnabled(true);
        }else{
            passPre.setEnabled(false);
            passNew.setEnabled(false);
            passRep.setEnabled(false);
        }
    }*/
    @Override
    public Bundle onDataSelected(Bundle bd,String key,String data) {
        bd.putString(key,data);
        return bd;
    }

    public static class MainContent_Shiper {
    }
}