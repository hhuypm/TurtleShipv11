package com.example.huypm.turtle_ship.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DonHangForShipper implements Serializable {

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
    private Object khoiLuong;
    @SerializedName("DinhGia")
    @Expose
    private Object dinhGia;
    @SerializedName("SoLuong")
    @Expose
    private Object soLuong;

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

    public Object getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(Object khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public Object getDinhGia() {
        return dinhGia;
    }

    public void setDinhGia(Object dinhGia) {
        this.dinhGia = dinhGia;
    }

    public Object getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Object soLuong) {
        this.soLuong = soLuong;
    }

}