package com.example.myapplication;

import java.io.Serializable;

public class Taxi implements Serializable {
    private int ma;
    private String soXe;
    private double quangDuong;
    private int gia;
    private int khuyenMai;


    public double tinhTongTien(){
        return gia*quangDuong*(100-khuyenMai)/100;

    }
    public Taxi() {
    }

    public Taxi(String soXe, double quangDuong, int gia, int khuyenMai) {
        this.soXe = soXe;
        this.quangDuong = quangDuong;
        this.gia = gia;
        this.khuyenMai = khuyenMai;
    }

    public Taxi(int ma, String soXe, double quangDuong, int gia, int khuyenMai) {
        this.ma = ma;
        this.soXe = soXe;
        this.quangDuong = quangDuong;
        this.gia = gia;
        this.khuyenMai = khuyenMai;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getSoXe() {
        return soXe;
    }

    public void setSoXe(String soXe) {
        this.soXe = soXe;
    }

    public double getQuangDuong() {
        return quangDuong;
    }

    public void setQuangDuong(double quangDuong) {
        this.quangDuong = quangDuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }
}
