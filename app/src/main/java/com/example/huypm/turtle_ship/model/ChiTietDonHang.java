package com.example.huypm.turtle_ship.model;

public class ChiTietDonHang {
    private int Id=-1;
    private String Hinhanh="";
    private String Mota="";
    private String Dinhgia="";
    private int Khoiluong=-1;
    private int Soluong=-1;

    public ChiTietDonHang(){}
    public ChiTietDonHang(int id, String hinhanh, String mota, String dinhgia, int khoiluong, int soluong){
        this.Id=id;
        this.Mota=mota;
        this.Hinhanh=hinhanh;
        this.Dinhgia=dinhgia;
        this.Khoiluong=khoiluong;
        this.Soluong=soluong;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHinhanh() {
        return Hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        Hinhanh = hinhanh;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getDinhgia() {
        return Dinhgia;
    }

    public void setDinhgia(String dinhgia) {
        Dinhgia = dinhgia;
    }

    public int getKhoiluong() {
        return Khoiluong;
    }

    public void setKhoiluong(int khoiluong) {
        Khoiluong = khoiluong;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }
}
