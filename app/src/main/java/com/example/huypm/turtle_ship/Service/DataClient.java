package com.example.huypm.turtle_ship.Service;

import com.example.huypm.turtle_ship.model.Customer_Employee;
import com.example.huypm.turtle_ship.model.DiaChi;
import com.example.huypm.turtle_ship.model.DonHangForShipper;
import com.example.huypm.turtle_ship.model.DonHangFullInfo;
import com.example.huypm.turtle_ship.model.ItemDonHang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DataClient {

    @FormUrlEncoded
    @POST("AddUsers.php")
    Call<String> InsertUser(@Field("Cus_Emp") String cus_emp
                            ,@Field("Pass") String pass);

    @FormUrlEncoded
    @POST("AddCus_Emp.php")
    Call<String> InsertCus_Emp(@Field("Ten") String ten
                                ,@Field("SDT") String sdt
                                ,@Field("Email") String email
                                ,@Field("NV") String nv);

    @FormUrlEncoded
    @POST("AddAddress.php")
    Call<String> InsertAddress(@Field("Cus_Emp") String cus_emp
                                ,@Field("ThanhPho") String thanhpho
                                ,@Field("Quan") String quan
                                ,@Field("Phuong") String phuong
                                ,@Field("Duong") String duong
                                ,@Field("GiaoNhan") String giaonhan
                                ,@Field("DCChinh") String dcchinh);

    @FormUrlEncoded
    @POST("getMaxIdCus_Emp.php")
    Call<String> getMaxIDCus_Emp(@Field("Email") String email);

    @FormUrlEncoded
    @POST("Sign_in.php")
    Call<String> sign_in(@Field("Account") String account
                        ,@Field("Pass") String pass);

    @FormUrlEncoded
    @POST("getCus_Emp_Info.php")
    Call<List<Customer_Employee>> getCusEmpInfo(@Field("Id") String id);

    @FormUrlEncoded
    @POST("getDiaChi.php")
    Call<List<DiaChi>> getDiaChi(@Field("Id") String id);

    @FormUrlEncoded
    @POST("AddNguoiNhan.php")
    Call<String> InsertNguoiNhan(@Field("Ten") String ten
                               ,@Field("SDT") String sdt);

    @FormUrlEncoded
    @POST("getIdNguoiNhan.php")
    Call<String> getIdNguoiNhan(@Field("Ten") String ten
                                ,@Field("SDT") String sdt);

    @FormUrlEncoded
    @POST("AddDonHang.php")
    Call<String> InsertDonHang(@Field("KhachHang") String khachhang
                                ,@Field("NguoiNhan") String nguoinhan
                                ,@Field("NgayDat") String ngaydat
                                ,@Field("NgayGiao") String ngaygiao
                                ,@Field("NgayNhan") String ngaynhan
                                ,@Field("DCNhanHang") String dcnhanhang
                                ,@Field("DCGiaoHang") String dcgiaohang
                                ,@Field("HTGiaoHang") String htgiaohang
                                ,@Field("CaySo") String cayso
                                ,@Field("ThanhTien") String thanhtien);

    @FormUrlEncoded
    @POST("getIdDonHang.php")
    Call<String> getIdDonHang(@Field("KhachHang") String khachhang
                                ,@Field("NguoiNhan") String nguoinhan
                                ,@Field("NgayDat") String ngaydat);

    @FormUrlEncoded
    @POST("AddCTDH.php")
    Call<String> InsertCTDH(@Field("DonHang") String donhang
                            ,@Field("HinhAnh") String hinhanh
                            ,@Field("Mota") String mota
                            ,@Field("DinhGia") String dinhgia
                            ,@Field("KhoiLuong") String khoiluong
                            ,@Field("SoLuong") String SoLuong);

    @FormUrlEncoded
    @POST("getInfoDonHang.php")
    Call<List<DonHangFullInfo>> getInfoDonHang(@Field("Id") String id);

    @FormUrlEncoded
    @POST("getDiaChiID.php")
    Call<List<DiaChi>> getDiaChiID(@Field("Id") String id);

    @FormUrlEncoded
    @POST("Check_NV.php")
    Call<String> check_nv(@Field("Id") String id);

    @FormUrlEncoded
    @POST("getTransportFee.php")
    Call<String> getTrnsprtFee(@Field("Distance") String distance
                                ,@Field("Value") String value
                                ,@Field("Weight") String weight);

    @POST("getDonHang.php")
    Call<List<DonHangForShipper>> getDonHangShipper();

    @FormUrlEncoded
    @POST("getDonHangShipped.php")
    Call<List<DonHangForShipper>> getDonHangShipped(@Field("Id") String id);

    @FormUrlEncoded
    @POST("getDonHangOfShipper.php")
    Call<List<DonHangForShipper>> getDonHangOfShipper(@Field("Id") String id);

    @FormUrlEncoded
    @POST("updateCus_emp.php")
    Call<String> updateCus(@Field("Id") String id
                            ,@Field("Ten") String ten
                            ,@Field("Email") String email
                            ,@Field("SDT") String sdt);

    @FormUrlEncoded
    @POST("updateUsers.php")
    Call<String> updatePass(@Field("Id") String id
                            ,@Field("Pass") String pass);
}
