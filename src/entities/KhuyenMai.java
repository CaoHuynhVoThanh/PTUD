package entities;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

public class KhuyenMai {
    private String ma;
    private String ten;
    private double phanTram;
    private LocalDateTime thoiGianBatDau;
    private LocalDateTime thoiGianKetThuc;
    private String thanhVien;
    private String loai;      

    // Constructor
    public KhuyenMai(String ma, String ten, double phanTram, LocalDateTime thoiGianBatDau,
                     LocalDateTime thoiGianKetThuc, String thanhVien, String loai) {
        this.ma = ma;
        this.ten = ten;
        this.phanTram = phanTram;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.thanhVien = thanhVien;
        this.loai = loai;
    }

    // Getter & Setter
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(double phanTram) {
        this.phanTram = phanTram;
    }

    public LocalDateTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalDateTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(String thanhVien) {
        this.thanhVien = thanhVien;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    // toString
    @Override
    public String toString() {
        return "KhuyenMai{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", phanTram=" + phanTram +
                ", thoiGianBatDau=" + thoiGianBatDau +
                ", thoiGianKetThuc=" + thoiGianKetThuc +
                ", thanhVien='" + thanhVien + '\'' +
                ", loai='" + loai + '\'' +
                '}';
    }
}


