package com.example.huypm.turtle_ship.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.huypm.turtle_ship.model.ChiTietDonHang;
import com.example.huypm.turtle_ship.model.Customer_Employee;
import com.example.huypm.turtle_ship.model.DiaChi;
import com.example.huypm.turtle_ship.model.DonHang;
import com.example.huypm.turtle_ship.model.NguoiNhan;
import com.example.huypm.turtle_ship.model.Users;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
public class TurtleShipManager extends SQLiteOpenHelper {
    // Tên cớ sở dữ liệu
    private static final String DATABASE_NAME = "TurleShip";

    //Bảng Users
    private static final String TABLE_USERS = "Users";
    private static final String ID = "Id";
    private static final String PASS = "Pass";
    private static final String CUS_EMP = "Cus_Emp";
    private String SQLQuery = "CREATE TABLE " + TABLE_USERS + " (" +
            ID + " integer primary key, " +
            CUS_EMP + " integer, " +
            PASS + " TEXT) ";

    // Thêm User
    public void addUser(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (user.getId() != -1){
            values.put(ID, user.getId());
            values.put(CUS_EMP, user.getCus_Emp());
            values.put(PASS, user.getPass());
            db.insert(TABLE_USERS, null, values);
        }
        db.close();
    }

    // Lấy tất cả User
    public List<Users> getAlluser() {
        List<Users> listUser = new ArrayList<>();

        String selectquery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);
        if (cursor.moveToFirst()) {
            do {
                Users user = new Users();
                user.setId(cursor.getInt(0));
                user.setCus_Emp(cursor.getInt(1));
                user.setPass(cursor.getString(2));
                listUser.add(user);
            } while (cursor.moveToNext());
        }
        db.close();
        return listUser;
    }
    // Lay Max id User
    public int MaxIdUser(){
        int maxid = -1;
        String selectquery = "SELECT max(id) FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);
        if (cursor.moveToFirst())
            if (cursor.getString(0)!= null)
                maxid = Integer.parseInt(cursor.getString(0));
        return maxid;
    }
    //Bảng Cus_Emp
    private static final String TABLES_CusEmp = "Customer_Employee";
    private static final String ID_cus = "Id";
    private static final String Ten_cus= "Ten";
    private static final String SDT_cus= "SDT";
    private static final String Email_cus= "Email";
    private static final String NV= "Nhanvien";
    private String SQLQuery2 = "CREATE TABLE " + TABLES_CusEmp + " (" +
                    ID_cus + " integer primary key," +
                    Ten_cus + " String, " +
                    SDT_cus + " String, " +
                    Email_cus + " String, "+
                    NV + " integer )";




    // Lay max id Cus_Emp
    public int getMaxIdCus_Emp(){
        int maxID = -1;
        String selectQuery = "SELECT max(Id) FROM " +TABLES_CusEmp;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) != null)
            maxID = Integer.parseInt(cursor.getString(0));
        }
        db.close();
        return maxID;
    }


    //bang Nguoi Nhan
    private static final String TABLES_NguoiNhan = "NguoiNhan";
    private static final String ID_nguoinhan = "Id";
    private static final String Ten_nguoinhan= "Ten";
    private static final String SDT_nguoinhan= "SDT";
    private String SQLQuery6 = "CREATE TABLE " + TABLES_NguoiNhan + " (" +
            ID_nguoinhan + " integer primary key," +
            Ten_nguoinhan + " String, " +
            SDT_nguoinhan + " String )" ;

    // Thêm Nguoi Nhan
    public void addNguoiNhan(NguoiNhan nguoiNhan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (nguoiNhan.getId() != -1) {
            values.put(ID, nguoiNhan.getId());
            values.put(Ten_nguoinhan, nguoiNhan.getTen());
            values.put(SDT_nguoinhan, nguoiNhan.getSDT());
            db.insert(TABLES_NguoiNhan, null, values);
        }
        db.close();
    }

    // Lấy tất cả thong tin nguoi nhan theo id KhachHang
    public Cursor getAllInfo_NguoiNhan(int id) {
        List<Customer_Employee> listCus = new ArrayList<>();

        String selectquery = "SELECT distinct("+TABLES_NguoiNhan+".*) FROM " + TABLES_NguoiNhan +","+TABLES_DiaChi+"where "+TABLES_DiaChi+".Id ="+String.valueOf(id)+" and "+TABLES_DiaChi+".GiaoNhan ="+TABLES_NguoiNhan+".Id";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) == null)
                return null;
        }
        db.close();
        return cursor;
    }
    // Lay max id NguoiNhan
    public int getMaxIdNguoiNhan(){
        int maxID = -1;
        String selectQuery = "SELECT max(Id) FROM " +TABLES_NguoiNhan;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) != null)
                maxID = Integer.parseInt(cursor.getString(0));
        }
        db.close();
        return maxID;
    }


    // Dang nhap
    public int Sign_in(String account, String pass){
        String selectQuery = "Select "+TABLES_CusEmp+".Id from " + TABLES_CusEmp+", "+TABLE_USERS +" where ("+TABLES_CusEmp+".SDT=\""+account+"\" or "+TABLES_CusEmp+".Email = \""+account+"\") and " +
                TABLES_CusEmp+".Id ="+TABLE_USERS+".Cus_Emp and "+TABLE_USERS+".Pass = \""+pass+"\"" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) != null) {
                db.close();
                return cursor.getInt(0);
            }
        }
        db.close();
        return -1;
    }

    //Check đăng ký
    public int Check_Register(String phone,String mail){
        String selectQuery = "Select * from " +TABLES_CusEmp +" where SDT=\"" + phone+"\" or Email=\"" +mail +"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) != null) {
                db.close();
                return cursor.getInt(0);
            }
        }
        db.close();
        return 1;
    }

    //Bảng DiaChi
    private static final String TABLES_DiaChi = "DiaChi";
    private static final String ID_diachi = "Id";
    private static final String Cus_emp= "Cus_emp";
    private static final String TP= "ThanhPho";
    private static final String Quan= "Quan";
    private static final String Phuong= "Phuong";
    private static final String Duong= "Duong";
    private static final String GiaoNhan= "GiaoNhan";
    private static final String DCChinh= "DCChinh";
    private String SQLQuery3 = "CREATE TABLE " + TABLES_DiaChi + " (" +
            ID_diachi + " integer primary key," +
            Cus_emp + " String, " +
            TP + " String, " +
            Quan + " String, "+
            Phuong + " String, "+
            Duong + " String, "+
            GiaoNhan + " integer, "+
            DCChinh + " integer )";

    // Thêm DiaChi

    //Lay dia chi theo id khach hang



    // Lấy tất cả DiaChi

    // lay thong tin dia chi
    public Cursor getDiaChi(int id){
        String selectQuery = "SELECT * From " + TABLES_DiaChi +" where Id="+String.valueOf(id);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) != null) {
                return cursor;
            }
        }
        return null;
    }
    //Lấy Id max cửa địa chỉ
    public  int getMaxidAdd(){
        int maxId=-1;
        String selectQuery = "SELECT max(Id) FROM " +TABLES_DiaChi;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) != null)
                maxId = Integer.parseInt(cursor.getString(0));
        }
        db.close();
        return maxId;
    }


    //Bảng DonHang
    private static final String TABLES_DonHang = "DonHang";
    private static final String ID_donhang = "Id";
    private static final String KhachHang= "KhachHang";
    private static final String NguoiNhan= "NguoiNhan";
    private static final String NgayDat= "NgayDat";
    private static final String NgayGiao= "NgayGiao";
    private static final String NgayNhan= "NgayNhan";
    private static final String DCNhanHang= "DCNhanHang";
    private static final String DCGiaoHang= "DCGiaoHang";
    private static final String HTGiaoHang= "HTGiaoHang";
    private static final String CaySo= "CaySo";
    private static final String ThanhTien= "ThanhTien";
    private String SQLQuery4 = "CREATE TABLE " + TABLES_DonHang + " (" +
            ID_donhang + " integer primary key," +
            KhachHang + " String, " +
            NguoiNhan + " integer, " +
            NgayDat + " String, " +
            NgayGiao + " String, "+
            NgayNhan + " String, "+
            DCNhanHang + " String, "+
            DCGiaoHang + " String, "+
            HTGiaoHang + " String, "+
            CaySo + " String, "+
            ThanhTien + " TEXT )";

    // Thêm DonHang
    public void addDonHang (DonHang donHang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, donHang.getId());
        values.put(KhachHang, donHang.getKh());
        values.put(NguoiNhan, donHang.getNguoiNhan());
        values.put(NgayDat, donHang.getNgayDat());
        values.put(NgayGiao, donHang.getNgayGiao());
        values.put(NgayNhan, donHang.getNgayNhan());
        values.put(DCGiaoHang, donHang.getDCgiaohang());
        values.put(DCNhanHang, donHang.getDCnhanhang());
        values.put(HTGiaoHang, donHang.getHTgiaohang());
        values.put(CaySo, donHang.getCayso());
        values.put(ThanhTien, donHang.getThanhTien());
        db.insert(TABLES_DonHang, null, values);
        db.close();
    }

    // Lấy tất cả DonHang
    public List<DonHang> getAllDonHang() {
        List<DonHang> listDonHang = new ArrayList<>();

        String selectquery = "SELECT * FROM " + TABLES_DonHang;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);
        if (cursor.moveToFirst()) {
            do {
                DonHang donHang = new DonHang();
                donHang.setId(cursor.getInt(0));
                donHang.setKh(cursor.getInt(4));
                donHang.setNgayDat(cursor.getString(3));
                donHang.setNgayGiao(cursor.getString(3));
                donHang.setNgayNhan(cursor.getString(2));
                donHang.setDCnhanhang(cursor.getInt(2));
                donHang.setDCgiaohang(cursor.getInt(2));
                donHang.setHTgiaohang(cursor.getInt(2));
                donHang.setCayso(cursor.getInt(2));
                donHang.setThanhTien(cursor.getInt(2));
                listDonHang.add(donHang);
            } while (cursor.moveToNext());
        }
        db.close();
        return listDonHang;


    }

    //Lấy Id max cửa đơn hàng
    public  int maxIdOrder(){
        int maxId=-1;
        String selectQuery = "SELECT max(Id) FROM " +TABLES_DonHang;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) != null)
                maxId = Integer.parseInt(cursor.getString(0));
        }
        db.close();
        return maxId;
    }

    //Bảng ChiTietDonHang
    private static final String TABLES_CTDH = "CTDH";
    private static final String ID_CTDH = "ID";
    private static final String HinhAnh= "HinhAnh";
    private static final String Mota= "Mota";
    private static final String DinhGia= "DinhGia";
    private static final String KhoiLuong= "KhoiLuong";
    private static final String SoLuong= "SoLuong";
    private String SQLQuery5 = "CREATE TABLE " + TABLES_CTDH + " (" +
            ID_CTDH + " integer primary key," +
            HinhAnh + " String, " +
            Mota + " String, " +
            DinhGia + " String, "+
            KhoiLuong + " String, "+
            SoLuong + " TEXT )";

    // Thêm ChiTietDonHang
    public void addCTDH (ChiTietDonHang chiTietDonHang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, chiTietDonHang.getId());
        values.put(HinhAnh, chiTietDonHang.getHinhanh());
        values.put(Mota, chiTietDonHang.getMota());
        values.put(DinhGia, chiTietDonHang.getDinhgia());
        values.put(KhoiLuong, chiTietDonHang.getKhoiluong());
        values.put(SoLuong, chiTietDonHang.getSoluong());
        db.insert(TABLES_CTDH, null, values);
        db.close();
    }


    // Lấy tất cả ChiTietDonHang
    public List<ChiTietDonHang> getAllCTDH() {
        List<ChiTietDonHang> listCTDH = new ArrayList<>();

        String selectquery = "SELECT * FROM " + TABLES_CTDH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);
        if (cursor.moveToFirst()) {
            do {
                ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
                chiTietDonHang.setId(cursor.getInt(0));
                chiTietDonHang.setHinhanh(cursor.getString(4));
                chiTietDonHang.setMota(cursor.getString(3));
                chiTietDonHang.setDinhgia(cursor.getString(3));
                chiTietDonHang.setKhoiluong(cursor.getInt(2));
                chiTietDonHang.setSoluong(cursor.getInt(2));
                listCTDH.add(chiTietDonHang);
            } while (cursor.moveToNext());
        }
        db.close();
        return listCTDH;

    }

    //Lấy Id max cửa chi tiết đơn hàng
    public  int maxIdOrderDetail(){
        int maxId=-1;
        String selectQuery = "SELECT max(Id) FROM " +TABLES_CTDH;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) != null)
                maxId = Integer.parseInt(cursor.getString(0));
        }
        db.close();
        return maxId;
    }

    //Ham lay show oders
    public Cursor getOrders(int id){
        String selectQuery = "SELECT "+TABLES_DonHang+".Id _id,"+TABLES_CusEmp+".Ten,"+TABLES_NguoiNhan+".Ten,"+TABLES_NguoiNhan+".SDT,"+TABLES_DonHang+".DCgiaohang,"+TABLES_DonHang+".DCnhanhang FROM " +TABLES_DonHang+","+TABLES_CusEmp+","+TABLES_NguoiNhan+
                " where "+TABLES_DonHang+".KhachHang="+String.valueOf(id)+" and "+TABLES_CusEmp+".Id = "+String.valueOf(id)+" and "+TABLES_NguoiNhan+".Id ="+TABLES_DonHang+".NguoiNhan";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) != null) {
                return cursor;
            }
        }
        return null;
    }

    public TurtleShipManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        sqLiteDatabase.execSQL(SQLQuery2);
        sqLiteDatabase.execSQL(SQLQuery3);
        sqLiteDatabase.execSQL(SQLQuery4);
        sqLiteDatabase.execSQL(SQLQuery5);
        sqLiteDatabase.execSQL(SQLQuery6);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

}
