package com.example.huypm.turtle_ship.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DonHangFullInfo implements Serializable {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("KhachHang")
    @Expose
    private String khachHang;
    @SerializedName("NguoiNhan")
    @Expose
    private String nguoiNhan;
    @SerializedName("Shipper")
    @Expose
    private String shipper;
    @SerializedName("NgayDat")
    @Expose
    private String ngayDat;
    @SerializedName("NgayGiao")
    @Expose
    private String ngayGiao;
    @SerializedName("NgayNhan")
    @Expose
    private String ngayNhan;
    @SerializedName("DCNhanHang")
    @Expose
    private String dCNhanHang;
    @SerializedName("DCGiaoHang")
    @Expose
    private String dCGiaoHang;
    @SerializedName("HTGiaoHang")
    @Expose
    private String hTGiaoHang;
    @SerializedName("CaySo")
    @Expose
    private String caySo;
    @SerializedName("ThanhTien")
    @Expose
    private String thanhTien;
    @SerializedName("TrangThai")
    @Expose
    private String trangThai;
    @SerializedName("Mota")
    @Expose
    private String mota;
    @SerializedName("KhoiLuong")
    @Expose
    private String khoiLuong;
    @SerializedName("DinhGia")
    @Expose
    private String dinhGia;
    @SerializedName("SoLuong")
    @Expose
    private String soLuong;
    @SerializedName("Ten")
    @Expose
    private String ten;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("SDT")
    @Expose
    private String sDT;
    @SerializedName("TenNguoiNhan")
    @Expose
    private String tenNguoiNhan;
    @SerializedName("SDTNguoiNhan")
    @Expose
    private String sDTNguoiNhan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(String nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(String ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public String getDCNhanHang() {
        return dCNhanHang;
    }

    public void setDCNhanHang(String dCNhanHang) {
        this.dCNhanHang = dCNhanHang;
    }

    public String getDCGiaoHang() {
        return dCGiaoHang;
    }

    public void setDCGiaoHang(String dCGiaoHang) {
        this.dCGiaoHang = dCGiaoHang;
    }

    public String getHTGiaoHang() {
        return hTGiaoHang;
    }

    public void setHTGiaoHang(String hTGiaoHang) {
        this.hTGiaoHang = hTGiaoHang;
    }

    public String getCaySo() {
        return caySo;
    }

    public void setCaySo(String caySo) {
        this.caySo = caySo;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(String khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public String getDinhGia() {
        return dinhGia;
    }

    public void setDinhGia(String dinhGia) {
        this.dinhGia = dinhGia;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return sDT;
    }

    public void setSDT(String sDT) {
        this.sDT = sDT;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getSDTNguoiNhan() {
        return sDTNguoiNhan;
    }

    public void setSDTNguoiNhan(String sDTNguoiNhan) {
        this.sDTNguoiNhan = sDTNguoiNhan;
    }

}