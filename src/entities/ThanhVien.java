package entities;

import java.util.Date;

public class ThanhVien {
    private String maTV;
    private String tenTV;
    private String email;
    private Date ngaySinh;
    private String hangThe;
    private int diemTichLuy;
    private Date ngayCap;

    // Constructor
    public ThanhVien(String maTV, String tenTV, String email, Date ngaySinh, int diemTichLuy, Date ngayCap) {
        this.maTV = maTV;
        this.tenTV = tenTV;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.diemTichLuy = diemTichLuy;
        setHangThe();
        this.ngayCap = ngayCap;
    }

    // Getters and Setters
    public String getMaTV() {
        return maTV;
    }

    public void setMaTV(String maTV) {
        this.maTV = maTV;
    }

    public String getTenTV() {
        return tenTV;
    }


    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getHangThe() {
        return hangThe;
    }

    public void setHangThe() {
        if (this.diemTichLuy<100) hangThe="Bạc";
        else if (this.diemTichLuy<1000) hangThe="Vàng";
        else hangThe="Kim Cương";
    }
    
    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public Date getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(Date ngayCap) {
        this.ngayCap = ngayCap;
    }
}