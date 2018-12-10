package com.example.huypm.turtle_ship.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemDonHang {

    @SerializedName("DonHang")
    @Expose
    private String donHang;
    @SerializedName("KhachHang")
    @Expose
    private String khachHang;
    @SerializedName("NguoiNhan")
    @Expose
    private String nguoiNhan;
    @SerializedName("SDTNguoiNhan")
    @Expose
    private String sDTNguoiNhan;
    @SerializedName("NoiGiaoHang")
    @Expose
    private String noiGiaoHang;
    @SerializedName("NoiNhanHang")
    @Expose
    private String noiNhanHang;

    public String getDonHang() {
        return donHang;
    }

    public void setDonHang(String donHang) {
        this.donHang = donHang;
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

    public String getSDTNguoiNhan() {
        return sDTNguoiNhan;
    }

    public void setSDTNguoiNhan(String sDTNguoiNhan) {
        this.sDTNguoiNhan = sDTNguoiNhan;
    }

    public String getNoiGiaoHang() {
        return noiGiaoHang;
    }

    public void setNoiGiaoHang(String noiGiaoHang) {
        this.noiGiaoHang = noiGiaoHang;
    }

    public String getNoiNhanHang() {
        return noiNhanHang;
    }

    public void setNoiNhanHang(String noiNhanHang) {
        this.noiNhanHang = noiNhanHang;
    }

}