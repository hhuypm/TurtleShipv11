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

import com.example.huypm.turtle_ship.Fragments.Orders;
import com.example.huypm.turtle_ship.Fragments.infor_cus;
import com.example.huypm.turtle_ship.Fragments.map_view_shipper;
import com.example.huypm.turtle_ship.Fragments.order_step1;
import com.example.huypm.turtle_ship.Fragments.status_oder;
import com.example.huypm.turtle_ship.Fragments.status_oder1;

public class MainContentShiper extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentManager{
    int id_cus;
    Bundle bundle = new Bundle();
    Bundle bundle2 = new Bundle();
    private CheckBox cb_sent, cb_receive;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_shiper);
        Intent intent = getIntent();
        id_cus = intent.getIntExtra("ID",-1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_shipper);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_shipper);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_shipper);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_shipper);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_shipper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_shipper) {
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

        if (id == R.id.DonHangShip) {
            fragment = new status_oder1();
        } else if (id == R.id.TimKiemDonHang) {
            fragment = new map_view_shipper();
        } else if (id == R.id.QuanLyDonHang) {
            fragment = new status_oder();

        } else if (id == R.id.DSThongBao) {

        } else if (id == R.id.QuanLyThongTin) {
            fragment = new infor_cus();
        } else if (id == R.id.LienHe) {

        } else if (id == R.id.DoiMatKhau) {

        } else if (id == R.id.Thoat) {

        }

        if (fragment != null){

            bundle.putInt("ID",id_cus);
            fragment.setArguments(bundle);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft =  fm.beginTransaction();
            ft.replace(R.id.content_main_shipper,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_shipper);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Bundle onDataSelected(Bundle bd,String key,String data) {
        bd.putString(key,data);
        return bd;
    }



}
